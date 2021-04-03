package com.example.flashapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView answer, question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answer = (TextView) findViewById(R.id.answer);
        answer.setVisibility(View.INVISIBLE);
        question = (TextView) findViewById(R.id.question);
        final ImageButton add = (ImageButton) findViewById(R.id.addButton);
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View answerSideView = findViewById(R.id.answer);
                // get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;
                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);
                answer.setVisibility(View.VISIBLE);
                question.setVisibility(View.INVISIBLE);

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                answer.setVisibility(View.INVISIBLE);
                question.setVisibility(View.VISIBLE);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAddCardActivity();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data!=null) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String newQuestion = data.getExtras().getString("question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String newAnswer = data.getExtras().getString("answer");
            answer.setText(newAnswer);
            question.setText(newQuestion);
        }
    }

    //@android.support.annotation.RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    public void launchAddCardActivity() {
        Intent i = new Intent(MainActivity.this, AddCardActivity.class);
        this.startActivityForResult(i, 100);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

}
