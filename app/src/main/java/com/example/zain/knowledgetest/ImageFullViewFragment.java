package com.example.zain.knowledgetest;


import android.app.ActionBar;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageFullViewFragment extends android.support.v4.app.Fragment {

    View view;

    DatabaseHelper myDb;
    ImageView iv;
    PhotoViewAttacher mAttacher;
    Long id;
    Bundle bundleGet;


    public ImageFullViewFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_full_view, container, false);

        myDb = new DatabaseHelper(getActivity());
        bundleGet = getArguments();
        String ImgIDString = bundleGet.getString("ImgID");
        String searchString = bundleGet.getString("searchTerm");

        id = Long.valueOf(ImgIDString);
        iv = (ImageView) view.findViewById(R.id.imgFullView1);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("cFrag", "imageFullView");
        editor.putString("Row_ID", ImgIDString);
        editor.putString("searchTerm", searchString);
        editor.commit();


        getIMG();

        return view;
    }

    public void getIMG() {
        Cursor res = myDb.GetRow(id);
        if (res.moveToFirst()) {
            int position = bundleGet.getInt("ImgPosition");
            String path = res.getString(DatabaseHelper.ROWIMG);
            String[] imgs = path.split("\\s+");
            Bitmap bmp = BitmapFactory.decodeFile(imgs[position]);
            iv.setImageBitmap(bmp);
            mAttacher = new PhotoViewAttacher(iv);

        }


    }

}
