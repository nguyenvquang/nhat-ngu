package com.q.a.hocnhatngumina;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MenuBangChuCaiActivity extends AppCompatActivity {

    private static boolean isHiragana = true;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bang_chu_cai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container_fragment, new HiraganaFragment()).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem item1 = menu.add(1, 1, 0, getResources().getString(R.string.menu_che_do_binh_thuong));
        if (isHiragana) {
            item1.setTitle("K");
        } else {
            item1.setTitle("H");
        }

        item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        MenuItem item2 = menu.add(1, 2, 0, getResources().getString(R.string.menu_che_slide));
        item2.setIcon(getResources().getDrawable(R.drawable.bt_testing));
        item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            if (isHiragana) {
                isHiragana = false;
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new KatakanaFragment()).commit();
            } else {
                isHiragana = true;
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new HiraganaFragment()).commit();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    void showPopupWindow(View view) {
        PopupMenu popup = new PopupMenu(MenuBangChuCaiActivity.this, view);
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
        popup.getMenuInflater().inflate(R.menu.menu_bang_chu_cai, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(), "You Clicked : " + item.getTitle(),      Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        popup.show();
    }

}
