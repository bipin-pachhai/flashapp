package com.example.flashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        final EditText question = (EditText) findViewById(R.id.newQuestion);
        final EditText answer = (EditText) findViewById(R.id.newAnswer);
        final ImageButton cancel = (ImageButton) findViewById(R.id.cancel);
        final ImageButton save = (ImageButton) findViewById(R.id.save);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().putExtra("question", question.getText().toString());
                getIntent().putExtra("answer", answer.getText().toString());
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
    }
}