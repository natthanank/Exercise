package com.example.admin.exercise.exercise5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.exercise.R;
import com.example.admin.exercise.exercise3.ThreeActivity;


public class FiveActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener,
                                                                DetailFragment.OnFragmentInteractionListener{

    public static final int LOGIN_RESULT = 2048;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        sp = getSharedPreferences("five", Context.MODE_PRIVATE);
        editor = sp.edit();
        String username = sp.getString("username", null);
        if (username == null) {
            Intent i = new Intent(FiveActivity.this, ThreeActivity.class);
            i.putExtra("request_code", LOGIN_RESULT);
            startActivityForResult(i, LOGIN_RESULT);
        } else {
            ListFragment listFragment = ListFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_frame, listFragment);
            transaction.commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOGIN_RESULT) {
            editor.putString("userID", data.getStringExtra("userID"));
            ListFragment listFragment = ListFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_frame, listFragment);
            transaction.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
