package com.ksubaka.codingtask.domain;

public class MovieInfo {
    private final String title;
    private final String year;
    private final String director;

    public MovieInfo(String title, String year, String director) {
        this.title = title;
        this.year = year;
        this.director = director;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Title: ");
        stringBuilder.append(title);
        stringBuilder.append("; ");

        stringBuilder.append("Year: ");
        stringBuilder.append(year);
        stringBuilder.append("; ");

        stringBuilder.append("Director(s): ");
        stringBuilder.append(director);

        return stringBuilder.toString();
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }
}
