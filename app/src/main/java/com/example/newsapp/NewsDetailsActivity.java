package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsDetailsActivity extends AppCompatActivity {

    private ImageView newsImage;
    private TextView textNewsTitle;
    private TextView detailDescription;
    private RecyclerView relatedRecyclerView;

    private ArrayList<News> allNews;
    private ArrayList<News> relatedNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        bindViews();
        loadIntentData();
    }

    private void bindViews() {
        newsImage = findViewById(R.id.newsImage);
        textNewsTitle = findViewById(R.id.textNewsTitle);
        detailDescription = findViewById(R.id.detailDescription);
        relatedRecyclerView = findViewById(R.id.relatedRecyclerView);
        relatedRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsImage.setOnClickListener(v -> finish());
    }

    private void loadIntentData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("body");
        int imageRes = intent.getIntExtra("img", R.mipmap.ic_launcher);

        // Show the selected news item
        textNewsTitle.setText(title);
        detailDescription.setText(description);
        newsImage.setImageResource(imageRes);

        // Rebuild the full list (mocked same way as in MainActivity)
        allNews = new ArrayList<>();
        allNews.add(new News("Breaking News", "This is a breaking news description.", R.mipmap.ic_launcher));
        allNews.add(new News("Daily Update", "Here is your daily update.", R.mipmap.ic_launcher));
        allNews.add(new News("Just in", "Details about this recent incident.", R.mipmap.ic_launcher));

        // Filter out the selected news
        relatedNews = new ArrayList<>();
        for (News n : allNews) {
            if (!n.getTitle().equals(title)) {
                relatedNews.add(n);
            }
        }

        RelatedStoriesAdapter adapter = new RelatedStoriesAdapter(relatedNews);
        relatedRecyclerView.setAdapter(adapter);
    }
}
