package com.q.a.hocnhatngumina;


import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.q.a.hocnhatngumina.adapters.HoiThoaiAdapter;
import com.q.a.hocnhatngumina.utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class HoiThoaiFragment extends Fragment {

    private ListView mListView;
    private HoiThoaiAdapter hoiThoaiAdapter;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "Nhan su kien", Toast.LENGTH_SHORT).show();
            if (intent.getAction().equals(Constants.ACTION_INTENT_MEDIA_PLAY)) {
                Toast.makeText(context, "Nhan su kien play", Toast.LENGTH_SHORT).show();
            }
            if (intent.getAction().equals(Constants.ACTION_INTENT_MEDIA_PAUSE)) {
                Toast.makeText(context, "Nhan su kien páue", Toast.LENGTH_SHORT).show();
            }
            if (intent.getAction().equals(Constants.ACTION_INTENT_MEDIA_STOP)) {
//                Toast.makeText(context, "Nhan su kien stop", Toast.LENGTH_SHORT).show();
                context.stopService(new Intent(context, PlayMediaService.class));
                NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(PlayMediaService.NOTIFICATTON_ID);
            }
        }
    };

    private IntentFilter mIntentFilter;

    public HoiThoaiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Constants.ACTION_INTENT_MEDIA_PLAY);
        mIntentFilter.addAction(Constants.ACTION_INTENT_MEDIA_PAUSE);
        mIntentFilter.addAction(Constants.ACTION_INTENT_MEDIA_STOP);
        getContext().registerReceiver(mBroadcastReceiver, mIntentFilter);
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
        ImageButton play = (ImageButton)header.findViewById(R.id.bt_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayMediaService.class);
                intent.putExtra(Constants.URL_FILE_MEDIA, "null");
                getActivity().startService(intent);
            }
        });
        ImageButton setting = (ImageButton)header.findViewById(R.id.bt_setting);
        setting.setOnClickListener(new View.OnClickListener() {
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

                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_tranparent);


                alertDialog.show();
            }
        });

        mListView.addHeaderView(header);
        mListView.setAdapter(hoiThoaiAdapter);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
