package com.use.samachar;


import java.util.List;

public interface NewsDataSource {
    void getTopHeadlines(String country, NewsCallback callback);

    interface NewsCallback {
        void onSuccess(List<Article> articles);
        void onError(String errorMessage);
    }
}
