package com.ksubaka.codingtask;

import com.ksubaka.codingtask.domain.MovieInfo;
import com.ksubaka.codingtask.webapi.MovieApiFactory;
import com.ksubaka.codingtask.webapi.MoviesApiParser;
import com.ksubaka.codingtask.webapi.connector.JerseyApiConnector;
import com.ksubaka.codingtask.webapi.connector.WebApiConnector;
import org.apache.log4j.Logger;

import java.util.List;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        String api = System.getProperty(Constants.API);
        String movie = System.getProperty(Constants.MOVIE);
        logger.debug(String.format("Received properties: api: %s, movie: %s", api, movie));

        try {
            validateProperties(api, movie);
            String formattedMovie = formatMovieProperty(movie);
            MoviesApiParser moviesApi = new MoviesApiParser(MovieApiFactory.getMovieApi(api));
            WebApiConnector jerseyApiConnector = new JerseyApiConnector();

            MovieInfoRetriever movieInfoRetriever = new MovieInfoRetriever(jerseyApiConnector, moviesApi);
            List<MovieInfo> movieInfos = movieInfoRetriever.retrieve(formattedMovie);
            movieInfos.forEach(movieInfo -> logger.info(movieInfo));
        } catch (Exception e) {
            logger.error(String.format("Unable to process request for api: \"%s\", movie: \"%s\". Reason: %s", api,
                    movie, e.getMessage()));
        }
    }

    private static void validateProperties(String api, String movie) throws Exception {
        if (isNullOrEmpty(api) || isNullOrEmpty(movie)) {
            throw new Exception("Program properties are not set correctly. Are you sure you set both api and movie " +
                    "property? Eg. java -Dapi=omdb -Dmovie=\"toy story\"");
        }
    }

    private static String formatMovieProperty(String movie) {
        return String.join(Constants.PLUS_DELIMITER, movie.split(Constants.SPACE));
    }

    private static boolean isNullOrEmpty(String property) {
        return property == null || "".equals(property);
    }
}
