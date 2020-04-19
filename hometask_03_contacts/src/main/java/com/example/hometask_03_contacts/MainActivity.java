package com.example.hometask_03_contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton addNewContact = findViewById(R.id.addNewContact) ;
        addNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_add.class) ;
                startActivity(intent);
            }
        });

        recyclerContacts = findViewById(R.id.recyclerContacts);
        recyclerContacts.setAdapter(new NameListAdapter());
        recyclerContacts.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    static class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.ItemViewHolder> {

        private ArrayList<ContactClass> contacts = new ArrayList<>();

        NameListAdapter() {
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_element, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NameListAdapter.ItemViewHolder holder, int position) {
            holder.bindData(contacts.get(position));
        }

        void addItem(String name, String email, boolean isEmail) {
            ContactClass newContact = new ContactClass() ;
            newContact.setNumberOrEmail(email);
            newContact.setName(name);
            if (isEmail) {newContact.itIsEmail();}
            contacts.add(newContact);
//            notifyItemChanged(items.indexOf(name)); // for item
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ContactsComparator comparator = new ContactsComparator() ;
                contacts.sort(comparator);
            }
            notifyDataSetChanged(); // for all items
        }

        @Override
        public int getItemCount() {if (contacts != null)
            return contacts.size();
        else {
            return 0;}
        }

        static class ItemViewHolder extends RecyclerView.ViewHolder {
            private TextView nameText;
            private TextView emailText;

            ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                nameText = itemView.findViewById(R.id.nameText);
                emailText = itemView.findViewById(R.id.emailText);
            }

            void bindData(ContactClass contact) {
                nameText.setText(contact.getName());
                emailText.setText(contact.getNumberOrEmail());
            }
        }
    }


}



