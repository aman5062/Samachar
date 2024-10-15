import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.use.samachar.Article;
import com.use.samachar.NewsAdapter;
import com.use.samachar.NewsDataSource;
import com.use.samachar.NewsRepository;
import com.use.samachar.R;
import com.use.samachar.screen;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private NewsRepository newsRepository;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the recyclerView


        // Initialize the newsAdapter
        newsAdapter = new NewsAdapter(new ArrayList<Article>());

        recyclerView.setAdapter(newsAdapter);

        // Hide the action bar
        getSupportActionBar().hide();

        // Set the status bar color
        getWindow().setStatusBarColor(Color.parseColor("#ffffff"));

        // Initialize NewsRepository
        newsRepository = new NewsRepository();

        // Fetch news
        fetchNews();

        // Delay for splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, screen.class);
                startActivity(intent);
                finish();
            }
        }, 3500);
    }

    private void fetchNews() {
        newsRepository.getTopHeadlines("us", new NewsDataSource.NewsCallback() {
            @Override
            public void onSuccess(List<Article> articles) {
                // Log the results
                Log.d(TAG, "API call successful. Fetched " + articles.size() + " articles.");
                for (Article article : articles) {
                    Log.d(TAG, "Title: " + article.getTitle());
                    Log.d(TAG, "Description: " + article.getDescription());
                    Log.d(TAG, "Image: " + article.getUrlToImage());
                    Log.d(TAG, "Time: " + article.getPublishedAt());
                    Log.d(TAG, "------------------- - - - - - ");
                }
                // Update the newsAdapter with the fetched articles
                newsAdapter.updateData(articles);
                // TODO: Store these articles or pass them to the next activity
            }

            @Override
            public void onError(String errorMessage) {
                Log.e(TAG, "API call failed: " + errorMessage);
            }
        });
    }
}