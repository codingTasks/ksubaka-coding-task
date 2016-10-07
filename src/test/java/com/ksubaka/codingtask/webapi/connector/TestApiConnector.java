package com.ksubaka.codingtask.webapi.connector;

import com.ksubaka.codingtask.TestConstants;

public class TestApiConnector implements WebApiConnector {

    @Override
    public String getResponse(String uri) {
        if (moviesListRequest(uri)) {
            return getShrekMoviesListResponse();
        } else if (movieNotExistsRequest(uri))
            return getMovieNoExistResponse();
        else if (singleMovieRequest(uri)) {
            return getSingleMovieResponse();
        }

        return getEmptyResponse();
    }

    private String getEmptyResponse() {
        return "[]";
    }

    private boolean singleMovieRequest(String uri) {
        return uri.startsWith(TestConstants.SINGLE_MOVIE_TEMPLATE_URI);
    }

    private boolean movieNotExistsRequest(String uri) {
        return String.format("%s%s", TestConstants.SINGLE_MOVIE_TEMPLATE_URI, TestConstants.NO_EXIST).equals(uri);
    }

    private boolean moviesListRequest(String uri) {
        return String.format("%s%s", TestConstants.MOVIE_LIST_TEMPLATE_URI, TestConstants.SHREK).equals(uri);
    }

    private String getMovieNoExistResponse() {
        return "{\"Error\":\"Movie not found\"}";
    }

    private String getSingleMovieResponse() {
        return "{\"Title\":\"Shrek\",\"Year\":\"2001\",\"Director\":\"Andrew Adamson, Vicky Jenson\"}";
    }

    private String getShrekMoviesListResponse() {
        return "[" +
                "{\"imdbID\":\"tt0126029\"}," +
                "{\"imdbID\":\"tt0298148\",}," +
                "{\"imdbID\":\"tt0413267\",}," +
                "{\"imdbID\":\"tt0892791\"}," +
                "{\"imdbID\":\"tt0897387\"}," +
                "{\"imdbID\":\"tt0360985\"}," +
                "{\"imdbID\":\"tt3107884\"}]";
    }
}
