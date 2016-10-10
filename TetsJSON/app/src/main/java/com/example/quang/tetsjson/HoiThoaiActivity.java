package com.example.quang.tetsjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class HoiThoaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoi_thoai);
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(new HoiThoaiAdapter(this));

    }
}
