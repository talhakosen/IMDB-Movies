package com.fleetmatrics.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetmatrics.R;
import com.fleetmatrics.adapters.TransitionAdapter;
import com.fleetmatrics.core.Constants;
import com.fleetmatrics.core.MyVolley;
import com.fleetmatrics.modal.Movie;
import com.fleetmatrics.modal.MovieDetail;
import com.fleetmatrics.utils.WindowCompatUtils;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;
import java.io.IOException;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String EXTRA_MOVIE = "com.fleetmatrics.modal.movie";
    private Toolbar toolbar;
    private ImageView image, img_poster_path;
    private Button fab, homepage;
    private TextView production_companies, vote_count, vote_average, status, spoken_languages, original_title, overview;
    private Movie movie;
    private MovieDetail movieDetail;
    private ObjectMapper objectMapper;

    public static void navigate(AppCompatActivity activity, View transitionImage, Movie movie) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, String.format(Constants.MOVIES_IMAGE_LARGE, movie.getBackdrop_path()));
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        objectMapper = new ObjectMapper();
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        setTitle(movie.getTitle());

        initializeViews();
        loadMovieData();
        loadMovieImageToView();
        addMovieDetailRequestToQueue();
    }

    private void loadMovieData() {
        original_title.setText(movie.getTitle());
        vote_average.setText(String.valueOf(movie.getVote_average()));
        vote_count.setText(String.valueOf(movie.getVote_count()));
        fab.setText(movie.getOriginal_language());
        overview.setText(movie.getOverview());
        Picasso.with(this).load(String.format(Constants.MOVIES_IMAGE_LARGE, movie.getPoster_path())).into(img_poster_path);
    }

    private void initializeViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = (Button) findViewById(R.id.fab);
        homepage = (Button) findViewById(R.id.homepage);
        img_poster_path = (ImageView) findViewById(R.id.img_poster_path);
        production_companies = (TextView) findViewById(R.id.production_companies);
        vote_count = (TextView) findViewById(R.id.vote_count);
        vote_average = (TextView) findViewById(R.id.vote_average);
        status = (TextView) findViewById(R.id.status);
        spoken_languages = (TextView) findViewById(R.id.spoken_languages);
        overview = (TextView) findViewById(R.id.overview);
        original_title = (TextView) findViewById(R.id.original_title);
    }

    public void addMovieDetailRequestToQueue() {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(String.format(Constants.MOVIE_DETAIL, movie.getId()), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    movieDetail = objectMapper.readValue(response.toString(), MovieDetail.class);
                    loadDetailInformation(movieDetail);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest);
    }

    private void loadMovieImageToView() {
        image = (ImageView) findViewById(R.id.image);
        ViewCompat.setTransitionName(image, String.format(Constants.MOVIES_IMAGE_LARGE, movie.getBackdrop_path()));
        Picasso.with(this).load(String.format(Constants.MOVIES_IMAGE_LARGE, movie.getBackdrop_path())).into(image, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette, image);
                    }
                });
            }

            @Override
            public void onError() {

            }
        });
    }

    private void loadDetailInformation(MovieDetail detail) {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < detail.getSpoken_languages().size(); i++) {
                sb.append(detail.getSpoken_languages().get(i).getName());
                if (i < detail.getSpoken_languages().size() - 1)
                    sb.append(", ");
            }

            spoken_languages.setText(sb.toString());
        } catch (Exception e) {
            spoken_languages.setVisibility(View.GONE);
        }

        try {
            sb = new StringBuilder();
            for (int i = 0; i < detail.getProduction_companies().size(); i++) {
                sb.append(detail.getProduction_companies().get(i).getName());
                if (i < detail.getProduction_companies().size() - 1)
                    sb.append(", ");
            }

            production_companies.setText(sb.toString());
        } catch (Exception e) {
            production_companies.setVisibility(View.GONE);
        }

        status.setText(movieDetail.getStatus());

        if (movieDetail.getHomepage() != null && !movieDetail.getHomepage().equals("")) {
            homepage.setText(movieDetail.getHomepage());
            homepage.setTag(movieDetail.getHomepage());
            homepage.setOnClickListener(this);
        } else
            homepage.setVisibility(View.GONE);
    }

    private void applyPalette(Palette palette, ImageView image) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        toolbar.setBackgroundColor(palette.getMutedColor(primary));
        WindowCompatUtils.setStatusBarcolor(getWindow(), palette.getDarkMutedColor(primaryDark));
        initScrollFade(image);
        ActivityCompat.startPostponedEnterTransition(this);
    }

    private void initScrollFade(final ImageView image) {
        final View scrollView = findViewById(R.id.scroll);

        setComponentsStatus(scrollView, image);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                setComponentsStatus(scrollView, image);
            }
        });
    }

    private void setComponentsStatus(View scrollView, ImageView image) {
        int scrollY = scrollView.getScrollY();
        if (Build.VERSION.SDK_INT > 10)
            image.setTranslationY(-scrollY / 2);

        ColorDrawable background = (ColorDrawable) toolbar.getBackground();
        int padding = scrollView.getPaddingTop();
        double alpha = (1 - (((double) padding - (double) scrollY) / (double) padding)) * 255.0;
        alpha = alpha < 0 ? 0 : alpha;
        alpha = alpha > 255 ? 255 : alpha;

        background.setAlpha((int) alpha);

        float scrollRatio = (float) (alpha / 255f);
        int titleColor = getAlphaColor(Color.WHITE, scrollRatio);
        toolbar.setTitleTextColor(titleColor);
    }

    private int getAlphaColor(int color, float scrollRatio) {
        return Color.argb((int) (scrollRatio * 255f), Color.red(color), Color.green(color), Color.blue(color));
    }

    private void restablishActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getReturnTransition().addListener(new TransitionAdapter() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    toolbar.setTitleTextColor(Color.WHITE);
                    toolbar.getBackground().setAlpha(255);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            restablishActionBar();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieDetail=null;
        movie=null;
        objectMapper=null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homepage:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(v.getTag().toString()));
                startActivity(browserIntent);
                break;
        }
    }
}
