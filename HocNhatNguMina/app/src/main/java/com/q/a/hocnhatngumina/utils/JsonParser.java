package com.q.a.hocnhatngumina.utils;

import com.q.a.hocnhatngumina.models.HoiThoai;
import com.q.a.hocnhatngumina.models.LuyenNghe;
import com.q.a.hocnhatngumina.models.NguPhap;
import com.q.a.hocnhatngumina.models.TuVung;
import com.q.a.hocnhatngumina.models.ViDu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Van Quang on 10/16/2016.
 */

public class JsonParser {


    public static List<TuVung> getTuVungList(String jsonString) {
        List<TuVung> ls = new ArrayList<>();
        try {
            JSONArray tuVungs = new JSONArray(jsonString);
            for (int i = 0; i < tuVungs.length(); i++) {
                JSONObject oi = tuVungs.getJSONObject(i);
                TuVung tv = new TuVung();
                tv.setCode(oi.getInt("code"));
                tv.setId(oi.getInt("id"));
                tv.setKanji(oi.getString("kanji").length() == 0 ? "null" : oi.getString("kanji"));
                tv.setHiragana(oi.getString("hiragana").length() == 0 ? "null":oi.getString("hiragana") );
                tv.setKatakana(oi.getString("katakana").length() == 0 ? "null" : oi.getString("katakana"));
                tv.setMeanVI(oi.getString("giai_thich_vn").length() == 0 ? "null" : oi.getString("giai_thich_vn"));
                tv.setMeanEN(oi.getString("giai_thich_en").length() == 0 ? "null" : oi.getString("giai_thich_en"));
                tv.setMeanJP(oi.getString("giai_thich_jp").length() == 0 ? "null" : oi.getString("giai_thich_jp"));
                tv.setAudioName(oi.getString("audio"));
                tv.setRomaji(oi.getString("romaji"));
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
//                Log.i("tv", tv.toString());
                ls.add(tv);
            }

        } catch (Exception e){

            e.printStackTrace();
        }
        return ls;
    }

    public static List<NguPhap> getNguPhapList(String jsonString) {
        List<NguPhap> ls = new ArrayList<>();

        try {
            JSONObject rootJsonObject = new JSONObject(jsonString);
            JSONObject noiDung = rootJsonObject.getJSONObject("noi_dung");
            JSONArray nguPhaps = noiDung.getJSONArray("ngu_phap");
            for (int i = 0; i < nguPhaps.length(); i++) {
                JSONObject oi = nguPhaps.getJSONObject(i);
                NguPhap np = new NguPhap();
                np.setTieuDe(oi.getString("tieu_de"));
                JSONArray yNghias = oi.getJSONArray("y_nghia");
                for (int k = 0; k < yNghias.length(); k++) {
                    JSONObject ok = yNghias.getJSONObject(k);
                    np.getyNghias().add(ok.getString("yn"));
                }
                JSONArray cachDungs = oi.getJSONArray("cach_dung");
                for (int k = 0; k < cachDungs.length(); k++) {
                    JSONObject ok = cachDungs.getJSONObject(k);
                    np.getCachDungs().add(ok.getString("cd"));
                }
                JSONArray chuYs = oi.getJSONArray("chu_y");
                for (int k = 0; k < chuYs.length(); k++) {
                    JSONObject ok = chuYs.getJSONObject(k);
                    np.getChuYs().add(ok.getString("cy"));
                }
                JSONArray viDus = oi.getJSONArray("vi_du");
                for (int k = 0; k < viDus.length(); k++) {
                    JSONObject ok = viDus.getJSONObject(k);
                    ViDu vd = new ViDu();
                    vd.setVietCau(ok.getString("viet_cau"));
                    vd.setDichCau(ok.getString("dich_cau"));
                    np.getViDus().add(vd);
                }

                ls.add(np);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ls;
    }

    public static List<HoiThoai> getHoiThoaiList(String jsonString) {
        List<HoiThoai> ls = new ArrayList<>();

        return ls;
    }

    public static List<LuyenNghe> getLuyenNgheList(String jsonString) {
        List<LuyenNghe> ls = new ArrayList<>();

        return ls;
    }


}
