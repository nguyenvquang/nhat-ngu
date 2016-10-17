package com.q.a.hocnhatngumina;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import com.q.a.hocnhatngumina.adapters.AbstractBaseAdapter;
import com.q.a.hocnhatngumina.adapters.MenuAdapter;
import com.q.a.hocnhatngumina.database.AppDb;
import com.q.a.hocnhatngumina.database.DbHelper;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow(); // in Activity's onCreate() for instance
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }

        AppDb appDb = new AppDb(this);
//        for (int i = 1; i <= 50; i++) {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("bai_hoc", i);
//            appDb.getWritableDatabase().insert(AppDb.TB_BAI_HOC, null, contentValues);
//        }
        DbHelper  dbHelper = new DbHelper(this);

        ListView lv = (ListView)findViewById(R.id.lvMenu);

        lv.setAdapter(new MenuAdapter(this, new AbstractBaseAdapter.ListViewOnItemClickListener() {
            @Override
            public void onItemClick(final int postion, View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_min);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        switch (postion) {
                            case 0:
                                startActivity(new Intent(MainActivity.this, MenuBangChuCaiActivity.class));
                                break;
                            case 1:
                                startActivity(new Intent(MainActivity.this, BaiHocMinaActivity.class));
                                break;
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                view.startAnimation(animation);
            }
        }));


//        Cursor c = appDb.getWritableDatabase().rawQuery("select * from " + AppDb.TB_BAI_HOC, null);
//        if (c != null && c.moveToFirst()) {
//            do {
//                Log.i("tv", c.getInt(0) +" " + c.getString(AppDb.COL_TU_VUNG));
//            }while (c.moveToNext());
//        }
//        String lg = Locale.getDefault().getLanguage();

//        DownloadFile.start(this, link, Constants.PATH_AUDIO_ABC, link.hashCode() + ".mp3");

    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
