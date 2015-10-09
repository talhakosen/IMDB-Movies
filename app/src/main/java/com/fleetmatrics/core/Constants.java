package com.fleetmatrics.core;

/**
 * Created by Talha Kosen on 06/03/2015.
 */
public class Constants {
    public final static String API_KEY ="21ce89fd10ca919d6f2edba1324ae870";
    public final static String MOVIES_LIST ="http://api.themoviedb.org/3/movie/popular?page=%s&api_key="+API_KEY;
    public final static String MOVIES_GENRES ="http://api.themoviedb.org/3/genre/movie/list?api_key="+API_KEY;
    public final static String MOVIE_DETAIL ="http://api.themoviedb.org/3/movie/%d"+"?api_key="+API_KEY;
    public final static String MOVIES_IMAGE_SMALL = "https://image.tmdb.org/t/p/w185%s";
    public final static String MOVIES_IMAGE_LARGE = "https://image.tmdb.org/t/p/w780%s";
}
