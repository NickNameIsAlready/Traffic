package traffic.traffic1.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import traffic.traffic1.R;
import traffic.traffic1.util.Constants;

/**
 * Created by asdf on 2017/5/15.
 */

public class ControlFragment extends Fragment implements View.OnClickListener{
    private Button btn_start1;
    private Button btn_start2;
    private Button btn_start3;
    private Button btn_start4;

    private Button btn_stop1;
    private Button btn_stop2;
    private Button btn_stop3;
    private Button btn_stop4;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_control, null);
        btn_start1=(Button)inflate.findViewById(R.id.btn_start1);
        btn_start2=(Button)inflate.findViewById(R.id.btn_start2);
        btn_start3=(Button)inflate.findViewById(R.id.btn_start3);
        btn_start4=(Button)inflate.findViewById(R.id.btn_start4);

        btn_stop1=(Button)inflate.findViewById(R.id.btn_stop1);
        btn_stop2=(Button)inflate.findViewById(R.id.btn_stop2);
        btn_stop3=(Button)inflate.findViewById(R.id.btn_stop3);
        btn_stop4=(Button)inflate.findViewById(R.id.btn_stop4);


        btn_start1.setOnClickListener(this);
        btn_start2.setOnClickListener(this);
        btn_start3.setOnClickListener(this);
        btn_start4.setOnClickListener(this);

        btn_stop1.setOnClickListener(this);
        btn_stop2.setOnClickListener(this);
        btn_stop3.setOnClickListener(this);
        btn_stop4.setOnClickListener(this);


        return inflate;
    }

    @Override
    public void onClick(View v) {
        int position;
        int stopposition;
        switch (v.getId()){
            case R.id.btn_start1:
                position=1;
                start(position);
                break;
            case R.id.btn_start2:
                position=2;
                start(position);
                break;
            case R.id.btn_start3:
                position=3;
                start(position);
                break;
            case R.id.btn_start4:
                position=4;
                start(position);
                break;


            case R.id.btn_stop1:
                stopposition=1;
                stopcar(stopposition);
                break;
            case R.id.btn_stop2:
                stopposition=2;
                stopcar(stopposition);
                break;
            case R.id.btn_stop3:
                stopposition=3;
                stopcar(stopposition);
                break;
            case R.id.btn_stop4:
                stopposition=4;
                stopcar(stopposition);
                break;


        }
    }

    private void stopcar(int position) {
        JSONObject object = new JSONObject();
        try {
            object.put("CarId",position);
            object.put("CarAction","Stop");
            object.put("UserName","Z0004");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.SetCarMove, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String data = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String result = jsonObject.getString("RESULT");
                    if(result.equals("S")){
                        Toast.makeText(getActivity(),position+"号小车停止成功",Toast.LENGTH_SHORT).show();
                        if(position==1){
                            btn_start1.setBackgroundColor(Color.GRAY);
                            btn_stop1.setBackgroundColor(Color.GREEN);
                        }else if(position==2){
                            btn_start2.setBackgroundColor(Color.GRAY);
                            btn_stop2.setBackgroundColor(Color.GREEN);
                        }else if(position==3){
                            btn_start3.setBackgroundColor(Color.GRAY);
                            btn_stop3.setBackgroundColor(Color.GREEN);
                        }else if(position==4){
                            btn_start4.setBackgroundColor(Color.GRAY);
                            btn_stop4.setBackgroundColor(Color.GREEN);
                        }

                    }else{
                        Toast.makeText(getActivity(),position+"号小车启动失败",Toast.LENGTH_SHORT).show();
                        if(position==1){
                            btn_start1.setBackgroundColor(Color.GREEN);
                            btn_stop1.setBackgroundColor(Color.GRAY);
                        }else if(position==2){
                            btn_start2.setBackgroundColor(Color.GREEN);
                            btn_stop2.setBackgroundColor(Color.GRAY);

                        }else if(position==3){
                            btn_start3.setBackgroundColor(Color.GREEN);
                            btn_stop3.setBackgroundColor(Color.GRAY);

                        }else if(position==4){
                            btn_start4.setBackgroundColor(Color.GREEN);
                            btn_stop4.setBackgroundColor(Color.GRAY);

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

    private void start(int position) {
        JSONObject object = new JSONObject();
        try {
            object.put("CarId",position);
            object.put("CarAction","Start");
            object.put("UserName","Z0004");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.SetCarMove, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String data = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String result = jsonObject.getString("RESULT");
                    if(result.equals("S")){
                        Toast.makeText(getActivity(),position+"号小车启动成功",Toast.LENGTH_SHORT).show();
                        if(position==1){
                            btn_start1.setBackgroundColor(Color.GREEN);
                            btn_stop1.setBackgroundColor(Color.GRAY);
                        }else if(position==2){
                            btn_start2.setBackgroundColor(Color.GREEN);
                            btn_stop2.setBackgroundColor(Color.GRAY);
                        }else if(position==3){
                            btn_start3.setBackgroundColor(Color.GREEN);
                            btn_stop3.setBackgroundColor(Color.GRAY);
                        }else if(position==4){
                            btn_start4.setBackgroundColor(Color.GREEN);
                            btn_stop4.setBackgroundColor(Color.GRAY);
                        }

                    }else{
                        Toast.makeText(getActivity(),position+"号小车启动失败",Toast.LENGTH_SHORT).show();
                        if(position==1){
                            btn_start1.setBackgroundColor(Color.GRAY);
                            btn_stop1.setBackgroundColor(Color.GREEN);
                        }else if(position==2){
                            btn_start2.setBackgroundColor(Color.GRAY);
                            btn_stop2.setBackgroundColor(Color.GREEN);

                        }else if(position==3){
                            btn_start3.setBackgroundColor(Color.GRAY);
                            btn_stop3.setBackgroundColor(Color.GREEN);

                        }else if(position==4){
                            btn_start4.setBackgroundColor(Color.GRAY);
                            btn_stop4.setBackgroundColor(Color.GREEN);

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
}
