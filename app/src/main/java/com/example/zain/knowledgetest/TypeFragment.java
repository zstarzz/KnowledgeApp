package com.example.zain.knowledgetest;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class TypeFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    DatabaseHelper myDb;
    ListView myListType;
    EditText editText;
    Button myButton;
    TextView typeTitle;
    Typeface customFont;

    public TypeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_type, container, false);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("cFrag", "kill");
        editor.commit();

        typeTitle = (TextView) view.findViewById(R.id.type_title);
        customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/segoe-ui-bold.ttf");
        typeTitle.setTypeface(customFont);

        myDb = new DatabaseHelper(getActivity());
        myListType = (ListView) view.findViewById(R.id.listViewType);
        editText = (EditText) view.findViewById(R.id.editText_type);
        myButton = (Button) view.findViewById(R.id.addType_button);
        myButton.setOnClickListener(this);

        getTypeFromDb();
        LongClick();

        return view;
    }

    @Override
    public void onClick(View v) {
        String typeInput = editText.getText().toString().trim();

        if (!typeInput.equals("")) {
            String typeInputC = typeInput.substring(0, 1).toUpperCase() + typeInput.substring(1).trim();
            
            int numRows = myDb.checkDuplicateType(typeInputC);
            if(numRows == 0) {
                boolean isInserted = myDb.insertType(typeInputC);
                if (isInserted) {
                    Toast.makeText(getActivity(), "Successfully inserted", Toast.LENGTH_LONG).show();
                    getTypeFromDb();
                    editText.setText("");
                } else {
                    Toast.makeText(getActivity(), "Not inserted", Toast.LENGTH_LONG).show();
                }
            }

            else{
                Toast.makeText(getActivity(), typeInputC+" already exists!", Toast.LENGTH_LONG).show();
            }

        }
        else {
            Toast.makeText(getActivity(), "Enter something", Toast.LENGTH_LONG).show();
        }
    }




    public void getTypeFromDb() {
        Cursor res = myDb.ViewAllType();
        getActivity().startManagingCursor(res);

        //Map cursor from db to viewFields
        String[] fromFieldNames = new String[]{DatabaseHelper.COL_1_2};
        int[] toViewIDS = new int[]{R.id.viewType1};
        //Create SimpleCursorAdaptor with null cursor
        SimpleCursorAdapter myCursorAdaptor = new SimpleCursorAdapter(getActivity(), R.layout.item_layout_type, res, fromFieldNames, toViewIDS, 0);
        // Set adaptor for listView
        myListType.setAdapter(myCursorAdaptor);
    }


    private void LongClick() {
        myListType.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View viewClicked, int position, long IDinDB) {
                Cursor res = myDb.GetRowT(IDinDB);
                if (res.moveToFirst()) {
                    final long idDB = res.getLong(DatabaseHelper.ROWID);
                    final String SidDB = Long.toString(idDB);

                    final String type = res.getString(DatabaseHelper.ROWNAME);
                    res.close();
                    final int numRows = myDb.checkAttachedType(type);

                    CharSequence options[] = new CharSequence[]{"Delete", "Edit"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Action");
                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0 && !type.equals("Select")) {

                                CharSequence yesNo[] = new CharSequence[]{"Yes", "No"};
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Are you sure?");
                                builder.setItems(yesNo, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int choice) {

                                        if (choice == 0) {

                                            if (numRows == 0) {
                                                myDb.deleteRowT(SidDB);
                                                getTypeFromDb();
                                                Toast.makeText(getActivity(), "Successfully deleted", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(getActivity(), "Error! Type being used", Toast.LENGTH_LONG).show();

                                            }

                                        }
                                    }
                                });
                                builder.show();


                            } else if (!type.equals("Select")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                final EditText typeEdit = new EditText(getActivity());
                                typeEdit.setText(type);
                                builder.setTitle("Edit");
                                builder.setView(typeEdit);

                                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String typeView = typeEdit.getText().toString().trim();
                                        if (numRows == 0) {
                                            boolean isUpdatedT = myDb.updateType(type, typeView);
                                            if (isUpdatedT) {
                                                Toast.makeText(getActivity(), "Successfully updated", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(getActivity(), "Error! Not updated", Toast.LENGTH_LONG).show();

                                            }
                                        } else {
                                            boolean isUpdatedT = myDb.updateType(type, typeView);
                                            boolean isUpdatedM = myDb.updateMainTypeCol(type, typeView);
                                            if (isUpdatedT && isUpdatedM) {
                                                Toast.makeText(getActivity(), "Successfully updated", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(getActivity(), "Error! Not updated", Toast.LENGTH_LONG).show();

                                            }

                                        }
                                        getTypeFromDb();

                                    }
                                });

                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                                    }
                                });


                                builder.show();


                            } else {
                                Toast.makeText(getActivity(), "Can not alter", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                    builder.show();
                }
                return true;
            }

        });

    }

}
