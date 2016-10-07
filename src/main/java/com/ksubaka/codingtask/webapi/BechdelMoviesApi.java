package com.ksubaka.codingtask.webapi;

import org.json.JSONArray;
import org.json.JSONObject;

class BechdelMoviesApi implements MoviesApi {
    private static final String TITLE = "title";
    private static final String YEAR = "year";
    private static final String ID = "imdbid";
    private static final String MOVIES_LIST_URI_TEMPLATE = "http://bechdeltest.com/api/v1/getMoviesByTitle?title=";
    private static final String SINGLE_MOVIE_URI_TEMPLATE = "http://bechdeltest.com/api/v1/getMovieByImdbId?imdbid=";
    private static final String STATUS = "status";
    private static final String STATUS_404 = "404";

    BechdelMoviesApi() {
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
        return jsonObject.getString(ID);
    }

    @Override
    public JSONArray getMoviesJsonList(String response) throws MovieNotFoundException {
        JSONArray jsonArray = new JSONArray(response);
        if (jsonArray.length() == 0)
            throw new MovieNotFoundException();

        return jsonArray;
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
        return "N/A";
    }

    @Override
    public boolean movieNotFound(JSONObject movieJsonObject) {
        return movieJsonObject.has(STATUS) && movieJsonObject.get(STATUS).equals(STATUS_404);
    }
}
