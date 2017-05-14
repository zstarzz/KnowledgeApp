package com.example.zain.knowledgetest;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();




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

    }



    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}
