package com.example.rssfeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssfeed.API.res.Feed;
import com.example.rssfeed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("ALL")
public class FeedHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.cv_fItem_card)
    CardView fRssCard;
    @BindView(R.id.author)
    TextView fRssAuthor;
    @BindView(R.id.title)
    TextView fRssTitle;
    @BindView(R.id.pubDate)
    TextView fRssPubDate;
    @BindView(R.id.link)
    TextView fRssLink;

    Context context;

    public FeedHolder(final View itemView) {
        super(itemView);
        this.context = context;

        ButterKnife.bind(this, itemView);
    }
    public void bind(final Feed feed) {
        fRssAuthor.setText(feed.getAuthor());
        //fRssLink.setText(feed.getLink());
        fRssPubDate.setText(feed.getPubDate());
        fRssTitle.setText(feed.getTitle());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = feed.getLink();

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
    }
}
