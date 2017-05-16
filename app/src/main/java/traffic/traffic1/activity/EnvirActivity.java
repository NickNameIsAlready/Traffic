package traffic.traffic1.activity;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import traffic.traffic1.R;
import traffic.traffic1.bean.AllSenseBean;
import traffic.traffic1.util.SomeTools;

/**
 * Created by feng on 17-5-11
 */

public class EnvirActivity extends BaseActivity{

    // TODO: 17-5-11 数据库信息存储
    // TODO: 17-5-11 只保存最近三分钟的信息
    @BindView(R.id.kongqiwendu) TextView kongqiwendu;
    @BindView(R.id.kongqishidu) TextView kongqishidu;
    @BindView(R.id.guangzhao) TextView guangzhao;
    @BindView(R.id.co2) TextView co2;
    @BindView(R.id.pm25) TextView pm25;
    @BindView(R.id.daoluzhuangtai) TextView daoluzhuangtai;
    @Override
    protected void initEvent() {
        //When I wrote this, only God and I understood what I was doing
        //Now, God only knows
        JsonObjectRequest getAllSense = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.110:8080/transportservice/action/GetAllSense.do"
                , null, response -> {
            AllSenseBean bean = SomeTools.gsonParse(AllSenseBean.class, response.toString());
            if (bean.getRESULT().equals("S")){
                kongqishidu.setText(String.valueOf(bean.getHumidity()));
                kongqiwendu.setText(String.valueOf(bean.getTemperature()));
                guangzhao.setText(String.valueOf(bean.getLightIntensity()));
                co2.setText(String.valueOf(bean.getCo2()));
                pm25.setText(String.valueOf(bean.get_$Pm25292()));
            }
            else
                SomeTools.showToast(getString(R.string.lajiwar));
        }, error -> SomeTools.showToast("网络错误"));

        JsonObjectRequest GetRoadStatus = null;
        try {
            GetRoadStatus = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.105:8080/transportservice/action/GetRoadStatus.do"
                    ,new JSONObject().put("RoadId", 1), response -> {
                try {
                    if (response.getString("RESULT").equals("S")){
                        daoluzhuangtai.setText(response.getString("Status"));
                    }
                    else
                        SomeTools.showToast("超时");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> SomeTools.showToast("网络错误"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest finalGetRoadStatus = GetRoadStatus;
        Observable.interval(0, 5, TimeUnit.SECONDS).subscribe(v-> {
            App.getRequestQueue().add(getAllSense);
            App.getRequestQueue().add(finalGetRoadStatus);
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_envir;
    }
}
