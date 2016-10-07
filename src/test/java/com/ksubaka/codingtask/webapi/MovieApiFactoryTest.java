package com.ksubaka.codingtask.webapi;

import com.ksubaka.codingtask.TestConstants;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class MovieApiFactoryTest {
    @Test
    public void shouldReturnOMDBMovieApi() throws Exception {
        MoviesApi movieApi = MovieApiFactory.getMovieApi(TestConstants.OMDB);
        assertThat(movieApi, instanceOf(OMDBMoviesApi.class));
    }

    @Test
    public void shouldReturnBechdelMovieApi() throws Exception {
        MoviesApi movieApi = MovieApiFactory.getMovieApi(TestConstants.BECHDEL);
        assertThat(movieApi, instanceOf(BechdelMoviesApi.class));
    }
}