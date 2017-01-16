package com.example.sharafievp.filmss.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sharafievp.filmss.Adapters.JsonAdapter;
import com.example.sharafievp.filmss.Other.Films;
import com.example.sharafievp.filmss.R;
import com.squareup.picasso.Picasso;

/**
 * Вторая активность с подробной информацией о фильме
 */

public class FilmDetails extends Activity implements Runnable{
    Films film=new Films();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Thread thread=new Thread(this);
        thread.run();

        TextView genres, age, title, body;
        ImageView big = (ImageView) findViewById(R.id.imageViewD);
        genres = (TextView) findViewById(R.id.genresD);
        age = (TextView) findViewById(R.id.ageD);
        title = (TextView) findViewById(R.id.titleD);
        body = (TextView) findViewById(R.id.bodyD);

        genres.setText(film.getGenres());
        age.setText(film.getAge_restriction());
        title.setText(film.getTitle());
        body.setText(film.getBody_text());
        Picasso.with(getApplicationContext())
                .load(film.getPoster())
                .into(big);
    }

    @Override
    public void run() {
        String filmsString = getIntent().getStringExtra("films");
        film= JsonAdapter.deserealize(filmsString, film);
    }
}
