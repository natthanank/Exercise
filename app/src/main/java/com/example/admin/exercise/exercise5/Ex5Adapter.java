package com.example.admin.exercise.exercise5;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.exercise.R;

import java.util.ArrayList;

public class Ex5Adapter extends RecyclerView.Adapter {

    ArrayList<String> dataSet;
    AppCompatActivity activity;

    public Ex5Adapter(ArrayList<String> dataSet, AppCompatActivity activity) {
        this.dataSet = dataSet;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ex5item, viewGroup, false);
        return new Ex5ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final String name = dataSet.get(i);
        ((Ex5ViewHolder) viewHolder).name.setText(name);

        ((Ex5ViewHolder) viewHolder).name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailFragment detailFragment = DetailFragment.newInstance(name);
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_frame, detailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
