//package com.example.myapplication;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private RepoListAdapter repoListAdapter;
//    private List<Repository> repositories = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // Call the method to fetch repositories
//        fetchRepositories("android");
//    }
//
//    private void fetchRepositories(String query) {
//        String url = "https://api.github.com/search/repositories?q=" + query;
//
//        StringRequest stringRequest = new StringRequest(
//                com.android.volley.Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            JSONArray items = jsonResponse.getJSONArray("items");
//                            repositories.clear();  // Clear previous results
//
//                            for (int i = 0; i < items.length(); i++) {
//                                JSONObject repoJson = items.getJSONObject(i);
//                                String name = repoJson.optString("name", "N/A");
//                                String owner = repoJson.optJSONObject("owner").optString("login", "N/A");
//                                String description = repoJson.optString("description", "No description");
//                                int stars = repoJson.optInt("stargazers_count", 0);
//                                String avatarUrl = repoJson.optJSONObject("owner").optString("avatar_url", "");
//
//                                // Add to the list
//                                repositories.add(new Repository(name, owner, description, stars, avatarUrl));
//                            }
//
//                            // Update the adapter with the new list
//                            repoListAdapter = new RepoListAdapter(MainActivity.this, repositories);
//                            recyclerView.setAdapter(repoListAdapter);
//
//                        } catch (JSONException e) {
//                            Log.e("MainActivity", "JSON Parsing error", e);
//                            Toast.makeText(MainActivity.this, "Error parsing data.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("MainActivity", "Error fetching data", error);
//                        Toast.makeText(MainActivity.this, "Error fetching data.", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
//    }
//}


package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RepoListAdapter adapter;
    private List<Repository> repositoryList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        searchView = findViewById(R.id.search_view);

        repositoryList = new ArrayList<>();
        adapter = new RepoListAdapter(this, repositoryList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchRepositories(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void fetchRepositories(String query) {
        String url = "https://api.github.com/search/repositories?q=" + query;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray itemsArray = jsonObject.getJSONArray("items");

                        repositoryList.clear(); // Clear old data
                        for (int i = 0; i < itemsArray.length(); i++) {
                            JSONObject repoObject = itemsArray.getJSONObject(i);
                            String name = repoObject.getString("name");
                            String owner = repoObject.getJSONObject("owner").getString("login");
                            String description = repoObject.optString("description", "No description available");
                            int stars = repoObject.getInt("stargazers_count");

                            repositoryList.add(new Repository(name, owner, description, stars));
                        }

                        adapter.notifyDataSetChanged();

                        if (repositoryList.isEmpty()) {
                            Toast.makeText(MainActivity.this, "No repositories found", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Log.e("PARSING_ERROR", "Error parsing response", e);
                    }
                },
                error -> Log.e("API_ERROR", "Error fetching data", error)
        );

        requestQueue.add(stringRequest);
    }
}
