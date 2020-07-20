package com.example.buoi5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MediaStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_store);
        ListView lv = findViewById(R.id.lv);
        ArrayList<String> list = new ArrayList<String>();
        String []projection = {
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATE_ADDED,
                MediaStore.MediaColumns.MIME_TYPE
        };
        CursorLoader cursorLoader = new CursorLoader(this, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,null,null,null);

        Cursor cursor = cursorLoader.loadInBackground();
        cursor.moveToFirst();
        String S = "";
        while (!cursor.isAfterLast()) {
            S = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            cursor.moveToNext();
            list.add(S);
        }
            cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        lv.setAdapter(adapter);
    }
}