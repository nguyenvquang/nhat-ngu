package com.q.a.hocnhatngumina;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MauCauActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mau_cau);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ListView lv = (ListView)findViewById(R.id.list_view);
//        String[] data = new String[100];
//        for(int i = 0; i < 100; i++){
//            data[i] = "Item " + i;
//        }
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
//        lv.setAdapter(new MauCauApdater(this, data, lv));
        String data = "";
        try {

            InputStream inputStream = getAssets().open("data/data.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int length = 0;
            char[] read = new char[4096];

            while ((length = inputStreamReader.read(read)) > 0) {
                String cp = String.copyValueOf(read, 0, length);
                read = new char[4096];
                Log.i("txt", cp);
                data += cp;
                Toast.makeText(this, cp, Toast.LENGTH_SHORT).show();
            }
            inputStreamReader.close();
            TextView tv = (TextView)findViewById(R.id.txtShow);
            tv.setText(data);
            Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
         while (true) {
             Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
         }
        }
        Log.i("data", data);
    }

}
