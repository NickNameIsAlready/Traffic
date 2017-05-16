package traffic.traffic1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import traffic.traffic1.R;
import traffic.traffic1.activity.BaseActivity;
import traffic.traffic1.dao.AddOPenHelper;
import traffic.traffic1.util.Constants;

/**
 * Created by asdf on 2017/5/15.
 */

public class MoneyFragment extends Fragment {

    private GridView gv;
    private Myadapter myadapter;
    private boolean isflag = false;
    private ArrayList<Integer> moneys;
    private Timer timer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = View.inflate(getActivity(), R.layout.frgment_money, null);
        gv = (GridView) inflate.findViewById(R.id.gv);
        myadapter = new Myadapter();
        gv.setAdapter(myadapter);
        initListener();
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                GetDataForFive();
            }
        };

        timer.schedule(timerTask, 100, 5000);


        return inflate;

    }

    private void initListener() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View inflate = View.inflate(getActivity(), R.layout.addmoney, null);
                EditText edt_add = (EditText) inflate.findViewById(R.id.edt_add);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("我的充值").setView(inflate).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strmoney = edt_add.getText().toString().trim();
                          if(strmoney!=null){
                              int intmoney = Integer.parseInt(strmoney);
                              if(intmoney>0&&intmoney<=50){
                                  addmoney(intmoney,(position+1));
                              }else{
                                  Toast.makeText(getActivity(),"请输入1-50的值",Toast.LENGTH_SHORT).show();
                              }
                          }


                    }
                });
                builder.setNegativeButton("取消", null).show();

            }
        });
    }

    private void addmoney(int intmoney, int position) {
        JSONObject object = new JSONObject();
        try {
            object.put("CarId",position);
            object.put("Money",intmoney);
            object.put("UserName","Z0004");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.SetCarAccountRecharge, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String data = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String result = jsonObject.getString("RESULT");
                    if(result.equals("S")){
                      Toast.makeText(getActivity(),position+"号小车充值"+intmoney+"元成功",Toast.LENGTH_SHORT).show();
                        //插入数据库
                        AddOPenHelper helper=new AddOPenHelper(getActivity());
                        SQLiteDatabase db = helper.getWritableDatabase();
                        int i=1;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                        String date = format.format(new Date());
                        helper.insert(db,i+"",position+"",intmoney+"",date);
                        System.out.println("----------------------插入数据成功");

                    }else{
                        Toast.makeText(getActivity(),position+"号小车充值"+"失败",Toast.LENGTH_SHORT).show();
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

    private void GetDataForFive() {
        moneys = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            JSONObject object = new JSONObject();
            try {
                object.put("CarId", i);
                object.put("UserName", "Z0004");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.GetCarAccountBalance, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String data = response.toString();
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        String result = jsonObject.getString("RESULT");
                        if (result.equals("S")) {
                            int balance = jsonObject.getInt("Balance");
                            moneys.add(balance);
                             if(moneys.size()==4){
                                 myadapter.notifyDataSetChanged();
                             }

                        } else {
                            Toast.makeText(getActivity(), "网络连接出错", Toast.LENGTH_SHORT).show();
                            moneys.add(0);
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

    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 4;
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
            View inflate = View.inflate(getActivity(), R.layout.item_gv, null);
            RelativeLayout car1 = (RelativeLayout) inflate.findViewById(R.id.car1);
            TextView tv_car1 = (TextView) inflate.findViewById(R.id.tv_car1);
            TextView tv_state1 = (TextView) inflate.findViewById(R.id.tv_state1);
            TextView tv_money1 = (TextView) inflate.findViewById(R.id.tv_money1);
            if (!isflag) {
                System.out.println("自定义");
                tv_car1.setText((position+1) + "号小车");
                if (position == 3) {
                    car1.setBackgroundColor(Color.RED);
                    tv_money1.setText("80");
                    tv_state1.setText("警告");

                } else {
                    car1.setBackgroundColor(Color.GREEN);
                    tv_money1.setText("100");
                    tv_state1.setText("正常");
                }
                if (position == 3) {
                    isflag = true;
                }

            } else {
                System.out.println("arraylist");
                if(moneys!=null&&moneys.size()>0){
                    int money = moneys.get(position);
                    tv_car1.setText((position+1) + "号小车");
                    if (money >= 100) {
                        //正常
                        car1.setBackgroundColor(Color.GREEN);
                        tv_state1.setText("正常");
                        tv_money1.setText("" + money);
                    } else {
                        //警告
                        car1.setBackgroundColor(Color.RED);
                        tv_state1.setText("警告");
                        tv_money1.setText("" + money);
                    }
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
