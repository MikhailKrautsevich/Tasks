package com.example.hometask_03_contacts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class Activity_add extends Activity {

    ImageButton btnBackFromAdd ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        btnBackFromAdd = findViewById(R.id.backFromAdd) ;
    }
}
