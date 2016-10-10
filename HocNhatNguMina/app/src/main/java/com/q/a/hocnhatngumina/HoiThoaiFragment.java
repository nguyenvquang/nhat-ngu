package com.q.a.hocnhatngumina;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.q.a.hocnhatngumina.adapters.HoiThoaiAdapter;
import com.q.a.hocnhatngumina.utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class HoiThoaiFragment extends Fragment {

    private ListView mListView;
    private HoiThoaiAdapter hoiThoaiAdapter;

    public HoiThoaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=  inflater.inflate(R.layout.fragment_hoi_thoai, container, false);
        int baiHocSo = PreferenceManager.getDefaultSharedPreferences(getContext()).getInt(Constants.BAI_HOC_SO, 1);
        ((BaiHocMinaActivity)getActivity()).changeTitle(getContext().getResources().getString(R.string.bai_hoc_so) + " " + baiHocSo);
        mListView = (ListView)rootView.findViewById(R.id.listView);
        hoiThoaiAdapter = new HoiThoaiAdapter(getContext(), baiHocSo);
        final View header = LayoutInflater.from(getContext()).inflate(R.layout.media_item, null);
        ImageButton play = (ImageButton)header.findViewById(R.id.bt_setting);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View contentView = LayoutInflater.from(getContext()).inflate(R.layout.item_popup_view, null);
                builder.setView(contentView);
                builder.setCancelable(true);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                Button b1 = (Button)contentView.findViewById(R.id.bt_an_hien_nghia);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        hoiThoaiAdapter.setmShowMean();
                        hoiThoaiAdapter.notifyDataSetChanged();
                    }
                });
                Button b2 = (Button)contentView.findViewById(R.id.bt_an_hien_romaji);
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        hoiThoaiAdapter.setmShowMean();
                        hoiThoaiAdapter.notifyDataSetChanged();
                    }
                });

                alertDialog.show();
            }
        });
        mListView.addHeaderView(header);
        mListView.setAdapter(hoiThoaiAdapter);
        return rootView;
    }

}
