package traffic.traffic1.activity;

import android.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import traffic.traffic1.R;
import traffic.traffic1.util.Constants;

/**
 * Created by asdf on 2017/5/15.
 */

public class news extends BaseActivity {
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.sp)
    Spinner sp;
    @BindView(R.id.btn_confirm)
    Button btn_confirm;
    private ArrayList<Integer>list;


    @Override
    protected void initEvent() {
        initListener();
    }

    private void initListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(news.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK).setTitle("创新题提示:").setMessage("点击确定，查询相应小车的余额")
                        .setPositiveButton("确定", null).setNegativeButton("取消", null).create();
                Window window = alertDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.alpha=0.6f;
                window.setAttributes(lp);
                alertDialog.show();
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  id = (int) (sp.getSelectedItemId() + 1);
                JSONObject object = new JSONObject();
                try {
                    object.put("CarId",id);
                    object.put("UserName","Z0004");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestQueue requestQueue = Volley.newRequestQueue(news.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.GetCarAccountBalance, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String data = response.toString();
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            String result = jsonObject.getString("RESULT");
                            if(result.equals("S")){
                                int balance = jsonObject.getInt("Balance");
                                Toast.makeText(news.this,id+"号小车的余额:"+balance+"元",Toast.LENGTH_SHORT).show();
                            }else{
                              Toast.makeText(news.this,"网络连接出错",Toast.LENGTH_SHORT).show();
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
        });

    }

    @Override
    protected void initView() {
        list=new ArrayList<>();
        for(int i=1;i<=15;i++){
            list.add(i);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(news.this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.news;
    }
}
