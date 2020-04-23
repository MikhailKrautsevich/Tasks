package com.example.hometask_03_contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.*;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerContacts;
    private TextView noContacts;
    static final int ADD_NEW_CONTACT = 900 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noContacts = findViewById(R.id.noContacts) ;

        ImageButton addNewContact = findViewById(R.id.addNewContact) ;
        addNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_add.class) ;
                startActivityForResult(intent, ADD_NEW_CONTACT);
            }
        });

        recyclerContacts = findViewById(R.id.recyclerContacts);
        recyclerContacts.setAdapter(new NameListAdapter());
        recyclerContacts.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        );
        recyclerContacts.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NameListAdapter adapter1 = (NameListAdapter) recyclerContacts.getAdapter();
        if (adapter1 != null && adapter1.isListOfContactsEmpty()) {
            recyclerContacts.setVisibility(View.INVISIBLE);
            noContacts.setVisibility(View.VISIBLE);
        } else {
            recyclerContacts.setVisibility(View.VISIBLE);
            noContacts.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean is = data.getBooleanExtra(EXTRAS.EXTRA_FOR_CONTACT_IS, false) ;
        if (!(data.getStringExtra(EXTRAS.EXTRA_FOR_CONTACT_NAME)==null) &&
                !(data.getStringExtra(EXTRAS.EXTRA_FOR_CONTACT_INFO)==null)) {
            String conInfo;
            String conName;
            conName = data.getStringExtra(EXTRAS.EXTRA_FOR_CONTACT_NAME).trim();
            conInfo = data.getStringExtra(EXTRAS.EXTRA_FOR_CONTACT_INFO).trim();
            ContactClass newContact = new ContactClass(conName, is, conInfo);
            NameListAdapter adapter1 = (NameListAdapter) recyclerContacts.getAdapter();
            if (!conInfo.isEmpty() && !conName.isEmpty()) {
                assert adapter1 != null;
                adapter1.addItem(newContact);
            } else {
                Toast.makeText(getApplicationContext(), R.string.somethingwrongwithc, LENGTH_SHORT)
                        .show();
            }
        }
    }


    static class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.ItemViewHolder> {

        static ArrayList<ContactClass> contacts = new ArrayList<>();

        NameListAdapter() {
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.contact_element, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NameListAdapter.ItemViewHolder holder, int position) {
            holder.bindData(contacts.get(position));
        }

        private boolean isListOfContactsEmpty(){
            return contacts.isEmpty();
        }

        private void addItem(ContactClass newContact) {
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
            private ImageView phNumberPic, emailPic ;

            ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                phNumberPic = itemView.findViewById(R.id.phNumberPic) ;
                emailPic = itemView.findViewById(R.id.emailPic) ;
                nameText = itemView.findViewById(R.id.nameText);
                emailText = itemView.findViewById(R.id.emailText);
            }

            void bindData(ContactClass contact) {
                nameText.setText(contact.getName());
                emailText.setText(contact.getNumberOrEmail());
                if (contact.isEmail) {phNumberPic.setVisibility(View.INVISIBLE);
                                        emailText.setVisibility(View.VISIBLE);
                                        nameText.setTextColor(Color.GREEN);}
                if (!contact.isEmail) {phNumberPic.setVisibility(View.VISIBLE);
                                        emailPic.setVisibility(View.INVISIBLE);
                                        nameText.setTextColor(Color.CYAN);}}
            }
        }
    }




