package traffic.traffic1.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import traffic.traffic1.R;
import traffic.traffic1.service.GetDataService;

/**
 * Created by asdf on 2017/5/15.
 */

public class AlertActivity extends BaseActivity {

    @BindView(R.id.tv_pm)
    TextView tv_pm;
    @BindView(R.id.tv_co2)
    TextView tv_co2;
    @BindView(R.id.tv_LightIntensity)
    TextView tv_LightIntensity;
    @BindView(R.id.tv_humidity)
    TextView tv_humidity;
    @BindView(R.id.tv_temperature)
    TextView tv_temperature;
    private Intent intent;
    private MyRecevice myrecevice;
    private Notification notification;
    private NotificationManager manager;
    @Override
    protected void initEvent() {
        intent = new Intent(AlertActivity.this, GetDataService.class);
        startService(intent);
        myrecevice = new MyRecevice();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.seneor");
       registerReceiver(myrecevice, filter);
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayout() {
        return R.layout.layout_alert;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
       stopService(intent);
    }

    class MyRecevice extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if(bundle!=null){
                int pm = bundle.getInt("pm");
                int co2 = bundle.getInt("co2");
                int LightIntensity = bundle.getInt("LightIntensity");
                int humidity = bundle.getInt("humidity");
                int temperature = bundle.getInt("temperature");
                tv_pm.setText("pm2.5的值:"+pm+"ug/m3");
                tv_co2.setText("co2的值:"+co2+"ug/m3");
                tv_LightIntensity.setText("亮度的值:"+LightIntensity+"ug/m3");
                tv_humidity.setText("湿度的值:"+humidity+"oC");
                tv_temperature.setText("温度的值:"+temperature+"oC");
            }else{
                Toast.makeText(AlertActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
            }



        }
    }
}
