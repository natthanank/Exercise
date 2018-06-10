package com.example.admin.exercise.exercise9;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.admin.exercise.R;

public class Ex9ViewHolder extends RecyclerView.ViewHolder {

    TextView userText;
    public Ex9ViewHolder(@NonNull View itemView) {
        super(itemView);

        userText = itemView.findViewById(R.id.userText);
    }
}
