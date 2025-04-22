package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private TextView textViewTitle, textViewNews;
    private RecyclerView topStoriesRecyclerView, newsRecyclerView;
    private NewsAdapter newsAdapter;

    // Declare the list at the top
    private final ArrayList<News> newsList = new ArrayList<>();
    private final ArrayList<Integer> topStoryImages = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind views
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewNews = findViewById(R.id.textViewNews);
        topStoriesRecyclerView = findViewById(R.id.topStoriesRecyclerView);
        newsRecyclerView = findViewById(R.id.newsRecyclerView);

        // Add mock news items
        newsList.add(new News("Breaking News", "This is a breaking news description.", R.mipmap.ic_launcher));
        newsList.add(new News("Daily Update", "Here is your daily update.", R.mipmap.ic_launcher));
        newsList.add(new News("Just in", "Details about this recent incident.", R.mipmap.ic_launcher));

        // Set up adapter and layout manager
        topStoryImages.add(R.mipmap.ic_launcher);
        topStoryImages.add(R.mipmap.ic_launcher);
        topStoryImages.add(R.mipmap.ic_launcher);

        // Set up top stories recycler view
        topStoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        topStoriesRecyclerView.setAdapter(new TopStoriesAdapter(topStoryImages));

        newsRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        NewsAdapter newsAdapter = new NewsAdapter(newsList, clicked -> {
            Intent i = new Intent(this, NewsDetailsActivity.class);
            i.putExtra("title", clicked.getTitle());
            i.putExtra("body",  clicked.getDescription());
            i.putExtra("img",   clicked.getImageResId());
            startActivity(i);
        });
        newsRecyclerView.setAdapter(newsAdapter);

    }
}
