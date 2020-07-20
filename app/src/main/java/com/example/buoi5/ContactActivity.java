package com.example.buoi5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        String path = "content://contacts/people";
        Uri uri = Uri.parse(path);
        CursorLoader cursorLoader = new CursorLoader(this, ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        ArrayList<String> list = new ArrayList<String>();
        if (ContextCompat.checkSelfPermission(ContactActivity.this,
                Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED) {
            Cursor cursor = cursorLoader.loadInBackground();
            if (cursor.getCount() > 0) {
                Toast.makeText(this, "Có danh bạ: " + cursor.getCount(), Toast.LENGTH_SHORT).show();
                cursor.moveToFirst();
                while (cursor.isAfterLast()==false){
                    String S = " ";
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    cursor.moveToNext();
                    list.add(name+". SĐT: "+number);

                }
                cursor.close();
            } else {
                Toast.makeText(this, "Không có danh bạ???", Toast.LENGTH_SHORT).show();
            }
        }else {
            ActivityCompat.requestPermissions(ContactActivity.this,new String[]{Manifest.permission.READ_CONTACTS},999);
        }
        ListView lv = findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        lv.setAdapter(adapter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Cảm ơn bạn đã đồng ý!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Không cho xem thì thôi!!!", Toast.LENGTH_SHORT).show();
        }
    }
}