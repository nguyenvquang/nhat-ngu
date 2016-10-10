package com.q.a.hocnhatngumina;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.q.a.hocnhatngumina.adapters.NguPhapAdapter;
import com.q.a.hocnhatngumina.utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class NguPhapFragment extends Fragment {


    public NguPhapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_ngu_phap, container, false);
        int baiHocSo = PreferenceManager.getDefaultSharedPreferences(getContext()).getInt(Constants.BAI_HOC_SO, 1);
        ((BaiHocMinaActivity)getActivity()).changeTitle(getContext().getResources().getString(R.string.bai_hoc_so) + " " + baiHocSo);
        ListView lv = (ListView)rootView.findViewById(R.id.listView);
        lv.setAdapter(new NguPhapAdapter(getContext(), baiHocSo));
        return rootView;
    }

}
