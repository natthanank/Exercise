package com.example.admin.exercise.exercise8;

import android.app.ActionBar;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.admin.exercise.R;

public class EightActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener
        , SecondFragment.OnFragmentInteractionListener,  TabFragment.OnFragmentInteractionListener
        , Second2Fragment.OnFragmentInteractionListener, Second3Fragment.OnFragmentInteractionListener{

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);

        TabFragment tabFragment = TabFragment.newInstance(null, null);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_frame, tabFragment);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
