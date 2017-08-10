package com.example.ahmad.movie21;

import android.app.ProgressDialog;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog dialog;
    private ListView listView;

    ArrayList<String> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);
        connection();
        /*ListAdapter adapter = new
        listView.setAdapter(adapter);*/
    }

    private void connection() {
        final Request request = new Request.Builder()
                .url(MyApplication.BASE_URL)
                .get().build();

        MyApplication.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String body = response.body().string();

                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        JSONArray movie = jsonObject.getJSONArray("production_companies");
                        for (int i = 0; i < movie.length(); i++) {
                            JSONObject c = movie.getJSONObject(i);

                            String mv = c.getString("name");
                            movieList.add(mv);
                            Log.d(TAG, "onResponse"+movieList);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
