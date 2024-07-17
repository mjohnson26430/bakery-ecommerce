package com.example.namequiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private Context context;
    private List<LeaderboardItem> leaderboardItems;

    public RvAdapter(Context context, List<LeaderboardItem> leaderboardItems) {
        this.context = context;
        this.leaderboardItems = leaderboardItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leaderboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LeaderboardItem item = leaderboardItems.get(position);
        holder.tvName.setText(item.getPlayerName());
        holder.tvScore.setText(String.valueOf(item.getScore()));

        switch (position) {
            case 0:
                holder.medalImage.setImageResource(R.drawable.first);
                break;
            case 1:
                holder.medalImage.setImageResource(R.drawable.second);
                break;
            case 2:
                holder.medalImage.setImageResource(R.drawable.third);
                break;
            default:
                holder.medalImage.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return leaderboardItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvScore;
        public ImageView medalImage;

        public ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvScore = view.findViewById(R.id.tvScore);
            medalImage = view.findViewById(R.id.Rank);
        }
    }
}
