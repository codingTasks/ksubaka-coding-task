package com.ksubaka.codingtask.webapi;

import com.ksubaka.codingtask.TestConstants;
import com.ksubaka.codingtask.domain.MovieInfo;
import com.ksubaka.codingtask.domain.MovieStub;
import com.ksubaka.codingtask.webapi.connector.TestApiConnector;
import com.ksubaka.codingtask.webapi.connector.WebApiConnector;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MoviesApiParserTest {
    private final TestMoviesApi moviesApi = new TestMoviesApi();
    private MoviesApiParser moviesApiParser = new MoviesApiParser(moviesApi);
    private WebApiConnector testWebApiConnector = new TestApiConnector();

    @Test
    public void shouldReturnMovieStubsList() throws MovieNotFoundException {
        String response = testWebApiConnector.getResponse(moviesApiParser.getMoviesListUri(TestConstants.SHREK));
        List<MovieStub> moviesList = moviesApiParser.getMovieStubsList(response);

        assertThat(moviesList.size(), is(equalTo(7)));
        assertThat(moviesList.get(0).getId(), is(equalTo("tt0126029")));
        assertThat(moviesList.get(1).getId(), is(equalTo("tt0298148")));
    }

    @Test
    public void shouldReturnMovieInfo() throws MovieNotFoundException {
        String response = testWebApiConnector.getResponse(moviesApiParser.getSingleMovieUri("tt0126029"));
        MovieInfo movieInfo = moviesApiParser.getMovieInfo(response, "tt0126029");

        assertThat(movieInfo.getTitle(), is(equalTo(TestConstants.SHREK)));
        assertThat(movieInfo.getYear(), is(equalTo("2001")));
        assertThat(movieInfo.getDirector(), is(equalTo("Andrew Adamson, Vicky Jenson")));
    }

    @Test
    public void shouldReturnSingleMovieUri() throws Exception {
        String movieId = "tt0126029";
        String singleMovieUri = moviesApiParser.getSingleMovieUri(movieId);
        assertThat(singleMovieUri, is(equalTo(String.format("%s%s", TestConstants.SINGLE_MOVIE_TEMPLATE_URI, movieId)
        )));
    }

    @Test
    public void shouldReturnMoviesListUri() throws Exception {
        String movieTitle = TestConstants.SHREK;
        String moviesListUri = moviesApiParser.getMoviesListUri(movieTitle);
        assertThat(moviesListUri, is(equalTo(String.format("%s%s", TestConstants.MOVIE_LIST_TEMPLATE_URI, movieTitle)
        )));
    }

    @Test(expected = MovieNotFoundException.class)
    public void shouldThrowExceptionWhenMovieIdNotFound() throws Exception {
        String response = testWebApiConnector.getResponse(moviesApiParser.getSingleMovieUri(TestConstants.NO_EXIST));
        moviesApiParser.getMovieInfo(response, TestConstants.NO_EXIST);
    }
}