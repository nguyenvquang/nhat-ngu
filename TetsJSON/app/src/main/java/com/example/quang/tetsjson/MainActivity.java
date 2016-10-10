package com.example.quang.tetsjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView tv = (TextView)findViewById(R.id.tv);
//        try {
//            InputStream is = getAssets().open("bai1.txt");
//            String text = "";
//            char[] charRead = new char[4096];
//            InputStreamReader isr = new InputStreamReader(is);
//            int length = 0;
//
//            while ((length = isr.read(charRead)) > -1) {
//                String cp = String.copyValueOf(charRead, 0, length);
//                text += cp;
//                charRead = new char[4096];
//                Log.i("txt", cp);
//            }
//            isr.close();
//            Log.i("data", text);
//            tv.setText(text);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        startActivity(new Intent(MainActivity.this, NguPhapActivity.class));
    }
}
