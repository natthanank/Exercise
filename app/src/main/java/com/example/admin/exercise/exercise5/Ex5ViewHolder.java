package com.example.admin.exercise.exercise5;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.exercise.R;

public class Ex5ViewHolder extends RecyclerView.ViewHolder {

    TextView name;

    public Ex5ViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
    }
}
