package com.fleetmatrics;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import com.fleetmatrics.ui.activities.DetailActivity;


public class DetailActivityTest extends ActivityInstrumentationTestCase2<DetailActivity> {
    private DetailActivity mDetailActivity;

    public DetailActivityTest() {
        super(DetailActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
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


}