package traffic.traffic1.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import traffic.traffic1.R;

public class Topic18Activity extends AppCompatActivity {
    TextView mRoadState1;
    TextView mRoadState2;
    TextView mRoadState3;
    Switch mCarState1;
    Switch mCarState2;
    Switch mCarState3;
    String [] str = new String[]{"通畅","2","3","4","5"};
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnable,10*1000);
            try {
                loadData();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    Handler handler = new Handler();
    private void loadData() throws JSONException {
        String url  = "http://192.168.0.105:8080/transportservice/action/GetRoadStatus.do";
        JSONObject obj = new JSONObject();
        obj.put("RoadId",1);
        JsonObjectRequest roadRequst1 = new JsonObjectRequest(Request.Method.POST,
                url + "", obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.has("Status")) {
                    try {
                        int status = response.getInt("Status");
                        mRoadState1.setText(str[status]);
                        String url = "http://192.168.0.105:8080/transportservice/action/SetCarMove.do";
                        JSONObject obj = new JSONObject();
                        if (status>3) {
                            obj.put("CarId",1);
                            obj.put("CarAction","Stop");
                        } else {
                            obj.put("CarId",1);
                            obj.put("CarAction","Start");
                        }
                        JsonObjectRequest carRequst = new JsonObjectRequest(Request.Method.POST,
                                url + "", obj, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                mCarState1.setEnabled(mCarState1.isSaveEnabled());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                        App.getRequestQueue().add(carRequst);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        obj.remove("RoadId");
        obj.put("RoadId",2);
        JsonObjectRequest roadRequst2 = new JsonObjectRequest(Request.Method.POST,
                url + "", obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.has("Status")) {
                    try {
                        int status = response.getInt("Status");
                        mRoadState2.setText(str[status]);
                        String url = "http://192.168.0.105:8080/transportservice/action/SetCarMove.do";
                        JSONObject obj = new JSONObject();
                        if (status>3) {
                            obj.put("CarId",1);
                            obj.put("CarAction","Stop");
                        } else {
                            obj.put("CarId",1);
                            obj.put("CarAction","Start");
                        }
                        JsonObjectRequest carRequst = new JsonObjectRequest(Request.Method.POST,
                                url + "", obj, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                mCarState2.setEnabled(mCarState2.isSaveEnabled());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                        App.getRequestQueue().add(carRequst);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        obj.remove("RoadId");
        obj.put("RoadId",3);
        JsonObjectRequest roadRequst3 = new JsonObjectRequest(Request.Method.POST,
                url + "", obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.has("Status")) {
                    try {
                        int status = response.getInt("Status");
                        mRoadState3.setText(str[status]);
                        String url = "http://192.168.0.105:8080/transportservice/action/SetCarMove.do";
                        JSONObject obj = new JSONObject();
                        if (status>3) {
                            obj.put("CarId",1);
                            obj.put("CarAction","Stop");
                        } else {
                            obj.put("CarId",1);
                            obj.put("CarAction","Start");
                        }
                        JsonObjectRequest carRequst = new JsonObjectRequest(Request.Method.POST,
                                url + "", obj, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                mCarState3.setEnabled(mCarState3.isSaveEnabled());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                        App.getRequestQueue().add(carRequst);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getRequestQueue().add(roadRequst1);
        App.getRequestQueue().add(roadRequst2);
        App.getRequestQueue().add(roadRequst3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic18);
        mCarState1 = (Switch) findViewById(R.id.sw_18_car_state_1);
        mCarState2 = (Switch) findViewById(R.id.sw_18_car_state_2);
        mCarState3 = (Switch) findViewById(R.id.sw_18_car_state_3);
        mRoadState1 = (TextView) findViewById(R.id.txt_18_road_state_1);
        mRoadState2 = (TextView) findViewById(R.id.txt_18_road_state_2);
        mRoadState3 = (TextView) findViewById(R.id.txt_18_road_state_3);
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.post(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        App.getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }
}
