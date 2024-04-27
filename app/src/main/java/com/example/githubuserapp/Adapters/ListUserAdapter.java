package com.example.githubuserapp.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubuserapp.DetailActivity;
import com.example.githubuserapp.Models.ItemsItem;
import com.example.githubuserapp.R;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ViewHolder> {
    private List<ItemsItem> users;

    public ListUserAdapter(List <ItemsItem> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemsItem user = users.get(position);
        holder.name.setText(user.getLogin());
        Picasso.get().load(user.getAvatarUrl()).into(holder.profile);

        holder.itemView.setOnClickListener(click -> {
            Intent intent = new Intent(click.getContext(), DetailActivity.class);
            intent.putExtra("username", user.getLogin());
            intent.putExtra("avatarUrl", user.getAvatarUrl());
            click.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.PhotoProfile);
            name = itemView.findViewById(R.id.namaProfile);
        }
    }
}