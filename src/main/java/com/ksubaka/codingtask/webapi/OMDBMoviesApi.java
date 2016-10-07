package com.ksubaka.codingtask.webapi;

import org.json.JSONArray;
import org.json.JSONObject;

class OMDBMoviesApi implements MoviesApi {
    private static final String SEARCH = "Search";
    private static final String IMDB_ID = "imdbID";
    private static final String TITLE = "Title";
    private static final String YEAR = "Year";
    private static final String ERROR = "Error";
    private static final String DIRECTOR = "Director";

    private static final String MOVIES_LIST_URI_TEMPLATE = "http://www.omdbapi.com/?s=";
    private static final String SINGLE_MOVIE_URI_TEMPLATE = "http://www.omdbapi.com/?i=";

    OMDBMoviesApi() {
    }

    @Override
    public String getSingleMovieUriTemplate() {
        return SINGLE_MOVIE_URI_TEMPLATE;
    }

    @Override
    public String getMoviesListUriTemplate() {
        return MOVIES_LIST_URI_TEMPLATE;
    }

    @Override
    public String getMovieId(JSONObject jsonObject) {
        return jsonObject.getString(IMDB_ID);
    }

    @Override
    public JSONArray getMoviesJsonList(String response) throws MovieNotFoundException {
        JSONObject jsonObject = new JSONObject(response);
        if (movieNotFound(jsonObject))
            throw new MovieNotFoundException(jsonObject.getString(ERROR));

        return jsonObject.getJSONArray(SEARCH);
    }

    @Override
    public String getMovieTitle(JSONObject movieJsonObject) {
        return movieJsonObject.getString(TITLE);
    }

    @Override
    public String getMovieYear(JSONObject movieJsonObject) {
        return movieJsonObject.getString(YEAR);
    }

    @Override
    public String getMovieDirector(JSONObject movieJsonObject) {
        return movieJsonObject.getString(DIRECTOR);
    }

    @Override
    public boolean movieNotFound(JSONObject movieJsonObject) {
        return movieJsonObject.has(ERROR);
    }
}
