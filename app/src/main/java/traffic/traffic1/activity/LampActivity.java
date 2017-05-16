package traffic.traffic1.activity;

import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import traffic.traffic1.R;
import traffic.traffic1.util.Constants;

/**
 * Created by asdf on 2017/5/15.
 */

public class LampActivity extends BaseActivity {
    @BindView(R.id.model)
    Switch model;
    @BindView(R.id.toggle)
    Switch toggle;
    @BindView(R.id.sp)
    Spinner sp;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.sp_toggleId)
    Spinner sp_toggleId;
    @BindView(R.id.btn_confirm)
    Button btn_confirm;

    private ArrayList<Integer> list;

    @Override
    protected void initEvent() {
        model.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //自动
                    toggle.setEnabled(false);
                    setAudio();
                } else {
                    //手动
                    toggle.setEnabled(true);
                    setHander();
                }
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) sp_toggleId.getSelectedItemId();
                boolean checked = toggle.isChecked();
                setlamp(id, checked);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) (sp.getSelectedItemId() + 1);
                FindLampById(id);


            }
        });
    }

    private void FindLampById(int id) {
        JSONObject object = new JSONObject();
        try {
            object.put("RoadLightId",id);
            object.put("UserName","Z0004");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(LampActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.GetRoadLightStatus, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String data = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String result = jsonObject.getString("RESULT");
                    if(result.equals("S")){
                        String status = jsonObject.getString("Status");
                        Toast.makeText(LampActivity.this,id+"路灯的状态:"+status,Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LampActivity.this,"网络连接出错",Toast.LENGTH_SHORT).show();
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
        requestQueue.add(jsonObjectRequest );


    }

    private void setlamp(int id, boolean checked) {
        JSONObject object = new JSONObject();
        try {
            object.put("RoadLightId", id+1);
            if (checked) {
                object.put("Action", "Open");
            } else {
                object.put("Action", "Close");
            }
            object.put("UserName", "Z0004");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(LampActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.SetRoadLightStatusAction, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String data = response.toString();
                try {
                    JSONObject object1 = new JSONObject(data);
                    String result = object1.getString("RESULT");
                    if(result.equals("S")){
                        if(checked){
                            Toast.makeText(LampActivity.this,id+1+"设置成功为:"+"打开",Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                 if(toggle.isChecked()){
                                     setlamp(id,false);
                                     toggle.setChecked(false);
                                 }
                                }
                            },10000);
                        }else{
                            Toast.makeText(LampActivity.this,id+1+"设置成功为:"+"关闭",Toast.LENGTH_SHORT).show();
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

    private void setHander() {
        JSONObject object = new JSONObject();
        try {
            object.put("ControlMode", "Manual");
            object.put("UserName", "Z0004");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(LampActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.SetRoadLightControlMode, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String data = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String result = jsonObject.getString("RESULT");
                    if (result.equals("S")) {
                        Toast.makeText(LampActivity.this, "手动路灯设置模式启动", Toast.LENGTH_SHORT).show();
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

    private void setAudio() {
        JSONObject object = new JSONObject();
        try {
            object.put("ControlMode", "Auto");
            object.put("UserName", "Z0004");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(LampActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.SetRoadLightControlMode, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String data = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String result = jsonObject.getString("RESULT");
                    if (result.equals("S")) {
                        Toast.makeText(LampActivity.this, "自动路灯设置模式启动", Toast.LENGTH_SHORT).show();
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
    protected void initView() {
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(LampActivity.this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp_toggleId.setAdapter(adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.lamp;
    }
}
