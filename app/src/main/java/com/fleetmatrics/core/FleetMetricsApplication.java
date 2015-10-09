package com.fleetmatrics.core;

import android.app.Application;

import com.fleetmatrics.modal.Genre;

import java.util.HashMap;

/**
 * Author
 * User: tkosen
 * Date: 06/03/2015
 * talhakosen@gmail.com
 */
public final class FleetMetricsApplication extends Application {
    private static FleetMetricsApplication instance;
    private HashMap<Integer, Genre> genres;

    public static FleetMetricsApplication getInstance() {
        if (instance == null)
            instance = new FleetMetricsApplication();

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public HashMap<Integer, Genre> getGenres() {
        if (genres == null)
            genres = new HashMap<>();

        return genres;
    }

}
