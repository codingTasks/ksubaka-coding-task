package com.ksubaka.codingtask;

import com.ksubaka.codingtask.domain.MovieInfo;
import com.ksubaka.codingtask.domain.MovieStub;
import com.ksubaka.codingtask.webapi.MovieNotFoundException;
import com.ksubaka.codingtask.webapi.MoviesApiParser;
import com.ksubaka.codingtask.webapi.connector.WebApiConnector;

import java.util.ArrayList;
import java.util.List;

class MovieInfoRetriever {
    private final WebApiConnector webApiConnector;
    private MoviesApiParser moviesApi;

    MovieInfoRetriever(WebApiConnector webApiConnector, MoviesApiParser moviesApi) {
        this.webApiConnector = webApiConnector;
        this.moviesApi = moviesApi;
    }

    List<MovieInfo> retrieve(String movieTitle) throws MovieNotFoundException {
        List<MovieStub> movieStubs = getMoviesList(movieTitle);
        List<MovieInfo> movieInfos = new ArrayList<>();

        for (MovieStub movieStub : movieStubs) {
            movieInfos.add(getMovieInfo(movieStub));
        }

        return movieInfos;
    }

    private List<MovieStub> getMoviesList(String movieTitle) throws MovieNotFoundException {
        String response = webApiConnector.getResponse(moviesApi.getMoviesListUri(movieTitle));
        return moviesApi.getMovieStubsList(response);
    }

    private MovieInfo getMovieInfo(MovieStub movieStub) throws MovieNotFoundException {
        String movieId = movieStub.getId();
        String response = webApiConnector.getResponse(moviesApi.getSingleMovieUri(movieId));

        return moviesApi.getMovieInfo(response, movieId);
    }
}
