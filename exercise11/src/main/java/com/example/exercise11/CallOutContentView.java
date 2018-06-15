package com.example.exercise11;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CallOutContentView extends FrameLayout {

    ArrayList<MyAttributes> attributes;
    TextView countText,
            row1Head, row1Text,
            row2Head, row2Text,
            row3Head, row3Text,
            row4Head, row4Text,
            backBtn, nextBtn;
    int position = 0;

    public CallOutContentView(@NonNull Context context) {
        super(context);
        bindView();
    }

    private void bindView() {
        inflate(getContext(), R.layout.custom_callout, this);
        countText = findViewById(R.id.countText);
        row1Head = findViewById(R.id.row_1_head);
        row1Text = findViewById(R.id.row_1_text);
        row2Head = findViewById(R.id.row_2_head);
        row2Text = findViewById(R.id.row_2_text);
        row3Head = findViewById(R.id.row_3_head);
        row3Text = findViewById(R.id.row_3_text);
        row4Head = findViewById(R.id.row_4_head);
        row4Text = findViewById(R.id.row_4_text);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }
        });
    }

    public void setAttributes(ArrayList<MyAttributes> attributes) {
        this.attributes = attributes;
        setText();
    }

    private void setText() {
        countText.setText((position + 1) + " of " + attributes.size());
        if (attributes.get(position).getName().equals("DemoGDB.DBO.MontgomeryParcelsOwners")) {
            row1Head.setText("objectid");
            row2Head.setText("owner_name");
            row3Head.setText("own_id");
            row4Head.setText("zoning_s");
        } else {
            row1Head.setText("objectid");
            row2Head.setText("shape_star");
            row3Head.setText("shape_stle");
            row4Head.setText("res");
        }

        row1Text.setText(attributes.get(position).getRow1());
        row2Text.setText(attributes.get(position).getRow2());
        row3Text.setText(attributes.get(position).getRow3());
        row4Text.setText(attributes.get(position).getRow4());


    }

    public void back() {
        if (position >= 1) {
            position--;
            setText();
        }
    }

    public void next() {
        if (position < attributes.size() - 1) {
            position++;
            setText();
        }
    }
}
