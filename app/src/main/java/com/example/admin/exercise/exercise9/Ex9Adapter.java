package com.example.admin.exercise.exercise9;

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

public class Ex9Adapter extends RecyclerView.Adapter {

    private ArrayList<User> users;
    private AppCompatActivity activity;

    public Ex9Adapter(ArrayList<User> users, AppCompatActivity activity) {
        this.users = users;
        this.activity = activity;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ex9item, viewGroup, false);
        return new Ex9ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        User user = users.get(i);
        ((Ex9ViewHolder) viewHolder).userText.setText(user.getName() + ", " + user.getAge());
        ((Ex9ViewHolder) viewHolder).userText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListFragment.setSelectedRow(i);
                Bundle bundle = new Bundle();
                bundle.putParcelable("user", user);
                DetailFragment detailFragment = DetailFragment.newInstance(null, null);
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
        return users.size();
    }
}
