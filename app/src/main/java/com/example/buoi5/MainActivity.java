package com.example.buoi5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Contact(View view) {
        intent = new Intent(MainActivity.this,ContactActivity.class);
        startActivity(intent);
    }

    public void MediaStore(View view) {
        intent = new Intent(MainActivity.this,MediaStoreActivity.class);
        startActivity(intent);
    }

    public void CallLog(View view) {
        intent = new Intent(MainActivity.this,CallLogActivity.class);
        startActivity(intent);
    }
}