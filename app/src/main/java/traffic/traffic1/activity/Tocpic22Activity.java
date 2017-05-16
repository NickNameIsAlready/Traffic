package traffic.traffic1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import traffic.traffic1.R;

public class Tocpic22Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tocpic22);
    }
    private void loadData() {
        for(int i=1;i<=3;i++) {
            JSONObject ojb = new JSONObject();
            //"RoadId":1{"UserName":"Z0004"}
            try {
                ojb.put("UserNama", "Z0004");
                ojb.put("RoadId", i);
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.110:8080/transportservice/action/GetRoadStatus.do",
                    ojb, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            App.getRequestQueue().add(request);
            ojb = new JSONObject(); try {
                ojb.put("UserNama", "Z0004");
                ojb.put("TrafficLightId", i);
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
            request = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.110:8080/transportservice/action/GetTrafficLightConfigAction.do",
                    ojb, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            App.getRequestQueue().add(request);
        }
    }
}
