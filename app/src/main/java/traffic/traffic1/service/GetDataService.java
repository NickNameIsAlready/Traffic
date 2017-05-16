package traffic.traffic1.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import traffic.traffic1.R;
import traffic.traffic1.activity.AlertActivity;
import traffic.traffic1.util.Constants;

/**
 * Created by asdf on 2017/5/15.
 */

public class GetDataService extends Service {

    private Timer timer;
    private NotificationManager manager;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GetData();
    }

    private void GetData() {
        AskDataForTenSecond();

    }

    private void AskDataForTenSecond() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(GetDataService.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.GetAllSense, null, new Response.Listener<JSONObject>() {



                    @Override
                    public void onResponse(JSONObject response) {
                        String data = response.toString();
                        try {
                            JSONObject object = new JSONObject(data);
                            String result = object.getString("RESULT");
                            if (result.equals("S")) {
                                int pm = object.getInt("pm2.5");
                                int co2 = object.getInt("co2");
                                int LightIntensity = object.getInt("LightIntensity");
                                int humidity = object.getInt("humidity");
                                int temperature = object.getInt("temperature");
                                Intent intent = new Intent();
                                intent.putExtra("pm", pm);
                                intent.putExtra("co2", co2);
                                intent.putExtra("LightIntensity", LightIntensity);
                                intent.putExtra("humidity", humidity);
                                intent.putExtra("temperature", temperature);
                                intent.setAction("com.seneor");
                                sendBroadcast(intent);

                                Intent intent1 = new Intent(GetDataService.this, AlertActivity.class);
                                PendingIntent pendingintent = PendingIntent.getActivity(GetDataService.this, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                                manager = (NotificationManager) getSystemService(AlertActivity.NOTIFICATION_SERVICE);
                                if (pm > 200) {
                                    Notification notification = new Notification.Builder(GetDataService.this)
                                            .setWhen(System.currentTimeMillis())
                                            .setSmallIcon(R.drawable.huaji)
                                            .setContentTitle("pm2.5超标")
                                            .setContentText("当前值:" + pm + "ug/m3,阀值:" + "200ug/m3")
                                            .setContentIntent(pendingintent)
                                            .build();
                                    manager.notify(1, notification);
                                } else {
                                    manager.cancel(1);
                                }
                                if (co2 > 8000) {
                                    manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                    Notification notification = new Notification.Builder(GetDataService.this)
                                            .setWhen(System.currentTimeMillis())
                                            .setSmallIcon(R.drawable.huaji)
                                            .setContentTitle("co2超标")
                                            .setContentText("当前值:" + co2 + "ug/m3,阀值:" + "8000ug/m3")
                                            .setContentIntent(pendingintent)
                                            .build();
                                    manager.notify(2, notification);
                                } else {
                                    manager.cancel(2);
                                }
                                if (LightIntensity > 1000) {
                                    manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                    Notification notification = new Notification.Builder(GetDataService.this)
                                            .setWhen(System.currentTimeMillis())
                                            .setSmallIcon(R.drawable.huaji)
                                            .setContentTitle("LightIntensity超标")
                                            .setContentText("当前值:" + LightIntensity + "ug/m3,阀值:" + "1000ug/m3")
                                            .setContentIntent(pendingintent)
                                            .build();
                                    manager.notify(3, notification);
                                } else {
                                    manager.cancel(3);
                                }
                                if (humidity > 100) {
                                    manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                    Notification notification = new Notification.Builder(GetDataService.this)
                                            .setWhen(System.currentTimeMillis())
                                            .setSmallIcon(R.drawable.huaji)
                                            .setContentTitle("humidity超标")
                                            .setContentText("当前值:" + humidity + "ug/m3,阀值:" + "100ug/m3")
                                            .setContentIntent(pendingintent)
                                            .build();
                                    manager.notify(4, notification);
                                } else {
                                    manager.cancel(4);
                                }
                                if (temperature > 30) {
                                    manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                    Notification notification = new Notification.Builder(GetDataService.this)
                                            .setWhen(System.currentTimeMillis())
                                            .setSmallIcon(R.drawable.huaji)
                                            .setContentTitle("temperature超标")
                                            .setContentText("当前值:" + temperature + "ug/m3,阀值:" + "30ug/m3")
                                            .setContentIntent(pendingintent)
                                            .build();
                                    manager.notify(5, notification);
                                } else {
                                    manager.cancel(5);
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
        };
        timer.schedule(timerTask, 100, 10000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
