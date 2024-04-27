package com.example.githubuserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubuserapp.Config.ApiConfig;
import com.example.githubuserapp.Config.ApiService;
import com.example.githubuserapp.Models.ItemsItem;
import com.example.githubuserapp.Models.ResponseDetail;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressBar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String username = extras.getString("username");
            ApiService apiService = ApiConfig.getApiService();
            Call<ResponseDetail> userCall = apiService.getDetailUser(username);

            TextView Name = findViewById(R.id.tvNama);
            TextView Username = findViewById(R.id.tvUsername);
            TextView Bio = findViewById(R.id.tvBio);
            ImageView imageView = findViewById(R.id.ivProfil);

            showLoading(true);
            userCall.enqueue(new Callback<ResponseDetail>() {
                @Override
                public void onResponse(Call<ResponseDetail> call, Response<ResponseDetail> response) {
                    if (response.isSuccessful()){
                        showLoading(false);
                        ResponseDetail user = response.body();
                        if (user != null){
                            String name = "Name: " + user.getName();
                            String usernames = "Username: " + user.getLogin();
                            String bio = "Bio: " + user.getBio();
                            String gambar = user.getAvatarUrl();

                            Name.setText(name);
                            Username.setText(usernames);
                            Bio.setText(bio);
                            Picasso.get().load(gambar).into(imageView);
                        }else {
                            Toast.makeText(DetailActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseDetail> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}