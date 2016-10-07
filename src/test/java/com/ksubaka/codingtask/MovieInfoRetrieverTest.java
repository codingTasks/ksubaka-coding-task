package com.ksubaka.codingtask;

import com.ksubaka.codingtask.domain.MovieInfo;
import com.ksubaka.codingtask.webapi.MovieNotFoundException;
import com.ksubaka.codingtask.webapi.MoviesApiParser;
import com.ksubaka.codingtask.webapi.TestMoviesApi;
import com.ksubaka.codingtask.webapi.connector.TestApiConnector;
import com.ksubaka.codingtask.webapi.connector.WebApiConnector;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class MovieInfoRetrieverTest {
    private WebApiConnector testWebApiConnector;
    private MovieInfoRetriever movieInfoRetriever;

    @Before
    public void init() {
        testWebApiConnector = new TestApiConnector();
        MoviesApiParser moviesApi = new MoviesApiParser(new TestMoviesApi());
        movieInfoRetriever = new MovieInfoRetriever(testWebApiConnector, moviesApi);
    }

    @Test
    public void shouldReturnListOfSevenMovies() throws Exception {
        List<MovieInfo> movieInfos = movieInfoRetriever.retrieve(TestConstants.SHREK);

        assertThat(movieInfos.size(), is(equalTo(7)));

        assertThat(movieInfos.get(0).getTitle(), is(equalTo("Shrek")));
        assertThat(movieInfos.get(0).getYear(), is(equalTo("2001")));
        assertThat(movieInfos.get(0).getDirector(), is(equalTo("Andrew Adamson, Vicky Jenson")));
    }

    @Test(expected = MovieNotFoundException.class)
    public void shouldThrowExceptionWhenMovieNotFound() throws Exception {
        movieInfoRetriever.retrieve(TestConstants.NO_EXIST);
    }
}
