package com.fleetmatrics;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

import com.fleetmatrics.modal.Movie;
import com.fleetmatrics.ui.activities.DetailActivity;


public class DetailActivityTest extends ActivityInstrumentationTestCase2<DetailActivity> {
    private DetailActivity mDetailActivity;

    public DetailActivityTest() {
        super(DetailActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Movie movie = new Movie();
        movie.setBackdrop_path("");
        setActivityIntent(new Intent().putExtra("com.fleetmatrics.modal.movie", movie));
        mDetailActivity = getActivity();

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests the preconditions of this test fixture.
     */
    @MediumTest
    public void testPreconditions() {
        assertNotNull("mDetailActivity is null", mDetailActivity);
    }


    @MediumTest
    public void testMovieImageIsNull() {
        Movie movie = getActivity().getIntent().getParcelableExtra("com.fleetmatrics.modal.movie");
        assertNotNull("Movie image is null", movie.getBackdrop_path());
    }
}