package traffic.traffic1.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import traffic.traffic1.R;
import traffic.traffic1.bean.BusCount;
import traffic.traffic1.dao.MyOPenHelper;
import traffic.traffic1.util.Constants;

/**
 * Created by asdf on 2017/5/15.
 */

public class BusCountActivity extends BaseActivity {
    @BindView(R.id.listview)
    ListView listView;
    private Timer timer;
    private MyOPenHelper helper;
    private Myadapter myadapter;
    private ArrayList<BusCount> busCounts;
    private SQLiteDatabase db;

    @Override
    protected void initEvent() {
        helper = new MyOPenHelper(BusCountActivity.this);
        db = helper.getWritableDatabase();
        GetBusCount();
        //查询数据库
        busCounts = new ArrayList<>();
        Cursor cursor = db.query("info", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            BusCount busCount = new BusCount();
            busCount.setId(cursor.getString(0));
            busCount.setNumber(cursor.getString(1));
            busCounts.add(busCount);
            if(busCounts.size()>0&&busCounts!=null){
                myadapter = new Myadapter();
                listView.setAdapter(myadapter);
            }
        }
    }

    private void GetBusCount() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                for (int i = 1; i <= 2; i++) {
                    int temp = i;
                    JSONObject object = new JSONObject();
                    try {
                        object.put("BusId", i);
                        object.put("UserName", "Z0004");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestQueue requestQueue = Volley.newRequestQueue(BusCountActivity.this);

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.GetBusCapacity, object, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            String data = response.toString();
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                String result = jsonObject.getString("RESULT");
                                if (result.equals("S")) {
                                    int busCapacity = jsonObject.getInt("BusCapacity");
                                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                    if (busCapacity > 10) {
                                        Toast.makeText(BusCountActivity.this, "超载人数:" + busCapacity + "超载公交:" + temp, Toast.LENGTH_SHORT).show();
                                        //超载
                                        Notification notification = new Notification.Builder(BusCountActivity.this)
                                                .setSmallIcon(R.drawable.huaji)
                                                .setWhen(System.currentTimeMillis())
                                                .setContentTitle("超载")
                                                .setContentText(temp + "号公交车超载，超载人数:" + busCapacity)
                                                .build();
                                        manager.notify(temp, notification);


                                        helper.insert(db, temp + "", busCapacity + "");
//                                        myadapter.notifyDataSetChanged();
                                        Cursor cursor = db.query("info", null, null, null, null, null, null);
                                        while (cursor.moveToNext()) {
                                            BusCount busCount = new BusCount();
                                            busCount.setId(cursor.getString(0));
                                            busCount.setNumber(cursor.getString(1));
                                            busCounts.add(busCount);
                                            if(busCounts.size()>0&&busCounts!=null){
                                                myadapter = new Myadapter();
                                                listView.setAdapter(myadapter);
                                            }
                                        }
//

                                    } else {
                                        manager.cancel(temp);
                                    }


                                } else {
                                    Toast.makeText(BusCountActivity.this, "网络连接出错", Toast.LENGTH_SHORT).show();
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
        };
        timer.schedule(timerTask, 100, 10000);
    }


    @Override
    protected void initView() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_count;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return busCounts.size();
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
            View inflate = View.inflate(BusCountActivity.this, R.layout.item_listview, null);
            TextView tv_id=(TextView)inflate.findViewById(R.id.tv_id);
            TextView tv_number=(TextView)inflate.findViewById(R.id.tv_number);
            tv_id.setText(busCounts.get(position).getId());
            tv_number.setText(busCounts.get(position).getNumber());
            return inflate;
        }
    }
}
