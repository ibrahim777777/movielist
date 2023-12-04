package com.example.movielist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView movielist;
    ProgressBar bar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movielist = findViewById(R.id.movielist);
        bar = findViewById(R.id.bar);


        Backendless.initApp(this, "08E80386-F8C9-372E-FF69-2C2C07F73C00", "20DEE612-F342-4718-8754-1A021AA9AD5C");
        Backendless.Data.of(movie.class).find(new AsyncCallback<List<movie>>() {
            @Override
            public void handleResponse(List<movie> response) {
                bar.setVisibility(View.INVISIBLE);
                ArrayList<String> names = new ArrayList<>();
                for (movie movie : response) {
                    names.add(movie.getName());
                    ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, names);
                    movielist.setAdapter(adapter);
                    movielist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(MainActivity.this,ShowDetails.class);
                            intent.putExtra("name",response.get(position).getName());
                            intent.putExtra("type",response.get(position).getType());
                            intent.putExtra("url",response.get(position).getUrl());
                            startActivity(intent);
                        }
                    });

                }


            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.moviemenu, menu);
        return true;
    }

    public void descinding(MenuItem item) {
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setSortBy( "name desc");
        Backendless.Data.of(movie.class).find(queryBuilder,new AsyncCallback<List<movie>>() {
            @Override
            public void handleResponse(List<movie> response) {
                bar.setVisibility(View.INVISIBLE);
                ArrayList<String> names = new ArrayList<>();
                for (movie movie : response) {
                    names.add(movie.getName());
                    ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, names);
                    movielist.setAdapter(adapter);

                }


            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

    }

    public void ascinding(MenuItem item) {
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setSortBy( "name");
        Backendless.Data.of(movie.class).find(queryBuilder,new AsyncCallback<List<movie>>() {
            @Override
            public void handleResponse(List<movie> response) {
                bar.setVisibility(View.INVISIBLE);
                ArrayList<String> names = new ArrayList<>();
                for (movie movie : response) {
                    names.add(movie.getName());
                    ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, names);
                    movielist.setAdapter(adapter);

                }


            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

    }

}



