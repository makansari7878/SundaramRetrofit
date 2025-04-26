package com.example.manipalappone;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<Users> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this, userList);
        recyclerView.setAdapter(userAdapter);

        fetchData();
    }

    private void fetchData() {
        Call<List<Users>> makeCall = ApiClient.getApiInterface().getData();

        makeCall.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                List<Users> usersList = response.body();
                if (usersList != null) {
                    Log.i("mytag", "Response received: " + usersList.size() + " users");
                    userList.clear();       // Clear the list before adding new data
                    userList.addAll(usersList); // Add the fetched users to the list
                    userAdapter.notifyDataSetChanged(); // Notify the adapter to update the RecyclerView
                } else {
                    Log.i("mytag", "Response body is null");
                    Toast.makeText(MainActivity.this, "Empty response from server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.e("mytag", "Error fetching data: " + t.toString());
                Toast.makeText(MainActivity.this, "Failed to fetch data: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}