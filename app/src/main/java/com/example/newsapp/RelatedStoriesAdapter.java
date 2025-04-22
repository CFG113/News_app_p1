package com.example.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RelatedStoriesAdapter extends RecyclerView.Adapter<RelatedStoriesAdapter.StoryViewHolder> {

    private final List<News> relatedItems;

    public RelatedStoriesAdapter(List<News> relatedItems) {
        this.relatedItems = relatedItems;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.related_news_item, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        News story = relatedItems.get(position);
        holder.title.setText(story.getTitle());
        holder.image.setImageResource(story.getImageResId());
    }

    @Override
    public int getItemCount() {
        return relatedItems != null ? relatedItems.size() : 0;
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageTopStory);
            title = itemView.findViewById(R.id.textRelatedTitle);
        }
    }
}
