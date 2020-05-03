package com.example.rssfeed.adapter.rss;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rssfeed.API.res.Rss;
import com.example.rssfeed.ItemListActivity;
import com.example.rssfeed.R;

import java.util.List;
@SuppressWarnings("ALL")

public class RssAdapter extends RecyclerView.Adapter<RssAdapter.RssHolder> {

    private final List<Rss> rssList;

    public RssAdapter(List<Rss> rssList) {
        this.rssList = rssList;
    }

    @Override
    public RssHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View v = inf.inflate(R.layout.rss_item_view, parent, false);
        return new RssHolder(v);
    }

    @Override
    public void onBindViewHolder(RssHolder holder, int position) {
        Rss r = this.rssList.get(position);
        holder.display(r);
    }

    @Override
    public int getItemCount() {
        return rssList.size();
    }

    public class RssHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;

        private Rss rssObj2;

        public RssHolder(final View itemView) {
            super(itemView);
            title = ((TextView) itemView.findViewById(R.id.rssTitle));
            description = ((TextView) itemView.findViewById(R.id.rssDescription));
            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), ItemListActivity.class);
                    // rssObj2.getItems()
                    myIntent.putParcelableArrayListExtra("items", rssObj2.getItems());
                    v.getContext().startActivity(myIntent);
                }
            });
        }

        public void display(Rss rssObj) {
            Log.d("ADAPTER", rssObj.getTitle() + " " + rssObj.getDescription());
            rssObj2 = rssObj;
            title.setText(rssObj.getTitle());
            description.setText(rssObj.getDescription());
        }
    }
}
