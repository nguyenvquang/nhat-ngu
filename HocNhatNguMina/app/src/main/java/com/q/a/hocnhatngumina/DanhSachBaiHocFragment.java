package com.q.a.hocnhatngumina;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.q.a.hocnhatngumina.adapters.AbstractBaseAdapter;
import com.q.a.hocnhatngumina.adapters.DanhSachBaiHocAdapter;
import com.q.a.hocnhatngumina.utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class DanhSachBaiHocFragment extends Fragment {


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
        ListView gridView = (ListView) rootView.findViewById(R.id.grid_view);
        gridView.setAdapter(new DanhSachBaiHocAdapter(getContext(), new AbstractBaseAdapter.ListViewOnItemClickListener() {
            @Override
            public void onItemClick(int postion, View view) {
                TuVungListFragment tuVungListFragment  = new TuVungListFragment();
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(Constants.BAI_HOC_SO, postion + 1);
                editor.commit();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, tuVungListFragment).commit();
            }
        }));
        return rootView;
    }

}