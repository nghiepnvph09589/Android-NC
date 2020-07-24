package com.example.buoi5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CallLogActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        listView = findViewById(R.id.lv);
        ArrayList<String> list = new ArrayList<String>();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

        }else {
            ActivityCompat.requestPermissions(CallLogActivity.this,new String[]{Manifest.permission.READ_CONTACTS},999);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String a = CallLog.Calls.DATE;
        String b = CallLog.Calls.NUMBER;
        String c = CallLog.Calls.DURATION;
        String[] projection = new String[]{
                a,b,c
        };
        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, projection, CallLog.Calls.DURATION + "<?", new String[]{"30"}, CallLog.Calls.DATE + " Asc");
        cursor.moveToFirst();
        String S = "";
        while (cursor.isAfterLast()==false){
            String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            int date = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DATE))*1000;
            String n = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
//            String d = simpleDateFormat.format(date);
            cursor.moveToNext();
            list.add(number+"_"+n);
        }
        cursor.close();
        Toast.makeText(this,S,Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        listView.setAdapter(adapter);

    }
}