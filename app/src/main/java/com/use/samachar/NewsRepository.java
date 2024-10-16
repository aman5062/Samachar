package com.use.samachar;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsRepository implements NewsDataSource {
    private static final String API_KEY = "eb662e80ddde4e0c824dbe390eadf26d";
    private NewsApiService apiService;

    public NewsRepository() {
        apiService = RetrofitClient.getClient().create(NewsApiService.class);
    }

    @Override
    public void getTopHeadlines(String country, final NewsCallback callback) {
        Call<NewsResponse> call = apiService.getTopHeadlines(country, API_KEY);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> articles = response.body().getArticles();
                    if (articles != null) {
                        callback.onSuccess(articles);
                    } else {
                        callback.onError("No articles found");
                    }
                } else {
                    callback.onError("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
}