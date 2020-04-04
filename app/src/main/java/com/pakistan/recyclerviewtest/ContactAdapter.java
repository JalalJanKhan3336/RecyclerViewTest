package com.pakistan.recyclerviewtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private Context mContext;
    private List<Contact> mContactList;
    private RecyclerViewItemClickListener mRecyclerViewItemClickListener;
    private RecyclerViewItemActionListener mRecyclerViewItemActionListener;

    public ContactAdapter(Context mContext, List<Contact> mContactList, RecyclerViewItemClickListener mRecyclerViewItemClickListener,
                          RecyclerViewItemActionListener mRecyclerViewItemActionListener) {
        this.mContext = mContext;
        this.mContactList = mContactList;
        this.mRecyclerViewItemClickListener = mRecyclerViewItemClickListener;
        this.mRecyclerViewItemActionListener = mRecyclerViewItemActionListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
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

        holder.popupMenu_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu(mContext,holder.popupMenu_IV);
                menu.getMenuInflater().inflate(R.menu.item_popup_menu,menu.getMenu());
                menu.show();

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        boolean isClicked = false;

                        switch (item.getItemId()){
                            case R.id.action_delete:{
                                isClicked = true;
                                Log.d("ContactAdapter", "Deleted_Item_At_Position: "+position);
                                mRecyclerViewItemActionListener.onActionDeleteClicked(obj,position);
                                break;
                            }
                            case R.id.action_archive:{
                                isClicked = true;
                                Log.d("ContactAdapter", "Archived_Item_At_Position: "+position);
                                mRecyclerViewItemActionListener.onActionArchiveClicked(obj,position);
                                break;
                            }
                            default:
                                break;
                        }

                        return isClicked;
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView, popupMenu_IV;
        private TextView nameTV, lastMessageTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_iv);
            popupMenu_IV = itemView.findViewById(R.id.popup_menu_iv);
            nameTV = itemView.findViewById(R.id.name_tv);
            lastMessageTV = itemView.findViewById(R.id.last_msg_tv);
        }
    }
}
