package com.example.zain.knowledgetest;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class addPoiFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private Fragment fragment;
    private FragmentManager fragmentManager;

    DatabaseHelper myDb;
    EditText editName, editAddress;
    Spinner spinPostcode, spinType;
    Button myButton;
    TextView title;
    Typeface customFont;

    public addPoiFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addpoi, container, false);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("cFrag", "kill");
        editor.commit();

        fragmentManager = getActivity().getSupportFragmentManager();
        fragment = new addPoiFragment();

        title = (TextView) view.findViewById(R.id.textview_addPOI);
        customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/segoe-ui-bold.ttf");
        title.setTypeface(customFont);

        String storagePath = Environment.getExternalStorageDirectory().toString();
        String inpFolderP = storagePath + "/.KnowImg";
        String delFolderP = storagePath + "/DeletedImg";
        File inpFolder = new File(inpFolderP);
        File delFolder = new File(delFolderP);

        if (!inpFolder.exists()){
            inpFolder.mkdir();
        }

        if(!delFolder.exists()){
            delFolder.mkdir();
        }

        myDb = new DatabaseHelper(getActivity());

        editName = (EditText) view.findViewById(R.id.editText_name);
        editAddress = (EditText) view.findViewById(R.id.editText_address);
        spinPostcode = (Spinner) view.findViewById(R.id.spinner_postcode);
        spinType = (Spinner) view.findViewById(R.id.spinner_type);

        myButton =(Button) view.findViewById(R.id.insertPOI_button);
        myButton.setOnClickListener(this);

        loadSpinnerDataPostcodes();
        loadSpinnerData();

        return view;

    }

    public void onClick(View v){
        String postcode = spinPostcode.getSelectedItem().toString().trim();
        String type = spinType.getSelectedItem().toString().trim();


        if(editName.getText().toString().trim().matches("") || editAddress.getText().toString().trim().matches("")
                || postcode.equals("Select") || type.equals("Select")){
            Toast.makeText(getActivity(), "Missing fields", Toast.LENGTH_LONG).show();
        }

        else{

            String name = editName.getText().toString().trim();
            String address = editAddress.getText().toString().trim();

            //String nameC = name.substring(0, 1).toUpperCase() + name.substring(1).trim();
            //String addressC = address.substring(0, 1).toUpperCase() + address.substring(1).trim();


            boolean isInserted = myDb.insertData(name, address, postcode, type, "");

            if (isInserted){

                Toast.makeText(getActivity(), "Successfully Inserted", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getActivity(), "Not inserted", Toast.LENGTH_LONG).show();
            }

            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.rootLayout, fragment).commit();
        }
    }



    private void loadSpinnerData() {
        // database handler
        DatabaseHelper myDb = new DatabaseHelper(getActivity());

        // Spinner Drop down elements
        List<String> lables = myDb.getAllTypes();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinType.setAdapter(dataAdapter);

        // set position
        String myString = "Select Type";
        int spinnerPosition = dataAdapter.getPosition(myString);
        spinType.setSelection(spinnerPosition);
    }


    private void loadSpinnerDataPostcodes() {
        // database handler
        DatabaseHelper myDb = new DatabaseHelper(getActivity());

        // Spinner Drop down elements
        List<String> lables = myDb.getAllPostcodes();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinPostcode.setAdapter(dataAdapter);

        // set position
        String myString = "Select Postcode";
        int spinnerPosition = dataAdapter.getPosition(myString);
        spinPostcode.setSelection(spinnerPosition);
    }
}

