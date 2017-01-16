package com.example.sharafievp.filmss.Adapters;

import com.example.sharafievp.filmss.Other.Films;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

/**
 *  Class for convert JSON
 */

public class JsonAdapter{

    private static String ggg;
    private static JSONObject jsonObject;

    /**
     * method to convert JSON into an object array
     * @param json - JSON string
     * @param films - Object array
     * @return Films array
     */
        public static Films[] deserealize (String json, Films[] films){
        Films film;
        try {
            JSONArray array = new JSONArray(json);
        films=new Films[array.length()];
        int i;
            for (i=0; i< array.length(); i++){
                film=new Films();
                jsonObject= array.getJSONObject(i);
                film.setTitle(jsonObject.getString("title"));
                String age=jsonObject.getString("age_restriction");
                String ageNotNull=age.replaceAll("[null]", "");
                film.setAge_restriction(ageNotNull);
                film.setBody_text(jsonObject.getString("body_text"));
                JSONObject poster=jsonObject.getJSONObject("poster");
                film.setPoster(poster.getString("image"));
                JSONArray genres=jsonObject.getJSONArray("genres");
                for(int j=0;j<genres.length();j++){
                    JSONObject genresObject = genres.getJSONObject(j);
                    if (j==0) ggg= genresObject.getString("name");
                    else      ggg+=", "+ genresObject.getString("name");
                }
                films[i]=film;
                film.setGenres(ggg);
            }} catch (JSONException e) {
            e.printStackTrace();
        }
        return films;
    }

    /**
     * method to convert JSON into an object
     * @param json - JSON string
     * @param films - Object
     * @return object film
     */
    public static Films deserealize (String json, Films films){
        try {
            jsonObject=new JSONObject(json);
            films.setTitle(jsonObject.getString("title"));
        films.setAge_restriction(jsonObject.getString("age_restriction"));
        films.setBody_text(jsonObject.getString("body_text"));
        films.setPoster(jsonObject.getString("poster"));
        films.setGenres(jsonObject.getString("genres"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return films;
    }

    /**
     * Method to convert object into an JSON format
     * @param film - serializable object
     * @return JSON string
     */
    public static String serealize(Films film) throws IOException {
            jsonObject=new JSONObject();
        try {
            jsonObject.put("title",film.getTitle());
            jsonObject.put("age_restriction", film.getAge_restriction());
            jsonObject.put("body_text", film.getBody_text());
            jsonObject.put("poster", film.getPoster());
            jsonObject.put("genres", film.getGenres());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
