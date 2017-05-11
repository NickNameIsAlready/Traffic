package traffic.traffic1.activity;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import traffic.traffic1.R;
import traffic.traffic1.adapter.SensorHistoryAdapter;
import traffic.traffic1.bean.SensorHistoryBean;

/**
 * Created by feng on 17-5-11.
 */

public class SensorHistoryActivity extends BaseActivity{
    @BindView(R.id.lv_sensor) ListView lvSensor;
    private List<SensorHistoryBean> datas;
    private SensorHistoryAdapter adapter;
    @Override
    protected void initEvent() {
        datas = new ArrayList<>();
        datas.add(new SensorHistoryBean("空气温度", "2017-5-11 21:12:28", "正常", "150"));
        datas.add(new SensorHistoryBean("空气湿度", "2017-5-11 21:12:28", "正常", "150"));
        datas.add(new SensorHistoryBean("光照强度", "2017-5-11 21:12:28", "正常", "150"));
        adapter = new SensorHistoryAdapter(this, R.layout.item_sensor_history, datas);
        lvSensor.setAdapter(adapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_sensor_history;
    }
}
