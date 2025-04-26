package com.example.manipalappone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<Users> userList;
    private LayoutInflater inflater;

    public UserAdapter(Context context, List<Users> userList) {
        this.context = context;
        this.userList = userList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.user_layout, parent, false); // Create user_item_layout.xml
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users user = userList.get(position);
        holder.nameTextView.setText("Name: " + user.getName());
        holder.usernameTextView.setText("Username: " + user.getUsername());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView usernameTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);  // Replace with your actual IDs from user_item_layout.xml
            usernameTextView = itemView.findViewById(R.id.textViewLMyUsername); // Replace with your actual IDs from user_item_layout.xml
        }
    }
}