package com.q.a.hocnhatngumina;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import com.q.a.hocnhatngumina.adapters.AbstractBaseAdapter;
import com.q.a.hocnhatngumina.adapters.MenuAdapter;
import com.q.a.hocnhatngumina.database.DbHelper;

import java.util.Locale;

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


        DbHelper db = new DbHelper(this);
//        Cursor c = db.open().rawQuery("select * from tu_vung", null);
//        Toast.makeText(this,  "asdfasdas " + c.getColumnCount(), Toast.LENGTH_SHORT).show();
//        if (c != null) {
//            for (int i = 0; i < c.getColumnCount(); i++) {
////                Toast.makeText(this, c.getColumnName(i), Toast.LENGTH_SHORT).show();
//                Log.i("pp", c.getColumnName(i));
//            }
//        }
//        if (c != null && c.moveToFirst()) {
//            do {
//                Toast.makeText(this, c.getInt(0) + "", Toast.LENGTH_SHORT).show();
//            }while (c.moveToNext());
//        }
        String lg = Locale.getDefault().getLanguage();

//        DownloadFile.start(this, link, Constants.PATH_AUDIO_ABC, link.hashCode() + ".mp3");

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuItem notifyMenuItem = menu.add(0, 1, 0, "Notification");
//        notifyMenuItem.setIcon(R.drawable.bt_notification);
//        notifyMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//
//        MenuItem shareMenuItem = menu.add(0, 2, 0, "Share");
//        shareMenuItem.setIcon(R.drawable.bt_share);
//        shareMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//
//        MenuItem rateMenuItem = menu.add(0, 3,0, "Rating");
//        rateMenuItem.setIcon(R.drawable.bt_rating);
//        rateMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == 1) {
//            mDrawerLayout.openDrawer(GravityCompat.END);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
