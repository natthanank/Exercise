package com.example.admin.exercise.exercise4;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.exercise.R;
import com.example.admin.exercise.exercise3.ThreeActivity;

public class FourActivity extends AppCompatActivity {

    Button loginBtn;
    public static int LOGIN_RESULT = 1024;
    TextView userID, loginResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FourActivity.this, ThreeActivity.class);
                i.putExtra("request_code", LOGIN_RESULT);
                startActivityForResult(i, LOGIN_RESULT);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        userID = findViewById(R.id.userID);
        loginResult = findViewById(R.id.loginResult);
        if (requestCode == LOGIN_RESULT) {
            userID.setText("User ID : " + data.getStringExtra("userID"));
            loginResult.setText("Login Result: " + data.getStringExtra("result"));
        }
    }
}
