package com.pakistan.recyclerviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewItemClickListener {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private List<Contact> contactList = new ArrayList<>();

    private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();

            // Right to Left Swipe
            if(direction == ItemTouchHelper.LEFT){
                contactList.remove(position);
                adapter.notifyItemRemoved(position);

                Snackbar snackbar = Snackbar.make(recyclerView, "Item at position "+position+" is Removed", Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Undo Clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                snackbar.show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        populateList();
    }

    private void populateList() {

        // Dummy data
        for(int i = 0; i < 20; i++){
            int img = R.drawable.ic_launcher_background;
            String naam = "Contact Name No. "+i;
            String lastmsg = "Last Message of Contact Name No. "+i;

            Contact obj = new Contact(img, naam, lastmsg);

            contactList.add(obj);
        }

        setUpRecyclerView(contactList);
    }

    private void setUpRecyclerView(List<Contact> list){

        adapter = new ContactAdapter(this, list, this);

        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(layout);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(simpleCallback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onItemSingleClicked(Object obj, int position) {
        Contact contact = (Contact) obj;
        Toast.makeText(MainActivity.this, "Item clicked on Position "+position, Toast.LENGTH_SHORT).show();
    }
}