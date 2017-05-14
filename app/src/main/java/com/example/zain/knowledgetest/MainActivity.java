package com.example.zain.knowledgetest;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    Bundle bundleSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bundleSet = new Bundle();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fragmentManager = getSupportFragmentManager();
        fragment = new SearchFragment();
        transaction = fragmentManager.beginTransaction();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 2909);
            } else {
                transaction.replace(R.id.rootLayout, fragment).commit();

            }
        } else {
            transaction.replace(R.id.rootLayout, fragment).commit();

        }



        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.menu_addPOI:
                        fragment = new addPoiFragment();
                        break;
                    case R.id.menu_type:
                        fragment = new TypeFragment();
                        break;
                    case R.id.menu_postcode:
                        fragment = new PostcodeFragment();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.rootLayout, fragment).commit();

                return true;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 2909: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission", "Granted");
                    transaction.replace(R.id.rootLayout, fragment).commit();


                } else {
                    Log.e("Permission", "Denied");

                    Intent loopIntent = new Intent(MainActivity.this, SplashScreen.class);
                    startActivity(loopIntent);
                    Toast.makeText(MainActivity.this, "Permission Required !", Toast.LENGTH_LONG).show();

                }
                return;
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp(){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String currentFrag = prefs.getString("cFrag", "no id"); //no id: default value

        if(currentFrag == "kill"){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }

        if(currentFrag == "imageFullView"){
            String data = prefs.getString("Row_ID", "no id"); //no id: default value
            String searchString = prefs.getString("searchTerm", "no id"); //no id: default value

            fragment = new ImageViewFragment();
            bundleSet = new Bundle();
            bundleSet.putString("ImgID", data);
            bundleSet.putString("searchTerm", searchString);
            fragment.setArguments(bundleSet);

            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.rootLayout, fragment).commit();

        }


        if(currentFrag == "imgUpload"){

            String data = prefs.getString("Row_ID", "no id"); //no id: default value
            String searchString = prefs.getString("searchTerm", "no id"); //no id: default value


            fragment = new ImageViewFragment();
            bundleSet = new Bundle();
            bundleSet.putString("ImgID", data);
            bundleSet.putString("searchTerm", searchString);
            fragment.setArguments(bundleSet);

            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.rootLayout, fragment).commit();

        }


        if(currentFrag == "View"){

            fragment = new SearchFragment();

            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.rootLayout, fragment).commit();

        }

        if(currentFrag == "imageView"){

            String data = prefs.getString("searchTerm", "no id"); //no id: default value

            fragment = new ViewFragment();
            bundleSet = new Bundle();
            bundleSet.putString("searchString", data);
            fragment.setArguments(bundleSet);

            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.rootLayout, fragment).commit();
        }

        if(currentFrag == "updateRow"){

            String data = prefs.getString("searchTerm", "no id"); //no id: default value

            fragment = new ViewFragment();
            bundleSet = new Bundle();
            bundleSet.putString("searchString", data);
            fragment.setArguments(bundleSet);

            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.rootLayout, fragment).commit();
        }


        return true;
    }
}
