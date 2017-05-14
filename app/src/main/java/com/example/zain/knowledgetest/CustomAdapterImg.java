package com.example.zain.knowledgetest;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class CustomAdapterImg extends ArrayAdapter<String> {

    public CustomAdapterImg(Context context, String[] imgs) {
        super(context, R.layout.img_layout, imgs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater zainsInflater = LayoutInflater.from(getContext());
        View customView = zainsInflater.inflate(R.layout.img_layout, parent, false);
        String singleImg = getItem(position);
        ImageView imgdb = (ImageView) customView.findViewById(R.id.img_db);
        //Bitmap bmp= BitmapFactory.decodeFile(singleImg);
        //imgdb.setImageBitmap(bmp);
        Picasso.with(getContext()).load(new File(singleImg)).resize(400, 600).into(imgdb);
        return customView;
    }
}
