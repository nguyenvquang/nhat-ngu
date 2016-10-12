package com.q.a.hocnhatngumina;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.q.a.hocnhatngumina.adapters.AbstractBaseAdapter;
import com.q.a.hocnhatngumina.adapters.DanhSachBaiHocAdapter;
import com.q.a.hocnhatngumina.models.TuVung;
import com.q.a.hocnhatngumina.models.ViDu;
import com.q.a.hocnhatngumina.utils.Constants;
import com.q.a.hocnhatngumina.utils.DanhSachBaiHoc;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DanhSachBaiHocFragment extends Fragment {

    private ListView mListView;

    public DanhSachBaiHocFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_danh_sach_bai_hoc, container, false);
        ((BaiHocMinaActivity)getActivity()).changeTitle("Danh sách bài học");
        ((BaiHocMinaActivity)getActivity()).showMenu(false);
        mListView = (ListView) rootView.findViewById(R.id.grid_view);
        mListView.setAdapter(new DanhSachBaiHocAdapter(getContext(), new AbstractBaseAdapter.ListViewOnItemClickListener() {
            @Override
            public void onItemClick(final int postion, View view) {

                    Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_min);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            final TuVungListFragment tuVungListFragment  = new TuVungListFragment();
                            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putInt(Constants.BAI_HOC_SO, postion + 1);
                            editor.commit();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    Toast.makeText(getContext(), "" + postion, Toast.LENGTH_SHORT);
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, tuVungListFragment).commit();
                                }
                            });
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    view.startAnimation(animation);

            }
        }));

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getContext(), "Destroy", Toast.LENGTH_SHORT).show();
        mListView = null;
    }

    private List<Object> read(int baiHocSo) {
        String name = DanhSachBaiHoc.layTenBaiHoc(baiHocSo);
        if (name != null) {
            try {
                List<Object> ls = new ArrayList<>();
                InputStream inputStream = getContext().getAssets().open("data/data.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int length = 0;
                char[] buffer = new char[4096];
                String data = "";
                while ((length = inputStreamReader.read(buffer)) > -1) {
                    String cp = String.copyValueOf(buffer, 0, length);
                    data += cp;
                    buffer = new char[4096];
                }
                inputStreamReader.close();
                JSONObject root = new JSONObject(data);
                JSONObject noiDung = root.getJSONObject("noi_dung");
                JSONArray tuVungs = noiDung.getJSONArray("tu_vung");
                for (int i = 0; i < tuVungs.length(); i++) {
                    JSONObject oi = tuVungs.getJSONObject(i);
                    TuVung tv = new TuVung();
                    tv.setCode(1);
                    tv.setId(i);
                    tv.setKanji(oi.getString("kanji").length() == 0 ? "null" : oi.getString("kanji"));
                    tv.setHiragana(oi.getString("hiragana").length() == 0 ? "null":oi.getString("hiragana") );
                    tv.setKatakana(oi.getString("katakana").length() == 0 ? "null" : oi.getString("katakana"));
                    tv.setMeanVI(oi.getString("giai_thich").length() == 0 ? "null" : oi.getString("giai_thich"));
                    tv.setAudioName(oi.getString("audio"));
                    tv.setRomaji("null");
                    tv.setTenBoKanji(oi.getString("bo_kanji"));
                    tv.setBoKanji(oi.getString("bo_kanji"));
                    JSONArray viDus = oi.getJSONArray("vi_du");
                    for (int k = 0; k < viDus.length(); k++) {
                        JSONObject ok = viDus.getJSONObject(k);
                        ViDu vd = new ViDu();
                        vd.setVietCau(ok.getString("viet_cau"));
                        vd.setDichCau(ok.getString("dich_cau"));
                        tv.getViDus().add(vd);
                    }
                    ls.add(tv);

                }
                return ls;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }



}
