package traffic.traffic1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import traffic.traffic1.R;
import traffic.traffic1.bean.Lamp;
import traffic.traffic1.util.Constants;

/**
 * Created by asdf on 2017/5/16.
 */

public class TrafficFragment extends Fragment {
    private ListView listview_traffic;
    private boolean isflag;
    private Myadapter myadapter;
    private Timer timer;
    private ArrayList<Lamp> lamps;
    private ArrayList<String> strings;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.traffic, null);

        listview_traffic = (ListView) inflate.findViewById(R.id.listview_traffic);
        myadapter = new Myadapter();
        listview_traffic.setAdapter(myadapter);
        GetData();


        return inflate;
    }

    private void GetData() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                GetLampData();
            }
        };
        timer.schedule(timerTask, 100, 1000);
    }

    private void GetLampData() {
        lamps = new ArrayList<>();
        strings = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            JSONObject object = new JSONObject();
            try {
                object.put("TrafficLightId", i);
                object.put("UserName", "Z0004");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.GetTrafficLightConfigAction, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String data = response.toString();
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        String result = jsonObject.getString("RESULT");
                        if (result.equals("S")) {
                            Gson gson = new Gson();
                            Lamp lamp = gson.fromJson(data, Lamp.class);
                            lamps.add(lamp);
                        } else {
                            Toast.makeText(getActivity(), "网络连接出粗", Toast.LENGTH_SHORT).show();
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
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //请求当前红绿灯状态
            JSONObject object1 = new JSONObject();
            try {
                object1.put("TrafficLightId", "1-" + i);
                object1.put("UserName", "Z0004");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestQueue requestQueue1 = Volley.newRequestQueue(getActivity());
            JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, Constants.GetTrafficLightNowStatus, object1, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String data = response.toString();
                    try {
                        JSONObject jb = new JSONObject(data);
                        String result = jb.getString("RESULT");
                        if (result.equals("S")) {
                            String status = jb.getString("Status");
                            String time = jb.getString("Time");
                            strings.add(status);

                        }
                        else{
                            Toast.makeText(getActivity(), "网络连接出粗", Toast.LENGTH_SHORT).show();
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
            requestQueue1.add(jsonObjectRequest1);
        }
        myadapter.notifyDataSetChanged();


    }

    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = View.inflate(getActivity(), R.layout.item_traffic, null);
            TextView tv_road = (TextView) inflate.findViewById(R.id.tv_road);
            TextView tv_roadInfo = (TextView) inflate.findViewById(R.id.tv_roadInfo);
            TextView tv_horInfo = (TextView) inflate.findViewById(R.id.tv_horInfo);
            TextView tv_verInfo = (TextView) inflate.findViewById(R.id.tv_verInfo);
            ImageView iv_hor = (ImageView) inflate.findViewById(R.id.iv_hor);
            ImageView iv_ver = (ImageView) inflate.findViewById(R.id.iv_ver);
            Button btn_hor = (Button) inflate.findViewById(R.id.btn_hor);
            Button btn_ver = (Button) inflate.findViewById(R.id.btn_ver);
            if (!isflag) {
                tv_road.setText("路口" + (position + 1));
                tv_roadInfo.setText("配置信息   绿灯50秒  黄灯6秒   红灯20秒");
                tv_horInfo.setText("横向状态   红灯3秒");
                tv_verInfo.setText("纵向状态   绿灯3秒");
                iv_hor.setImageResource(R.drawable.redshape);
                iv_ver.setImageResource(R.drawable.greenshape);
                if (position == 5) {
                    isflag = true;
                }
            } else {
                //请求数据
                if(lamps!=null&&lamps.size()>0&&strings.size()>0&&strings!=null){
                    Lamp lamp = lamps.get(position);
                    if(strings.get(position).equals("Red")){
                        iv_hor.setImageResource(R.drawable.redshape);
                        iv_ver.setImageResource(R.drawable.redshape);
                        tv_horInfo.setText("横向状态   红灯");
                        tv_verInfo.setText("横向状态   红灯");


                    }else if(strings.get(position).equals("Green")){
                        iv_hor.setImageResource(R.drawable.greenshape);
                        iv_ver.setImageResource(R.drawable.greenshape);
                        tv_horInfo.setText("横向状态   绿灯");
                        tv_verInfo.setText("横向状态   绿灯");
                    }else if(strings.get(position).equals("YellowTime")){
                        iv_hor.setImageResource(R.drawable.yellowshape);
                        iv_ver.setImageResource(R.drawable.yellowshape);
                        tv_horInfo.setText("横向状态   黄灯");
                        tv_verInfo.setText("横向状态   黄灯");
                    }



                    tv_horInfo.setText("横向状态   红灯"+strings.get(position)+"秒");
                    tv_roadInfo.setText("配置信息   绿灯" + lamp.getGreenTime() + "秒黄灯" + lamp.getYellowTime() + "秒红灯" + lamp.getRedTime() + "秒");
                }
            }
            return inflate;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
