package traffic.traffic1.activity;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.LineChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYValueSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import traffic.traffic1.R;
import traffic.traffic1.bean.hTrafficLightBean;
import traffic.traffic1.util.GsonRequest;

public class Topic19Activity extends AppCompatActivity {
    private HashMap<TrafficLight,hTrafficLightBean> data = new HashMap<>();
    private enum TrafficLight{
        RED,GREED,YELLOW
    }
    private Handler mHandler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            loadData();
            mHandler.postDelayed(runnable,3*1000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic19);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.post(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runnable);
    }

    private void dealData() {
        if(data.size()<=2) return;
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        TrafficLight [] light = new TrafficLight[]{TrafficLight.GREED,TrafficLight.RED,TrafficLight.YELLOW};
        for(int i=0;i<data.size();i++) {
            hTrafficLightBean bean = data.get(light[i]);
            Log.d("resoponse",bean.toString());
            XYValueSeries series = new XYValueSeries(i+"号");
            series.add(1,bean.RedTime);
            series.add(2,bean.YellowTime);
            series.add(3,bean.GreenTime);
            dataset.addSeries(series);
        }
        int [] colors = new int[]{Color.RED,Color.BLUE,Color.GRAY};
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.TRANSPARENT);
        renderer.setGridColor(Color.BLACK);
        renderer.setShowGridY(true);//设置背景色
        renderer.setShowAxes(true);
        renderer.setBackgroundColor(Color.argb(0, 220, 228, 234) );
        //设置报表周边颜色
        renderer.setMarginsColor(Color.GRAY);
        renderer.setMargins(new int[]{50,50,50,50});
        String [] str = new String[3];
        for(int i=0;i<dataset.getSeriesCount();i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            renderer.addSeriesRenderer(r);
            str[i] = LineChart.TYPE;
        }
        if(dataset.getSeriesCount()<=0||dataset==null) return;
        GraphicalView view = ChartFactory.getCombinedXYChartView(Topic19Activity.this,dataset,renderer,str);
        view.setBackgroundColor(Color.TRANSPARENT);
        FrameLayout layout = (FrameLayout)findViewById(R.id.layout_17_chart);
        layout.removeAllViews();
        layout.addView(view);
        view.repaint();
        data.remove(TrafficLight.GREED);
        data.remove(TrafficLight.RED);
        data.remove(TrafficLight.YELLOW);
    }
    private void loadData(){
        TrafficLight [] light = new TrafficLight[]{TrafficLight.GREED,TrafficLight.RED,TrafficLight.YELLOW};
        for(int i=1;i<=3;i++) {
            TrafficLight l = light[i-1];
            JSONObject obj = new JSONObject();
            try {
                obj.put("TrafficLightId",String.valueOf(i));
                obj.put("UserName","Z0004");
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
            GsonRequest<hTrafficLightBean> gsonRequest = new GsonRequest<hTrafficLightBean>(Request.Method.POST,
                    "http://192.168.0.110:8080/transportservice/action/GetTrafficLightConfigAction.do",obj, hTrafficLightBean.class,
                    new Response.Listener<hTrafficLightBean>() {
                        @Override
                        public void onResponse(hTrafficLightBean response) {
                            if(response.RESULT.equals("F")) return;
                            if(data.containsKey(l)) data.remove(l);
                            data.put(l,response);
                            dealData();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error","error");
                }
            });
            App.getRequestQueue().add(gsonRequest);
        }
    }
}
