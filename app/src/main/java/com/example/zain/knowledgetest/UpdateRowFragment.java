package com.example.zain.knowledgetest;


import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
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

import java.util.List;

public class UpdateRowFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    View view;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    TextView title;
    Typeface customFont;
    EditText nameUpdateText, addressUpdateText;
    DatabaseHelper myDb;
    Spinner spinPostcode, spinType;
    Button updateButton;
    String id, searchString;
    Long idL;

    Bundle getBundle;

    public UpdateRowFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_update_row, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();

        myDb = new DatabaseHelper(getActivity());
        title = (TextView) view.findViewById(R.id.textView_updateRow);
        customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/segoe-ui-bold.ttf");
        title.setTypeface(customFont);


        nameUpdateText = (EditText) view.findViewById(R.id.editText_name_u);
        addressUpdateText = (EditText) view.findViewById(R.id.editText_address_u);
        spinPostcode = (Spinner) view.findViewById(R.id.spinner_postcode_u);
        spinType = (Spinner) view.findViewById(R.id.spinner_type_u);
        updateButton = (Button) view.findViewById(R.id.updateRow_button);
        updateButton.setOnClickListener(this);
        //cancelButton = (Button) view.findViewById(R.id.cancelUpdate_button);
        //cancelButton.setOnClickListener(this);


        getBundle = getArguments();
        id = getBundle.getString("rowID");
        searchString = getBundle.getString("searchTerm");
        idL = Long.valueOf(id);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("cFrag", "updateRow");
        editor.putString("searchTerm", searchString);
        editor.commit();

        loadSpinnerData();
        loadSpinnerDataPostcodes();
        getList();


        return view;
    }


    public void getList(){
        Cursor res = myDb.GetRow(idL);
        if (res.moveToFirst()) {
            String name = res.getString(DatabaseHelper.ROWNAME);
            String address = res.getString(DatabaseHelper.ROWADDRESS);


            nameUpdateText.setText(name);
            addressUpdateText.setText(address);
        }
        res.close();
    }

    public void  onClick(View v){

        switch (v.getId()){
            case R.id.updateRow_button:

                String postcode = spinPostcode.getSelectedItem().toString().trim();
                String type = spinType.getSelectedItem().toString().trim();


                if(nameUpdateText.getText().toString().trim().matches("") || addressUpdateText.getText().toString().trim().matches("")
                        || postcode.equals("Select") || type.equals("Select")){

                    Toast.makeText(getActivity(), "Missing fields", Toast.LENGTH_LONG).show();
                }
                else {

                    String name = nameUpdateText.getText().toString().trim();
                    String address = addressUpdateText.getText().toString().trim();

                    //String nameC = name.substring(0, 1).toUpperCase() + name.substring(1).trim();
                    //String addressC = address.substring(0, 1).toUpperCase() + address.substring(1).trim();


                    Boolean isUpdated = myDb.updateData(id, name, address, postcode, type);

                    if (isUpdated) {
                        Toast.makeText(getActivity(), "Successfully updated", Toast.LENGTH_LONG).show();

                        Bundle bundleSet = new Bundle();
                        fragment = new ViewFragment();
                        bundleSet.putString("searchString", searchString);
                        fragment.setArguments(bundleSet);

                        final FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rootLayout, fragment).commit();

                    } else {
                        Toast.makeText(getActivity(), "Not updated", Toast.LENGTH_LONG).show();
                    }
                }
                break;

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

        // get type from db
        Cursor res = myDb.GetRow(idL);
        if (res.moveToFirst()){
            String myType = res.getString(DatabaseHelper.ROWTYPE);

            // set position
            int spinnerPosition = dataAdapter.getPosition(myType);
            spinType.setSelection(spinnerPosition);
        }


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

        // get type from db
        Cursor res = myDb.GetRow(idL);
        if (res.moveToFirst()){
            String myPostcode = res.getString(DatabaseHelper.ROWPOSTCODE);

            // set position
            int spinnerPosition = dataAdapter.getPosition(myPostcode);
            spinPostcode.setSelection(spinnerPosition);
        }
    }


}
