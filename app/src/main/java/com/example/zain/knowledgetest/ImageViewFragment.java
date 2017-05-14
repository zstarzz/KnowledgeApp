package com.example.zain.knowledgetest;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static android.app.Activity.RESULT_OK;

public class ImageViewFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    View view;

    DatabaseHelper myDb;
    Long id;
    String searchString;
    Button uploadButton;
    TextView title;
    Typeface customFont;

    private static final int RESULT_LOAD_IMAGE = 1;

    private Fragment fragment;
    private FragmentManager fragmentManager;

    Bundle bundleSet, bundleGet;

    public ImageViewFragment(){


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_image_view, container, false);

        title = (TextView) view.findViewById(R.id.textview_viewImages);
        customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/segoe-ui-bold.ttf");
        title.setTypeface(customFont);

        myDb = new DatabaseHelper(getActivity());
        uploadButton = (Button) view.findViewById(R.id.imgUpload_button);
        uploadButton.setOnClickListener(this);



        fragmentManager = getActivity().getSupportFragmentManager();
        bundleSet = new Bundle();
        bundleGet = getArguments();

        if(bundleGet != null) {
            String data = bundleGet.getString("ImgID");
            searchString = bundleGet.getString("searchTerm");
            id = Long.valueOf(data);

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("cFrag", "imageView");
            editor.putString("searchTerm", searchString);
            editor.commit();

        }



        loadImgDb();
        registerListClickCallback();
        LongClick();



        return view;
    }


    public void loadImgDb(){
        ListView zainList = (ListView) view.findViewById(R.id.listView2);
        Cursor res = myDb.GetRow(id);
        if(res.moveToFirst()) {
            String path = res.getString(DatabaseHelper.ROWIMG);
            if (path != null) {
                String[] imgs = path.split("\\s+");
                ListAdapter zainAdapater = new CustomAdapterImg(getActivity(), imgs);
                zainList.setAdapter(zainAdapater);
            }

            res.close();
        }
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
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

            String idU = bundleGet.getString("ImgID");

            fragment = new ImgUploadFragment();
            bundleSet = new Bundle();

            bundleSet.putString("ImgPath", picturePath);
            bundleSet.putString("ImgID", idU);
            bundleSet.putString("searchTerm", searchString);

            fragment.setArguments(bundleSet);

            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.rootLayout, fragment).commit();

            //Intent intent = new Intent(ImageViewActivity.this, ImgUploadActivity.class);
            //intent.putExtra("ImgPath", picturePath);
            //intent.putExtra("ImgID", idU);
            //startActivity(intent);

        }
    }



    private void registerListClickCallback() {
        ListView zainList = (ListView) view.findViewById(R.id.listView2);
        zainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long IDinDB) {
                String ImgIDString = bundleGet.getString("ImgID");

                fragment = new ImageFullViewFragment();
                bundleSet = new Bundle();
                bundleSet.putInt("ImgPosition", position);
                bundleSet.putString("ImgID", ImgIDString);
                bundleSet.putString("searchTerm", searchString);
                fragment.setArguments(bundleSet);

                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.rootLayout, fragment).commit();

            }
        });

    }

    private void LongClick() {
        ListView zainList = (ListView) view.findViewById(R.id.listView2);
        zainList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View viewClicked, final int position, long IDinDB) {

                CharSequence options[] = new CharSequence[]{"Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Action");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            CharSequence yesNo[] = new CharSequence[]{"Yes", "No"};
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("Are you sure?");
                            builder.setItems(yesNo, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {

                                        if(bundleGet != null) {

                                            String ImgIdString = bundleGet.getString("ImgID");
                                            Cursor res = myDb.GetRow(id);
                                            if(res.moveToFirst()) {
                                                String path = res.getString(DatabaseHelper.ROWIMG);
                                                String[] imgs = path.split("\\s+");
                                                String img = imgs[position];
                                                Boolean isDeleted = myDb.deleteImg(ImgIdString,img);
                                                if (isDeleted == true)
                                                {
                                                    String storagePath = Environment.getExternalStorageDirectory().toString();
                                                    String subName = img.substring(img.lastIndexOf("/") + 1);

                                                    File sourceLocation = new File (img);

                                                    File targetLocation = new File (storagePath + "/DeletedImg/"+subName);

                                                    int choice = 1;



                                                    try {

                                                        if (choice == 1){
                                                            if(sourceLocation.exists()){

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

                                                                if(sourceLocation.delete()){
                                                                    Log.v("MainActivity.java", "Delete file successful.");
                                                                }

                                                                Log.v("MainActivity.java", "Copy file successful.");


                                                            }
                                                            else{
                                                                Log.v("MainActivity.java", "Copy file failed. Source file missing.");
                                                            }

                                                        }

                                                    }

                                                    catch (NullPointerException e) {
                                                        e.printStackTrace();
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }

                                                    loadImgDb();
                                                }
                                                else{
                                                    Toast.makeText(getActivity(), "Image not deleted", Toast.LENGTH_LONG).show();
                                                }
                                                //myDb.deleteRow(SidDB);


                                            }
                                        }



                                    }
                                }
                            });
                            builder.show();
                        }
                    }
                });
                builder.show();

                return true;
            }

        });

    }

}
