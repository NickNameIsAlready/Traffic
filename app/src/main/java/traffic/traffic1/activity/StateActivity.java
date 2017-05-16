package traffic.traffic1.activity;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
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

public class StateActivity extends BaseActivity {
    @BindView(R.id.state_lv)
    ListView state_lv;
    @BindView(R.id.iv_refresh)
    ImageView iv_refresh;
    private ArrayList<String> list;
    private ArrayList<Integer> list1;
    private JsonObjectRequest jsonObjectRequest;
    private Myadapter myadapter;


    @Override
    protected void initEvent() {
        iv_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetData();
            }
        });
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list.add("第1号路");
        list.add("第2号路");
        list.add("第3号路");
        list.add("第4号路");
        list.add("第5号路");
        list.add("第6号路");
        GetData();
        state_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        list.remove(0);
                        list1.remove(0);

                        break;
                    case 1:
                        list.remove(1);
                        list1.remove(1);
                        break;
                    case 2:
                        list.remove(2);
                        list1.remove(2);
                        break;
                    case 3:
                        list.remove(3);
                        list1.remove(3);
                        break;
                    case 4:
                        list.remove(4);
                        list1.remove(4);
                        break;
                    case 5:
                        list.remove(5);
                        list1.remove(5);
                        break;
                }
                myadapter.notifyDataSetChanged();

                return false;
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.layout_state;
    }

    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
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
            View inflate = View.inflate(StateActivity.this, R.layout.layout_item_listview, null);
            TextView tv_id = (TextView) inflate.findViewById(R.id.tv_id);
            TextView tv_state = (TextView) inflate.findViewById(R.id.tv_state);
            tv_id.setText(list.get(position));
            if (list1.get(position) > 3) {
                tv_state.setText("拥挤");
                tv_state.setTextColor(Color.RED);
            } else {
                tv_state.setText("通畅");
                tv_state.setTextColor(Color.GREEN);
            }
            return inflate;
        }
    }

    public void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(StateActivity.this);
        for (int i = 1; i <= list.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("RoadId", i);
                jsonObject.put("UserName", "Z0004");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.GetRoadStatus, jsonObject, new Response.Listener<JSONObject>() {


                @Override
                public void onResponse(JSONObject response) {
                    String data = response.toString();
                    try {
                        JSONObject object = new JSONObject(data);
                        String result = object.getString("RESULT");
                        if (result.equals("S")) {
                            int banlance = object.getInt("Balance");
                            list1.add(banlance);

                        } else {
//                            list1.add(3);
                            Toast.makeText(StateActivity.this,"连接不到服务器",Toast.LENGTH_SHORT).show();
                        }
                        if (list1.size() == list.size()) {
                            System.out.println("数组的大小:" + list1.size());
                            myadapter = new Myadapter();
                            state_lv.setAdapter(myadapter);
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
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);
        }

    }
}
