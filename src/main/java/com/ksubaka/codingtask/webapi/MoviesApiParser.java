package com.ksubaka.codingtask.webapi;

import com.ksubaka.codingtask.domain.MovieInfo;
import com.ksubaka.codingtask.domain.MovieStub;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MoviesApiParser {
    private MoviesApi moviesApi;

    public MoviesApiParser(MoviesApi moviesApi) {
        this.moviesApi = moviesApi;
    }

    public String getSingleMovieUri(String id) {
        return String.format("%s%s", moviesApi.getSingleMovieUriTemplate(), id);
    }

    public String getMoviesListUri(String movieTitle) {
        return String.format("%s%s", moviesApi.getMoviesListUriTemplate(), movieTitle);
    }

    public List<MovieStub> getMovieStubsList(String response) throws MovieNotFoundException {
        List<MovieStub> movieStubs = new ArrayList<>();

        JSONArray movies = moviesApi.getMoviesJsonList(response);
        for (int i = 0; i < movies.length(); i++) {
            movieStubs.add(new MovieStub(moviesApi.getMovieId(movies.getJSONObject(i))));
        }

        return movieStubs;
    }

    public MovieInfo getMovieInfo(String response, String movieId) throws MovieNotFoundException {
        JSONObject movieJsonObject = new JSONObject(response);
        if (moviesApi.movieNotFound(movieJsonObject))
            throw new MovieNotFoundException(String.format("Movie with id: %s not found", movieId));

        String title = moviesApi.getMovieTitle(movieJsonObject);
        String year = moviesApi.getMovieYear(movieJsonObject);
        String director = moviesApi.getMovieDirector(movieJsonObject);

        return new MovieInfo(title, year, director);
    }
}
