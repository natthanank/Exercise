package com.example.admin.exercise.exercise7;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.exercise.R;

public class Ex7ViewHolder extends RecyclerView.ViewHolder {

    TextView name;

    public Ex7ViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
    }
}
