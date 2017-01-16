package com.example.sharafievp.filmss.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sharafievp.filmss.Activity.FilmDetails;
import com.example.sharafievp.filmss.Other.Films;
import com.example.sharafievp.filmss.R;
import com.squareup.picasso.Picasso;

/**
 * Адаптер для RecyclerView
 */

public class Adapter extends RecyclerView.Adapter<Adapter.FilmsViewHolder> {

    static class FilmsViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView genres;
        TextView age;
        ImageView image;

        FilmsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_film);
            genres = (TextView) itemView.findViewById(R.id.genres);
            age = (TextView) itemView.findViewById(R.id.age);
            image = (ImageView) itemView.findViewById(R.id.poster);
        }
    }

    private Films[] filmses;
    private Activity main;
    private Context context;

    public Adapter(Activity main, Context context, Films[] filmses) {

        this.filmses = filmses;
        this.main = main;
        this.context = context;
        Intent intent = new Intent(context, FilmDetails.class);
    }

    @Override
    public FilmsViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.my_item, viewGroup, false);
        return new FilmsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FilmsViewHolder personViewHolder, int i) {
        personViewHolder.title.setText(filmses[i].getTitle());
        personViewHolder.genres.setText(filmses[i].getGenres());
        personViewHolder.age.setText(filmses[i].getAge_restriction());
        Picasso.with(context)
                .load(filmses[i].getPoster())
                .into(personViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return filmses.length;
    }
}