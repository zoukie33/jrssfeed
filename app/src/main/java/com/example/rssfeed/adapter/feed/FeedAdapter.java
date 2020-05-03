package com.example.rssfeed.adapter.feed;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rssfeed.API.res.Feed;
import com.example.rssfeed.R;

import java.util.List;

@SuppressWarnings("ALL")

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedHolder> {

    private final List<Feed> feedList;

    public FeedAdapter(List<Feed> feedList) {
        this.feedList = feedList;
    }

    @Override
    public FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View v = inf.inflate(R.layout.feed_item_view, parent, false);
        return new FeedHolder(v);
    }

    @Override
    public void onBindViewHolder(FeedHolder holder, int position) {
        Feed f = this.feedList.get(position);
        holder.display(f);
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public class FeedHolder extends RecyclerView.ViewHolder {

        private final TextView author;
        private final TextView title;
        private final TextView pubDate;
        private final TextView link;

        private Feed feedObj2;

        public FeedHolder(final View itemView) {
            super(itemView);
            author = ((TextView) itemView.findViewById(R.id.author));
            title = ((TextView) itemView.findViewById(R.id.title));
            pubDate = ((TextView) itemView.findViewById(R.id.pubDate));
            link = ((TextView) itemView.findViewById(R.id.link));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Link", feedObj2.getPubDate());
                    String url = feedObj2.getPubDate();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    v.getContext().startActivity(i);
                }
            });
        }

        public void display(Feed feedobj) {
            Log.d("ADAPTER", feedobj.getTitle() + " " + feedobj.getAuthor());
            if (feedobj.getAuthor() == "") {
                feedobj.setAuthor("n/a");
            }
            if (feedobj.getTitle() == "") {
                feedobj.setTitle("n/a");
            }
            if (feedobj.getPubDate() == "") {
                feedobj.setPubDate("n/a");
            }
            if (feedobj.getLink() == "") {
                feedobj.setLink("n/a");
            }

            feedObj2 = feedobj;
            author.setText(feedobj.getAuthor());
            title.setText(feedobj.getTitle());
            pubDate.setText(feedobj.getPubDate());
            link.setText(feedobj.getLink());
        }
    }
}
