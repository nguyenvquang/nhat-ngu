package com.q.a.hocnhatngumina;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChiTietBHFragment extends Fragment {


    public ChiTietBHFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        View toolbarView = LayoutInflater.from(context).inflate(R.layout.toolbar_view, null);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setCustomView(toolbarView);
        Toast.makeText(context, "Attach", Toast.LENGTH_SHORT).show();
    }
}
