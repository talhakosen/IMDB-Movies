package com.fleetmatrics.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetmatrics.R;
import com.fleetmatrics.adapters.RecyclerViewAdapter;
import com.fleetmatrics.core.Constants;
import com.fleetmatrics.core.FleetMetricsApplication;
import com.fleetmatrics.core.MyVolley;
import com.fleetmatrics.modal.Genre;
import com.fleetmatrics.modal.Movie;
import com.fleetmatrics.modal.MovieModal;
import com.fleetmatrics.utils.ScrollManager;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Constants.MOVIES_GENRES, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    List<Genre> genres = objectMapper.readValue(response.get("genres").toString(), new TypeReference<List<Genre>>() {});
                    for(Genre genre : genres){
                        FleetMetricsApplication.getInstance().getGenres().put(genre.getId(),genre);
                    }

                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    SplashActivity.this.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        MyVolley.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest);

    }

}
