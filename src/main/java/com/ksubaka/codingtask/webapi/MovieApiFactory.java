package com.ksubaka.codingtask.webapi;

import java.util.HashMap;
import java.util.Map;

enum MovieApiName {
    OMDB("omdb"),
    BECHDEL("bechdel");

    private static final Map<String, MovieApiName> nameToValue = new HashMap<>();

    static {
        for (MovieApiName movieApiName : values()) {
            nameToValue.put(movieApiName.name, movieApiName);
        }
    }

    private final String name;

    MovieApiName(String name) {
        this.name = name;
    }

    static MovieApiName getValue(String api) throws MovieApiFactory.UnrecognizedMovieApiException {
        if (!nameToValue.containsKey(api))
            throw new MovieApiFactory.UnrecognizedMovieApiException(api);
        return nameToValue.get(api);
    }
}

public class MovieApiFactory {
    public static MoviesApi getMovieApi(String api) throws UnrecognizedMovieApiException {
        switch (MovieApiName.getValue(api)) {
            case OMDB:
                return new OMDBMoviesApi();
            case BECHDEL:
                return new BechdelMoviesApi();
        }
        throw new UnrecognizedMovieApiException(api);
    }

    static class UnrecognizedMovieApiException extends Exception {
        private static final String messageTemplate = "Unrecognized movie api with name: ";

        UnrecognizedMovieApiException(String parameter) {
            super(String.format("%s\"%s\"", messageTemplate, parameter));
        }
    }
}
