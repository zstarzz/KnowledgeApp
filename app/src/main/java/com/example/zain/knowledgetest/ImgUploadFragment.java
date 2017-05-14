package com.example.zain.knowledgetest;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static android.app.Activity.RESULT_OK;

public class ImgUploadFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    View view;

    private Fragment fragment;
    private FragmentManager fragmentManager;

    Button uploadButton, chooseButton, cancelButton;
    TextView title;
    Typeface customFont;

    Bundle getBundle, setBundle;

    DatabaseHelper myDb;
    String iDinDB, ImgPath, searchString;
    private static final int RESULT_LOAD_IMAGE = 1;

    public ImgUploadFragment(){


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_img_upload, container, false);


        fragmentManager = getActivity().getSupportFragmentManager();
        getBundle = getArguments();
        setBundle = new Bundle();
        ImgPath = getBundle.getString("ImgPath");
        iDinDB = getBundle.getString("ImgID");
        searchString = getBundle.getString("searchTerm");


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("cFrag", "imgUpload");
        editor.putString("Row_ID", iDinDB);
        editor.putString("searchTerm", searchString);
        editor.commit();

        title = (TextView) view.findViewById(R.id.textview_uploadImage);
        customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/segoe-ui-bold.ttf");
        title.setTypeface(customFont);

        uploadButton = (Button) view.findViewById(R.id.uploadIMG_button);
        uploadButton.setOnClickListener(this);
        chooseButton =(Button) view.findViewById(R.id.chooseIMG_button);
        chooseButton.setOnClickListener(this);


        myDb = new DatabaseHelper(getActivity());

        getIMG();

        return view;
    }


    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.uploadIMG_button:

                String orgImgName = ImgPath;
                String rmW = orgImgName.replaceAll("\\s+", "");
                String storagePath = Environment.getExternalStorageDirectory().toString();
                String subName = rmW.substring(rmW.lastIndexOf("/") + 1);
                String NewImgName = storagePath + "/.KnowImg/" + subName;

                getBundle = getArguments();
                Boolean isUploaded = myDb.insertImg(iDinDB, NewImgName);

                if (isUploaded) {

                    File sourceLocation = new File(ImgPath);

                    File targetLocation = new File(storagePath + "/.KnowImg/" + subName);

                    try {

                        if (sourceLocation.exists()) {

                            InputStream in = new FileInputStream(sourceLocation);
                            OutputStream out = new FileOutputStream(targetLocation);

                            // Copy the bits from instream to outstream
                            byte[] buf = new byte[1024];
                            int len;

                            while ((len = in.read(buf)) > 0) {
                                out.write(buf, 0, len);
                            }

                            in.close();
                            out.close();

                            Toast.makeText(getActivity(), "Image uploaded", Toast.LENGTH_LONG).show();

                        }

                        else {
                            Log.v("MainActivity.java", "Copy file failed. Source file missing.");
                            Toast.makeText(getActivity(), "Image not copied", Toast.LENGTH_LONG).show();

                        }

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    fragment = new ImageViewFragment();
                    setBundle.putString("ImgID", iDinDB);
                    fragment.setArguments(setBundle);

                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.rootLayout, fragment).commit();

                }

                break;

            case R.id.chooseIMG_button:

                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);

                break;

            default:
                Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();

                break;


        }


    }

    public void getIMG(){

        Bitmap bmp= BitmapFactory.decodeFile(ImgPath);
        ImageView iv= (ImageView)view.findViewById(R.id.ImgViewU);
        iv.setImageBitmap(bmp);
    }


    public void chooseImg(View v){

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);

            c.close();
            //Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

            Log.e("path: ", picturePath + "");
            //ImgText.setText(picturePath);
            ImgPath = picturePath;
            getIMG();


        }
    }



    public void uploadImg(View v) {

    }
}
