package com.example.zain.knowledgetest;


import android.content.Intent;
import android.content.SharedPreferences;
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


public class SearchFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private Fragment fragment;
    private FragmentManager fragmentManager;

    Bundle bundle;

    TextView title;
    Typeface customFont;
    Boolean search;
    String searchString;
    EditText searchBox;
    Spinner spinnerPostcode, spinnerType;
    Button myButton;

    Bundle bundleSet;

    public SearchFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("cFrag", "kill");
        editor.commit();

        fragmentManager = getActivity().getSupportFragmentManager();
        fragment = new ViewFragment();

        bundle = new Bundle();

        title = (TextView) view.findViewById(R.id.textViewSearch);
        customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/segoe-ui-bold.ttf");
        title.setTypeface(customFont);


        searchBox = (EditText) view.findViewById(R.id.searchText);
        spinnerPostcode = (Spinner) view.findViewById(R.id.postcode_spinner);
        spinnerType = (Spinner) view.findViewById(R.id.type_spinner);
        myButton = (Button) view.findViewById(R.id.search_button);
        customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/segoe-ui.ttf");
        myButton.setText("Submit");
        myButton.setTypeface(customFont);

        myButton.setOnClickListener(this);

        loadSpinnerData();
        loadSpinnerDataPostcodes();


        return view;
    }

    @Override
    public void onClick(View v){

        search = true;


        if (spinnerPostcode.getSelectedItem().toString().trim().equals("Select Postcode") && spinnerType.getSelectedItem().toString().trim().equals("Select Type")) {

            searchString = searchBox.getText().toString().trim();

        }
        else if(searchBox.getText().toString().trim().equals("") && spinnerType.getSelectedItem().toString().trim().equals("Select Type")) {

            searchString = spinnerPostcode.getSelectedItem().toString();

        }
        else if(searchBox.getText().toString().trim().equals("") && spinnerPostcode.getSelectedItem().toString().trim().equals("Select Postcode")){

            searchString = spinnerType.getSelectedItem().toString();

        }
        else{
            search = false;
            Toast.makeText(getActivity(), "You can only search with one filter at a time", Toast.LENGTH_LONG).show();
        }

        if(search == true) {

            bundle.putString("searchString", searchString);
            fragment.setArguments(bundle);

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
        spinnerType.setAdapter(dataAdapter);

        String myString = "Select Type";
        int spinnerPosition = dataAdapter.getPosition(myString);
        spinnerType.setSelection(spinnerPosition);
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
        spinnerPostcode.setAdapter(dataAdapter);

        // set position
        String myString = "Select Postcode";
        int spinnerPosition = dataAdapter.getPosition(myString);
        spinnerPostcode.setSelection(spinnerPosition);
    }
}
