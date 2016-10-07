package com.ksubaka.codingtask.webapi;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OMDBMoviesApiTest {
    private static final String TITLE_VALUE = "Shrek";
    private static final String YEAR_VALUE = "2001";
    private static final String TITLE = "Title";
    private static final String ID = "imdbID";
    private static final String YEAR = "Year";
    private static final String DERECTOR = "Director";
    private static final String ID_VALUE = "tt0126029";
    private static final String DIRECTOR_VALUE = "N/A";

    private static Map<String, String> map = new HashMap<>();
    private static JSONObject jsonObject;
    private OMDBMoviesApi omdbMoviesApi = new OMDBMoviesApi();

    @BeforeClass
    public static void setUp() throws Exception {
        map.put(ID, ID_VALUE);
        map.put(TITLE, TITLE_VALUE);
        map.put(YEAR, YEAR_VALUE);
        map.put(DERECTOR, DIRECTOR_VALUE);

        jsonObject = new JSONObject(map);
    }

    @Test
    public void shouldReturnMovieId() throws Exception {
        String movieId = omdbMoviesApi.getMovieId(jsonObject);
        assertThat(BechdelMoviesApi.class.getName(), movieId, is(equalTo(ID_VALUE)));
    }

    @Test
    public void shouldReturnMovieYear() throws Exception {
        String movieYear = omdbMoviesApi.getMovieYear(jsonObject);
        assertThat(movieYear, is(equalTo(YEAR_VALUE)));
    }

    @Test
    public void shouldReturnMovieTitle() throws Exception {
        String movieTitle = omdbMoviesApi.getMovieTitle(jsonObject);
        assertThat(movieTitle, is(equalTo(TITLE_VALUE)));
    }

    @Test
    public void shouldReturnMovieDirector() throws Exception {
        String movieDirector = omdbMoviesApi.getMovieDirector(jsonObject);
        assertThat(movieDirector, is(equalTo(DIRECTOR_VALUE)));
    }

    @Test
    public void shouldReturnMoviesJsonList() throws Exception {
        String response = getShrekMoviesListResponse();
        JSONArray moviesJsonList = omdbMoviesApi.getMoviesJsonList(response);

        assertThat(moviesJsonList.length(), is(equalTo(7)));
        assertThat(moviesJsonList.getJSONObject(0).getString(ID), is(equalTo(ID_VALUE)));
    }

    @Test(expected = MovieNotFoundException.class)
    public void shouldThrowExceptionWhenMovieNotFound() throws Exception {
        String response = getErrorResponse();
        omdbMoviesApi.getMoviesJsonList(response);
    }

    private String getErrorResponse() {
        return "{\"Response\":\"False\",\"Error\":\"Movie not found!\"}";
    }

    private String getShrekMoviesListResponse() {
        return "{\"Search\":[" +
                "{\"imdbID\":\"tt0126029\"}," +
                "{\"imdbID\":\"tt0298148\",}," +
                "{\"imdbID\":\"tt0413267\",}," +
                "{\"imdbID\":\"tt0892791\"}," +
                "{\"imdbID\":\"tt0897387\"}," +
                "{\"imdbID\":\"tt0360985\"}," +
                "{\"imdbID\":\"tt3107884\"}]}";
    }

}