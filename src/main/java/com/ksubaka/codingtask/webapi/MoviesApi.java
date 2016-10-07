package com.ksubaka.codingtask.webapi;

import org.json.JSONArray;
import org.json.JSONObject;

interface MoviesApi {
    String getSingleMovieUriTemplate();

    String getMoviesListUriTemplate();

    String getMovieId(JSONObject jsonObject);

    JSONArray getMoviesJsonList(String response) throws MovieNotFoundException;

    String getMovieTitle(JSONObject movieJsonObject);

    String getMovieYear(JSONObject movieJsonObject);

    String getMovieDirector(JSONObject movieJsonObject);

    boolean movieNotFound(JSONObject movieJsonObject);
}
