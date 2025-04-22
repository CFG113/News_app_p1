package com.example.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.CardVH> {

    public interface OnTap { void handle(News n); }

    private final List<News> feed;
    private final OnTap tapCb;

    public NewsAdapter(List<News> feed, OnTap tapCb) {
        this.feed  = feed;
        this.tapCb = tapCb;
    }

    @NonNull @Override
    public CardVH onCreateViewHolder(@NonNull ViewGroup p,int vt) {
        return new CardVH(LayoutInflater.from(p.getContext()).inflate(R.layout.news_item,p,false));
    }

    @Override public void onBindViewHolder(@NonNull CardVH h,int pos) { h.bind(feed.get(pos),tapCb); }

    @Override public int getItemCount() { return feed.size(); }

    /* ------------ holder ------------ */
    public static class CardVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView thumb; private final TextView head,snip;
        private News cached; private OnTap cb;
        CardVH(View v){ super(v);
            thumb=v.findViewById(R.id.imageTopStory);
            head =v.findViewById(R.id.textNewsTitle);
            snip =v.findViewById(R.id.textNewsSummary);
            v.setOnClickListener(this);
        }
        void bind(News n,OnTap cb){
            cached=n; this.cb=cb;
            thumb.setImageResource(n.getImageResId());
            head .setText(n.getTitle());
            snip .setText(n.getDescription());
        }
        @Override public void onClick(View v){ if(cb!=null&&cached!=null) cb.handle(cached); }
    }
}
