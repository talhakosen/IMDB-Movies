package com.fleetmatrics.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetmatrics.R;
import com.fleetmatrics.adapters.RecyclerViewAdapter;
import com.fleetmatrics.core.Constants;
import com.fleetmatrics.core.MyVolley;
import com.fleetmatrics.modal.Movie;
import com.fleetmatrics.modal.MovieModal;
import com.fleetmatrics.utils.ScrollManager;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener, View.OnClickListener {
    private RecyclerView recyclerView;
    private final int MSG_LOAD_DATA = 100;
    private final int MSG_LOAD_MORE = 101;
    private final int MSG_REFRESH = 102;
    private final int MSG_ERROR = 200;
    private Handler getMovieListHandler;
    private ObjectMapper objectMapper;

    private CircleProgressBar circleProgressBar;
    private TextView txt_error;
    private Button btn_loadMore;
    private GridLayoutManager gridLayoutManager;
    private RecyclerViewAdapter adapter;
    private Toolbar toolbar;
    private Button fab;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int movieListRequestPageIndex = 1;
    private boolean loadMoreLoading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private List<Movie> loadedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objectMapper = new ObjectMapper();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeViews();
        handleConcurrency();
        addMoviesRequestToQueue(MSG_LOAD_DATA);
    }

    private void handleConcurrency() {
        Thread getMovieList = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                getMovieListHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.obj == null)
                            return;

                        switch (msg.what) {
                            case MSG_LOAD_DATA:
                                loadMovies(msg);
                                break;
                            case MSG_LOAD_MORE:
                                loadMoreMovies(msg);
                                break;
                            case MSG_REFRESH:
                                refreshMovies(msg);
                                break;
                            case MSG_ERROR:
                                // TODO
                                break;
                        }

                    }

                    private void loadMoreMovies(Message msg) {
                        try {
                            MovieModal movieModal = objectMapper.readValue(msg.obj.toString(), MovieModal.class);
                            addNewMoviesToLoadedMovieList(movieModal.getResults());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            });

                            if (movieListRequestPageIndex <= movieModal.getTotal_pages())
                                movieListRequestPageIndex++;

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("MainActivity", e.toString());
                        }
                    }

                    private void loadMovies(Message msg) {
                        try {
                            MovieModal movieModal = objectMapper.readValue(msg.obj.toString(), MovieModal.class);
                            loadDataToRecyclerView(movieModal);

                            if (movieListRequestPageIndex <= movieModal.getTotal_pages())
                                movieListRequestPageIndex++;
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("MainActivity", e.toString());
                        }
                    }

                    public void refreshMovies(Message msg) {
                        if (loadedItems != null)
                            loadedItems.clear();

                        loadMovies(msg);
                    }
                };

                Looper.loop();
            }
        };
        getMovieList.start();
    }

    private void initializeViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        txt_error = (TextView) findViewById(R.id.txt_error);
        btn_loadMore = (Button) findViewById(R.id.btn_loadMore);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        circleProgressBar = (CircleProgressBar) findViewById(R.id.progressBar);
        fab = (Button) findViewById(R.id.fab);

        btn_loadMore.setOnClickListener(this);
        circleProgressBar.setColorSchemeResources(android.R.color.holo_green_dark);
        gridLayoutManager = new GridLayoutManager(this, 2);
        fab.setText(getResources().getText(R.string.lang));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });

        // Toolbar height needs to be known before establishing the initial offset
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                ScrollManager manager = new ScrollManager();
                manager.attach(recyclerView);
                manager.addView(toolbar, ScrollManager.Direction.UP);
                manager.addView(fab, ScrollManager.Direction.DOWN);
                manager.setInitialOffset(toolbar.getHeight());
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                movieListRequestPageIndex = 1;
                btn_loadMore.setVisibility(View.GONE);
                addMoviesRequestToQueue(MSG_REFRESH);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void addMoviesRequestToQueue(final int msg) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(String.format(Constants.MOVIES_LIST, movieListRequestPageIndex), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadMoreLoading = true;
                getMovieListHandler.obtainMessage(msg, response).sendToTarget();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                circleProgressBar.setVisibility(View.GONE);
                txt_error.setVisibility(View.VISIBLE);
            }
        });

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest);
    }

    private void loadDataToRecyclerView(final MovieModal movieModal) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new RecyclerViewAdapter(addNewMoviesToLoadedMovieList(movieModal.getResults()));
                adapter.setOnItemClickListener(MainActivity.this);
                recyclerView.setAdapter(adapter);


                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        visibleItemCount = gridLayoutManager.getChildCount();
                        totalItemCount = gridLayoutManager.getItemCount();
                        pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();

                        if (loadMoreLoading) {
                            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                loadMoreLoading = false;
                                btn_loadMore.setVisibility(View.VISIBLE);
                            }
                        }


                    }
                });
                circleProgressBar.setVisibility(View.GONE);
            }
        });
    }

    public List<Movie> addNewMoviesToLoadedMovieList(List<Movie> movies) {
        if (loadedItems == null)
            loadedItems = movies;
        else
            loadedItems.addAll(movies);

        Collections.sort(loadedItems, MovieModal.popularityComparator);

        return loadedItems;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loadMore:
                addMoviesRequestToQueue(MSG_LOAD_MORE);
                btn_loadMore.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onItemClick(View view, Movie movie) {
        DetailActivity.navigate(this, view.findViewById(R.id.image), movie);
    }
}


