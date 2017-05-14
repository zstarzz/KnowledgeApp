package com.example.zain.knowledgetest;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ViewFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private Fragment fragment;
    private FragmentManager fragmentManager;

    DatabaseHelper myDb;
    ListView myList;
    ListView optionList;
    ImageView img;
    TextView title;
    Typeface customFont;
    Button bottomButton;

    Bundle bundleSet, bundleGet;

    public ViewFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();
        fragment = new ImageViewFragment();

        bundleSet = new Bundle();
        bundleGet = getArguments();

        title = (TextView) view.findViewById(R.id.textview_locations);
        customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/segoe-ui-bold.ttf");
        title.setTypeface(customFont);

        bottomButton = (Button) view.findViewById(R.id.button_bottom);
        bottomButton.setOnClickListener(this);

        myDb = new DatabaseHelper(getActivity());
        myList = (ListView) view.findViewById(R.id.listViewLocations);
        img = (ImageView) getActivity().findViewById(R.id.imageView);
        optionList = new ListView(getActivity());
        getListFromDb();
        registerListClickCallback();
        LongClick();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("cFrag", "View");
        editor.commit();


        return view;
    }

    public void onClick (View v){

        myList.post(new Runnable() {
            @Override
            public void run() {
                myList.setSelection(myList.getCount());
            }
        });

    }



    public void getListFromDb(){
        Cursor res = myDb.ViewAll();
        getActivity().startManagingCursor(res);

        //Map cursor from db to viewFields
        String[] fromFieldNames = new String[]{DatabaseHelper.COL_2, DatabaseHelper.COL_3, DatabaseHelper.COL_4, DatabaseHelper.COL_5};
        int[] toViewIDS = new int[]{R.id.viewName, R.id.viewAddress, R.id.viewPostcode, R.id.viewType};
        //Create SimpleCursorAdaptor with null cursor
        SimpleCursorAdapter myCursorAdaptor = new CustomAdapterView(getActivity(), R.layout.item_layout, null, fromFieldNames, toViewIDS, 0);
        // Set adaptor for listView
        myList.setAdapter(myCursorAdaptor);

        new AsyncTask<SimpleCursorAdapter, Void, Cursor>() {
            private SimpleCursorAdapter mSimpleCursorAdapter;
            @Override
            protected Cursor doInBackground(SimpleCursorAdapter... params) {
                // Save cursorAdapter to use in postExecute
                this.mSimpleCursorAdapter = params[0];
                // Load cursor on background thread with search function

                if (bundleGet != null) {


                    if (bundleGet.getString("searchString") != null) {

                        String searchString = bundleGet.getString("searchString");
                        return myDb.Search(searchString);
                    }
                }
                else {
                    return myDb.ViewAll();


                }
                return myDb.ViewAll();
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                // and update the cursor (which is already in the listview)
                this.mSimpleCursorAdapter.changeCursor(cursor);
            }
        }.execute(myCursorAdaptor);




    }




    private void registerListClickCallback() {
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long IDinDB) {
                Cursor res = myDb.GetRow(IDinDB);
                if (res.moveToFirst()) {

                    long idDB = res.getLong(DatabaseHelper.ROWID);
                    String ImgIDString = Long.toString(idDB);

                    String searchString = bundleGet.getString("searchString");

                    bundleSet.putString("ImgID", ImgIDString);
                    bundleSet.putString("searchTerm", searchString);

                    fragment.setArguments(bundleSet);

                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.rootLayout, fragment).commit();
                }
            }

        });

    }


    private void LongClick() {
        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View viewClicked, int position, long IDinDB) {

                Cursor res = myDb.GetRow(IDinDB);
                if (res.moveToFirst()) {
                    final long idDB = res.getLong(DatabaseHelper.ROWID);
                    final String SidDB = Long.toString(idDB);


                    CharSequence options[] = new CharSequence[]{"Edit", "Delete"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Action");
                    builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {

                                        fragment = new UpdateRowFragment();
                                        String searchString = bundleGet.getString("searchString");
                                        bundleSet.putString("rowID", SidDB);
                                        bundleSet.putString("searchTerm", searchString);
                                        fragment.setArguments(bundleSet);

                                        final FragmentTransaction transaction = fragmentManager.beginTransaction();
                                        transaction.replace(R.id.rootLayout, fragment).commit();
                                    } else {
                                        CharSequence yesNo[] = new CharSequence[]{"Yes", "No"};
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                        builder.setTitle("Are you sure?");
                                        builder.setItems(yesNo, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (which == 0) {
                                                            Cursor res = myDb.GetRow(idDB);
                                                            if (res.moveToFirst()) {
                                                                String path = res.getString(DatabaseHelper.ROWIMG);
                                                                if (!path.contains("/")) {
                                                                    myDb.deleteRow(SidDB);
                                                                    getListFromDb();
                                                                    Toast.makeText(getActivity(), "Successfully deleted", Toast.LENGTH_LONG).show();

                                                                } else {
                                                                    Toast.makeText(getActivity(), "Delete Image First", Toast.LENGTH_LONG).show();
                                                                }
                                                            }


                                                        }

                                                    }
                                                }

                                        );
                                        builder.show();


                                    }

                                }
                            }

                    );
                    builder.show();
                }
                return true;
            }

        });

    }



}
