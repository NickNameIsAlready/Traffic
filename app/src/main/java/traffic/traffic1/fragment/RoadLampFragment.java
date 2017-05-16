package traffic.traffic1.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import traffic.traffic1.R;
import traffic.traffic1.util.Constants;

/**
 * Created by asdf on 2017/5/16.
 */

public class RoadLampFragment extends Fragment implements View.OnClickListener {
    private ImageView state_lamp;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private String Model="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.item_lamp, null);
        state_lamp = (ImageView) inflate.findViewById(R.id.state_lamp);
        btn1 = (Button) inflate.findViewById(R.id.btn1);
        btn2 = (Button) inflate.findViewById(R.id.btn2);
        btn3 = (Button) inflate.findViewById(R.id.btn3);
        btn4 = (Button) inflate.findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        GetLampState();
        return inflate;
    }

    private void GetLampState() {
        JSONObject object = new JSONObject();
        try {
            object.put("RoadLightId", 1);
            object.put("UserName", "Z0004");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.GetRoadLightStatus, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String data = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String result = jsonObject.getString("RESULT");
                    if (result.equals("S")) {
                        String status = jsonObject.getString("Status");
                        if (status.equals("Close")) {
                            state_lamp.setImageResource(R.drawable.main_community);
                        } else {
                            state_lamp.setImageResource(R.drawable.main_community_press);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //手动
                Model="Manual";
                setModel(Model);

                break;
            case R.id.btn2:
                //自动
                Model="Auto";
                setModel(Model);
                break;
            case R.id.btn3:
                if(Model.equals("Auto")){
                    Toast.makeText(getActivity(),"必须先将路灯模式设置为手动",Toast.LENGTH_SHORT).show();
                }else{
                    setToggle("Open");
                }
                break;
            case R.id.btn4:
                if(Model.equals("Auto")){
                    Toast.makeText(getActivity(),"必须先将路灯模式设置为手动",Toast.LENGTH_SHORT).show();
                }else{
                    setToggle("Close");
                }
                break;

        }
    }

    private void setToggle(String open) {
        //设置路灯开关
        JSONObject object = new JSONObject();
        try {
            object.put("RoadLightId", 1);
            object.put("Action", open);
            object.put("UserName", "Z0004");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.SetRoadLightStatusAction, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String data = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String result = jsonObject.getString("RESULT");
                    if(result.equals("S")){
                        if(open.equals("Open")){
                            btn3.setBackgroundColor(Color.GREEN);
                            btn4.setBackgroundColor(Color.WHITE);
                            Toast.makeText(getActivity(),"路灯启动",Toast.LENGTH_SHORT).show();
                            state_lamp.setImageResource(R.drawable.main_community_press);
                        }else{
                            Toast.makeText(getActivity(),"路灯关闭",Toast.LENGTH_SHORT).show();
                            btn3.setBackgroundColor(Color.WHITE);
                            btn4.setBackgroundColor(Color.GREEN);
                            state_lamp.setImageResource(R.drawable.main_community);
                        }
                    }else{
                        Toast.makeText(getActivity(),"网络连接出错",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);


    }

    private void setModel(String manual) {
        JSONObject object = new JSONObject();
        try {
            object.put("ControlMode", manual);
            object.put("UserName", "Z0004");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.SetRoadLightControlMode, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String data = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String result = jsonObject.getString("RESULT");
                    if(result.equals("S")){
                        if(manual.equals("Manual")){
                            btn1.setBackgroundColor(Color.GREEN);
                            btn2.setBackgroundColor(Color.WHITE);
                            Toast.makeText(getActivity(),"手动模式启动",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(),"自动模式启动",Toast.LENGTH_SHORT).show();
                            btn1.setBackgroundColor(Color.WHITE);
                            btn2.setBackgroundColor(Color.GREEN);
                        }
                    }else{
                        Toast.makeText(getActivity(),"网络连接出错",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);


    }
}
