package com.ksubaka.codingtask.webapi;

import com.ksubaka.codingtask.TestConstants;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestMoviesApi implements MoviesApi {
    @Override
    public String getSingleMovieUriTemplate() {
        return TestConstants.SINGLE_MOVIE_TEMPLATE_URI;
    }

    @Override
    public String getMoviesListUriTemplate() {
        return TestConstants.MOVIE_LIST_TEMPLATE_URI;
    }

    @Override
    public String getMovieId(JSONObject jsonObject) {
        return jsonObject.getString("imdbID");
    }

    @Override
    public JSONArray getMoviesJsonList(String response) throws MovieNotFoundException {
        if (new JSONArray(response).length() == 0)
            throw new MovieNotFoundException();
        return new JSONArray(response);
    }

    @Override
    public String getMovieTitle(JSONObject movieJsonObject) {
        return movieJsonObject.getString("Title");
    }

    @Override
    public String getMovieYear(JSONObject movieJsonObject) {
        return movieJsonObject.getString("Year");
    }

    @Override
    public String getMovieDirector(JSONObject movieJsonObject) {
        return movieJsonObject.getString("Director");
    }

    @Override
    public boolean movieNotFound(JSONObject movieJsonObject) {
        return movieJsonObject.has("Error");
    }
}
