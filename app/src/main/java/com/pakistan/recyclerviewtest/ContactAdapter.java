package com.pakistan.recyclerviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private Context mContext;
    private List<Contact> mContactList;
    private RecyclerViewItemClickListener mRecyclerViewItemClickListener;

    public ContactAdapter(Context mContext, List<Contact> mContactList, RecyclerViewItemClickListener listener) {
        this.mContext = mContext;
        this.mContactList = mContactList;
        mRecyclerViewItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Contact obj = mContactList.get(position);

        holder.imageView.setImageResource(obj.getImage());
        holder.nameTV.setText(obj.getName());
        holder.lastMessageTV.setText(obj.getLastMsg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerViewItemClickListener.onItemSingleClicked(obj, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nameTV, lastMessageTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_iv);
            nameTV = itemView.findViewById(R.id.name_tv);
            lastMessageTV = itemView.findViewById(R.id.last_msg_tv);
        }
    }
}
