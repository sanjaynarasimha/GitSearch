package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubAPI {
    @GET("search/repositories")
    Call<GitHubResponse> searchRepositories(
            @Query("q") String query,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
}
