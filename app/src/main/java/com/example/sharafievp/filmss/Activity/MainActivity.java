package com.example.sharafievp.filmss.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.sharafievp.filmss.Adapters.Adapter;
import com.example.sharafievp.filmss.Adapters.JsonAdapter;
import com.example.sharafievp.filmss.Adapters.RecyclerItemClickListener;
import com.example.sharafievp.filmss.Other.Films;
import com.example.sharafievp.filmss.Other.SpacesItemDecoration;
import com.example.sharafievp.filmss.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Pavel
 */

public class MainActivity extends AppCompatActivity {

    Context context;
    ProgressBar progressBar;
    RecyclerView filmView;
    Activity main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_layout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        main = this;
        context=this;
        new Parser().execute();
    }

    /**
     * Класс для обработки Json
     */
    public class Parser extends AsyncTask<Void, Void, Films[]> {

        private final long date=System.currentTimeMillis()/1000L;
        private final String FILMS_URL = "https://kudago.com/public-api/v1.3/movies/?fields=title,poster,genres,body_text,age_restriction&location=msk&text_format=text&page_size=10&actual_since="+date;
        private String resultJson;
        private JSONObject jsonObject;
        private JSONArray js;
        private Films[] films;
        InputStream inputStream;
        Adapter adapter;
        @Override
        protected Films[] doInBackground(Void... params) {
            try {
                URL url = new URL(FILMS_URL);
                URLConnection http = url.openConnection();
                inputStream = http.getInputStream();
                http.connect();
                StringBuilder buffer = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                resultJson = buffer.toString();
                jsonObject=new JSONObject(resultJson);
                js=jsonObject.getJSONArray("results");
                films=JsonAdapter.deserealize(js.toString(), films);
                inputStream.close();
            }
            catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return films;
        }

        @Override
        protected void onPostExecute(final Films[] films) {
            super.onPostExecute(films);
            setContentView(R.layout.activity_main);
            filmView = (RecyclerView) findViewById(R.id.FilmsList);
            filmView.setLayoutManager(new LinearLayoutManager(main));
            adapter = new Adapter(main, context, films);
            filmView.setAdapter(adapter);
            filmView.addItemDecoration(new SpacesItemDecoration(50));
            filmView.setHasFixedSize(true);

            /**
             * Обработка нажатий
             */
            filmView.addOnItemTouchListener(
                    new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {

                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(MainActivity.this, FilmDetails.class);
                            String filmString ="";
                            try {
                                filmString=JsonAdapter.serealize(films[position]);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            intent.putExtra("films", filmString);       //передаём объект как json-строку
                            startActivity(intent);
                        }
                    })
            );
        }
    }

    public void onProgressClick(View view){
        Toast toast=Toast.makeText(this, "loading...", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}
