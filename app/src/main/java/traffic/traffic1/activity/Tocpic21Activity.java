package traffic.traffic1.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import traffic.traffic1.R;

public class Tocpic21Activity extends AppCompatActivity {
    int pmMin = 0;
    int pmMax = 100;
    int coMin = 0;
    int coMax = 80;
    int tempMin = 15;
    int tempMax = 18;
    TextView what;
    TextView pmTxt;
    TextView temp;
    TextView hum;
    String [] strs = new String[]{
         "减少户外活动",
            "可适当进行户外运动",
            "快出去走走吧"}; private Handler mHandler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            loadData();
            mHandler.postDelayed(runnable,3*1000);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.post(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runnable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tocpic21);
        what = (TextView)findViewById(R.id.txt_21_what);
        pmTxt = (TextView)findViewById(R.id.txt_21_pm);
        temp = (TextView)findViewById(R.id.txt_21_temp);
        hum = (TextView)findViewById(R.id.txt_21_hum);
    }
    private  void loadData() {
        JSONObject ojb = new JSONObject();
        //{"UserName":"Z0004"}
        try {
            ojb.put("UserNama","Z0004");
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.110:8080/transportservice/action/GetAllSense.do",
                ojb, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.has("co2"))
                try {/*
                    {"RESULT":"S","ERRMSG":"成功","pm2.5":8,"co2":5919,"LightIntensity":1711,
                            "humidity":44,"temperature":28}*/
                    int pm = response.getInt("pm2.5");
                    int co2 = response.getInt("co2");
                    int LightIntensity = response.getInt("LightIntensity");
                    int humidity = response.getInt("humidity");
                    int temperature = response.getInt("temperature");
                    int i = 0;
                    if(pm>pmMin&&pm<pmMax) i++;
                    if(co2>coMin&&co2<coMax) i++;
                    if(temperature>tempMin&&temperature<tempMax) i++;
                    if(i==1|i==2) {
                        what.setText(strs[1]);
                    } else if(i==0){
                        what.setText(strs[i]);
                    } else {
                        what.setText(strs[2]);
                    }
                    pmTxt.setText(pm+"");
                    hum.setText(humidity+"");
                    temp.setText(temperature+"");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getRequestQueue().add(request);
    }
}
