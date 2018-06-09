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
                                                                ThirdFragment.OnFragmentInteractionListener,
                                                                Second2Fragment.OnFragmentInteractionListener,
                                                                Second3Fragment.OnFragmentInteractionListener{

    public static final int LOGIN_RESULT = 3072;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TabFragment tabFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);

        sp = getSharedPreferences("six", Context.MODE_PRIVATE);
        editor = sp.edit();
        String username = sp.getString("username", null);
        if (username == null) {
            Intent i = new Intent(SixActivity.this, ThreeActivity.class);
            i.putExtra("request_code", LOGIN_RESULT);
            startActivityForResult(i, LOGIN_RESULT);
        } else {
            tabFragment = TabFragment.newInstance(null, null);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_frame, tabFragment);
            transaction.commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        sp = getSharedPreferences("six", Context.MODE_PRIVATE);
        String username = sp.getString("username", null);
//        System.out.println(sp.getString("username", null));

        System.out.println("in Resume userID = " + sp.getString("username", null));

        if (username == null) {
            Intent i = new Intent(SixActivity.this, ThreeActivity.class);
            i.putExtra("request_code", LOGIN_RESULT);
            startActivityForResult(i, LOGIN_RESULT);
        } else {
            tabFragment = TabFragment.newInstance(null, null);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_frame, tabFragment);
            transaction.commit();
        }
    }

    public void logout() {
        editor.remove("username");
        editor.commit();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragmentManager.getFragments().get(0));
        transaction.commit();
        onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOGIN_RESULT) {
            editor.putString("username", data.getStringExtra("userID"));
            editor.commit();
            TabFragment tabFragment = TabFragment.newInstance(null, null);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_frame, tabFragment);
            transaction.commit();
        }
        System.out.println("in Result userID = " + sp.getString("username", null));

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
