package com.example.zain.knowledgetest;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CustomAdapterView extends SimpleCursorAdapter{
    public CustomAdapterView(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View cV = super.getView(position, convertView, parent);
        int newPos = position + 1;
        TextView idP = (TextView) cV.findViewById(R.id.viewId);
        idP.setText(""+newPos);

        return cV;
    }
}
