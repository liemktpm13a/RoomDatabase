package com.example.lab7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private Context ctx;
    private LayoutInflater inflater;
    private List<User> listUser;

    public UserAdapter(Context ctx, List<User> listUser) {

        this.listUser = listUser;
        inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View viewItem = inflater.inflate(R.layout.item_view,parent,false);
       UserViewHolder viewHolder = new UserViewHolder(viewItem);
       return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = listUser.get(position);
        holder.tvNameStd.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserViewHolder extends  RecyclerView.ViewHolder{
        private TextView tvNameStd;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameStd = itemView.findViewById(R.id.tvNameUser);
        }
    }
}
