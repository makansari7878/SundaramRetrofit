package com.example.manipalappone;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fetchData();
    }

    private void fetchData() {
        Call<List<Users>> makeCall = ApiClient.getApiInterface().getData();

        makeCall.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                List<Users> usersList = response.body();
                if (usersList != null) {
                    Log.i("mytag", "Response received:");
                    for (Users user : usersList) {
                        Log.i("mytag", "User: Name - " + user.getName() + ", " +
                                "Username - " + user.getUsername());
                    }

                } else {
                    Log.i("mytag", "Response body is null");

                }
            }
            // testig

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.e("mytag", "Error fetching data: " + t.toString());
                Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}