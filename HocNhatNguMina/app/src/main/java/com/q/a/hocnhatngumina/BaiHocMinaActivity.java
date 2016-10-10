package com.q.a.hocnhatngumina;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BaiHocMinaActivity extends AppCompatActivity {

    private ImageButton mMenuDSImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_hoc_mina);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((TextView)findViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.title_activity_bai_hoc_mina));

        mMenuDSImageButton = (ImageButton)findViewById(R.id.bt_menu_danh_sach_ct);
        mMenuDSImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container_fragment, new DanhSachBaiHocFragment()).commit();
        }

    }

    void showPopupWindow(View view) {
        PopupMenu popup = new PopupMenu(BaiHocMinaActivity.this, view);
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
                switch (item.getItemId()) {
                    case R.id.action_danh_sach_bai_hoc:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new DanhSachBaiHocFragment()).commit();
                        break;
                    case R.id.action_tu_vung:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new TuVungListFragment()).commit();
                        break;
                    case R.id.action_ngu_phap:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new NguPhapFragment()).commit();
                        break;
                    case R.id.action_hoi_thoai:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new HoiThoaiFragment()).commit();
                        break;
                    case R.id.action_luyen_nghe:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new LuyenNgheFragment()).commit();
                        break;
                    case R.id.action_bai_tap:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new BaiTapFragment()).commit();
                        break;
                    case R.id.action_kiem_tra:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new KiemTraFragment()).commit();
                        break;
                    case R.id.action_kanji:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new KanjiFragment()).commit();
                        break;
                }
                return true;
            }
        });
        popup.show();
    }

    public void showMenu(boolean isShow) {
        if (isShow)
        mMenuDSImageButton.setVisibility(View.VISIBLE);
        else mMenuDSImageButton.setVisibility(View.GONE);
    }


    public void changeTitle(String tile) {
        ((TextView)findViewById(R.id.toolbar_title)).setText(tile);
    }
}
