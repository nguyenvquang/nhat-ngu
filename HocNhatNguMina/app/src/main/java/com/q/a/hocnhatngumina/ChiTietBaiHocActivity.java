package com.q.a.hocnhatngumina;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ChiTietBaiHocActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bai_hoc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((TextView)findViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.title_activity_chi_tiet_bai_hoc));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.bt_menu_danh_sach_ct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showMenuDialog();
                showPopupWindow(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuItem item1 = menu.add(1, 1, 0, getResources().getString(R.string.menu_che_do_binh_thuong));
//        item1.setIcon(getResources().getDrawable(R.drawable.bt_menu));
//        item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == 1) {
//            showMenuDialog();
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    private void showMenuDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_menu_bai_hoc, null);
        builder.setView(view);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        WindowManager.LayoutParams wlmp = alertDialog.getWindow().getAttributes();
        wlmp.gravity = Gravity.BOTTOM;
//        alertDialog.getWindow().getAttributes().windowAnimations = R.style.CustomAnimationsGrow;
        alertDialog.setCancelable(true);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.go_up);
        view.startAnimation(animation);
        alertDialog.show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(ChiTietBaiHocActivity.this, "Land", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(ChiTietBaiHocActivity.this, "Por", Toast.LENGTH_SHORT).show();
        }
    }

    void showPopupWindow(View view) {
        PopupMenu popup = new PopupMenu(ChiTietBaiHocActivity.this, view);
        try {
            Field[] fields = popup.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popup);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        popup.getMenuInflater().inflate(R.menu.menu_chi_tiet, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(), "You Clicked : " + item.getTitle(),      Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        popup.show();
    }

}
