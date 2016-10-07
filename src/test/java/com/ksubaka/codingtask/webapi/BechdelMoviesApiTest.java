package com.ksubaka.codingtask.webapi;

import com.ksubaka.codingtask.TestConstants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BechdelMoviesApiTest {
    private static final String YEAR_VALUE = "2001";
    private static final String TITLE = "title";
    private static final String YEAR = "year";
    private static final String ID = "imdbid";
    private static final String ID_VALUE = "tt0126029";
    private static final String DIRECTOR_VALUE = "N/A";
    private static Map<String, String> map = new HashMap<>();
    private static JSONObject jsonObject;
    private BechdelMoviesApi bechdelMoviesApi = new BechdelMoviesApi();

    @BeforeClass
    public static void setUp() throws Exception {
        map.put(ID, ID_VALUE);
        map.put(TITLE, TestConstants.SHREK);
        map.put(YEAR, YEAR_VALUE);
        jsonObject = new JSONObject(map);
    }

    @Test
    public void shouldReturnMovieId() throws Exception {
        String movieId = bechdelMoviesApi.getMovieId(jsonObject);
        assertThat(BechdelMoviesApi.class.getName(), movieId, is(equalTo(ID_VALUE)));
    }

    @Test
    public void shouldReturnMovieYear() throws Exception {
        String movieYear = bechdelMoviesApi.getMovieYear(jsonObject);
        assertThat(movieYear, is(equalTo(YEAR_VALUE)));
    }

    @Test
    public void shouldReturnMovieTitle() throws Exception {
        String movieTitle = bechdelMoviesApi.getMovieTitle(jsonObject);
        assertThat(movieTitle, is(equalTo(TestConstants.SHREK)));
    }

    @Test
    public void shouldReturnMovieDirector() throws Exception {
        String movieDirector = bechdelMoviesApi.getMovieDirector(jsonObject);
        assertThat(movieDirector, is(equalTo(DIRECTOR_VALUE)));
    }

    @Test
    public void shouldReturnMoviesJsonList() throws Exception {
        String response = getShrekMoviesListResponse();
        JSONArray moviesJsonList = bechdelMoviesApi.getMoviesJsonList(response);

        assertThat(moviesJsonList.length(), is(equalTo(7)));
        assertThat(moviesJsonList.getJSONObject(0).getString(ID), is(equalTo(ID_VALUE)));
    }

    @Test(expected = MovieNotFoundException.class)
    public void shouldThrowExceptionWhenMovieNotFound() throws Exception {
        String response = getErrorResponse();
        bechdelMoviesApi.getMoviesJsonList(response);
    }

    private String getErrorResponse() {
        return "[]";
    }

    private String getShrekMoviesListResponse() {
        return "[" +
                "{\"imdbid\":\"tt0126029\"}," +
                "{\"imdbid\":\"tt0298148\",}," +
                "{\"imdbid\":\"tt0413267\",}," +
                "{\"imdbid\":\"tt0892791\"}," +
                "{\"imdbid\":\"tt0897387\"}," +
                "{\"imdbid\":\"tt0360985\"}," +
                "{\"imdbid\":\"tt3107884\"}]";
    }

}