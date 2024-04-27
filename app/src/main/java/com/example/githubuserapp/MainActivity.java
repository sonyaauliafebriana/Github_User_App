package com.example.githubuserapp;



import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubuserapp.R;
import com.example.githubuserapp.Adapters.ListUserAdapter;
import com.example.githubuserapp.Config.ApiConfig;
import com.example.githubuserapp.Config.ApiService;
import com.example.githubuserapp.Models.ItemsItem;
import com.example.githubuserapp.Models.ResponseGithub;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RvList);

        ApiService apiService = ApiConfig.getApiService();
        Call<ResponseGithub> call = apiService.getUsers("sonya");

        call.enqueue(new Callback<ResponseGithub>() {
            @Override
            public void onResponse(Call<ResponseGithub> call, Response<ResponseGithub> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List ItemsItem = response.body().getItems();
                    adapter = new ListUserAdapter(ItemsItem);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGithub> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}