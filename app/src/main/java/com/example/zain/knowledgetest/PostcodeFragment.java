package com.example.zain.knowledgetest;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class PostcodeFragment extends android.support.v4.app.Fragment {


    DatabaseHelper myDb;
    ListView myListPostcode;
    TextView postcodeTitle;
    Typeface customFont;

    public PostcodeFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_postcode, container, false);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("cFrag", "kill");
        editor.commit();

        postcodeTitle = (TextView) view.findViewById(R.id.postcode_title);
        customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/segoe-ui-bold.ttf");
        postcodeTitle.setTypeface(customFont);

        myDb = new DatabaseHelper(getActivity());
        myListPostcode = (ListView) view.findViewById(R.id.listViewPostcode);


        Cursor res = myDb.getPostcodeDb();
        getActivity().startManagingCursor(res);


        getPostcodeDb();
        //Map cursor from db to viewFields
        String[] fromFieldNames = new String[]{DatabaseHelper.COL_2_2, DatabaseHelper.COL_2_3};
        int[] toViewIDS = new int[]{R.id.PostCodeView, R.id.PostCodeInfo};
        //Create SimpleCursorAdaptor with null cursor
        SimpleCursorAdapter myCursorAdaptor = new SimpleCursorAdapter(getActivity(), R.layout.item_postcode_layout, res, fromFieldNames, toViewIDS, 0);
        // Set adaptor for listView
        myListPostcode.setAdapter(myCursorAdaptor);

        return view;
    }

    public void getPostcodeDb(){

    }




}
