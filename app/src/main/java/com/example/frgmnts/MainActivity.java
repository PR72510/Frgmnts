package com.example.frgmnts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button Load, Load1;
    Button Hide,Replace, Show, Remove;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Load = findViewById(R.id.Btn_1);
        Load1 = findViewById(R.id.Btn_6);
        Replace = findViewById(R.id.Btn_2);
        Hide = findViewById(R.id.Btn_3);
        Show = findViewById(R.id.Btn_4);
        Remove = findViewById(R.id.Btn_5);
        fragmentManager =getSupportFragmentManager();

        Log.i(TAG, "onCreate: Entered");

        Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlankFragment frg = new BlankFragment();
                addFragment(frg);
            }
        });

        Load1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlankFragment2 frg = new BlankFragment2();
                addFragment(frg);
            }
        });

        Replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment();
            }
        });

        Hide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                hideFragment();
            }
        });

        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment();
            }
        });

        Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    removeFragment();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: Entered");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: Entered");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: Entered");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: Entered");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: Entered");
    }

    private void addFragment(Fragment frg){

        fragmentTransaction = fragmentManager.beginTransaction();
//        BlankFragment blankFragment = new BlankFragment();
        fragmentTransaction.add(R.id.fragment_container, frg);
        fragmentTransaction.addToBackStack("fragmentStack1");
        fragmentTransaction.commit();
    }

    private void replaceFragment(){

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        fragmentTransaction = fragmentManager.beginTransaction();

        BlankFragment frg1 = new BlankFragment();
        BlankFragment2 frg2 = new BlankFragment2();

        if(fragment instanceof BlankFragment){
            fragmentTransaction.replace(R.id.fragment_container,frg2);
            fragmentTransaction.commit();
        }
        else if (fragment instanceof BlankFragment2){
            fragmentTransaction.replace(R.id.fragment_container, frg1);
            fragmentTransaction.commit();
        }
    }

    private void removeFragment(){
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    private void hideFragment(){
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_out, android.R.animator.fade_in)
                        .hide(fragment).commit();
    }

    private void showFragment(){
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .show(fragment).commit();

    }
}
