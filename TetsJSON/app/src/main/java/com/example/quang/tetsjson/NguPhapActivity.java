package com.example.quang.tetsjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class NguPhapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngu_phap);
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(new NguPhapAdapter(this));
    }
}
