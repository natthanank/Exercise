package com.example.admin.exercise.exercise7;

import android.os.Bundle;
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

public class Ex7Adapter extends RecyclerView.Adapter {

    ArrayList<Comment> dataSet;
    AppCompatActivity activity;

    public Ex7Adapter(ArrayList<Comment> dataSet, AppCompatActivity activity) {
        this.dataSet = dataSet;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ex5item, viewGroup, false);
        return new Ex7ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final int id = dataSet.get(i).getId();
        ((Ex7ViewHolder) viewHolder).name.setText(Integer.toString(id));

        ((Ex7ViewHolder) viewHolder).name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("comment", dataSet.get(i));

                DetailFragment detailFragment = DetailFragment.newInstance(null);
                detailFragment.setArguments(bundle);
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
