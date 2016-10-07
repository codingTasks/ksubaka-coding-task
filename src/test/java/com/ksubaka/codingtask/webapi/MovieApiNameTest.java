package com.ksubaka.codingtask.webapi;

import com.ksubaka.codingtask.TestConstants;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class MovieApiNameTest {

    @Test
    public void shouldReturnOMDBMovieApiName() throws Exception {
        assertThat(MovieApiName.getValue(TestConstants.OMDB), is(equalTo(MovieApiName.OMDB)));
    }

    @Test
    public void shouldReturnBechdelMovieApiName() throws Exception {
        assertThat(MovieApiName.getValue(TestConstants.BECHDEL), is(equalTo(MovieApiName.BECHDEL)));
    }

    @Test(expected = MovieApiFactory.UnrecognizedMovieApiException.class)
    public void shouldThrowExceptionWhenTryingToRetrieveNotExistingValue() throws Exception {
        MovieApiName.getValue(TestConstants.NO_EXIST);
    }

}