package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ShowDetails extends AppCompatActivity {
    ImageView moviveimage;
    TextView movietext,movietype;
    ProgressBar imagebar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        movietext=findViewById(R.id.moviename);
        movietype=findViewById(R.id.movietype);
        moviveimage=findViewById(R.id.movieimage);
        imagebar=findViewById(R.id.imagebar);
        movietext.setText("name : "+getIntent().getStringExtra("name"));
        movietype.setText("type : "+getIntent().getStringExtra("type"));
        Picasso.get().load(getIntent().getStringExtra("url")).into(moviveimage, new Callback() {
            @Override
            public void onSuccess() {
                imagebar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}