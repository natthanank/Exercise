package com.example.admin.exercise.exercise6;

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
import com.example.admin.exercise.exercise5.FiveActivity;
import com.example.admin.exercise.exercise5.ListFragment;

public class SixActivity extends AppCompatActivity implements TabFragment.OnFragmentInteractionListener,
                                                                FirstFragment.OnFragmentInteractionListener,
                                                                SecondFragment.OnFragmentInteractionListener,
                                                                ThirdFragment.OnFragmentInteractionListener{

    public static final int LOGIN_RESULT = 3072;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);

        sp = getSharedPreferences("five", Context.MODE_PRIVATE);
        editor = sp.edit();
        String username = sp.getString("username", null);
        if (username == null) {
            Intent i = new Intent(SixActivity.this, ThreeActivity.class);
            i.putExtra("request_code", LOGIN_RESULT);
            startActivityForResult(i, LOGIN_RESULT);
        } else {
            TabFragment tabFragment = TabFragment.newInstance(null, null);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_frame, tabFragment);
            transaction.commit();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOGIN_RESULT) {
            editor.putString("userID", data.getStringExtra("userID"));
            TabFragment tabFragment = TabFragment.newInstance(null, null);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_frame, tabFragment);
            transaction.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
