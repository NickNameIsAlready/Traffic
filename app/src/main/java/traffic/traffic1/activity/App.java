package traffic.traffic1.activity;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.orhanobut.logger.Logger;

/**
 * Created by feng on 17-5-10.
 */

public class App extends Application{
    private static App context;
    private static RequestQueue requestQueue;

    public synchronized static RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(App.getInstance());
        }
        return requestQueue;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Logger.init("FENG");
    }

    public static synchronized App getInstance(){
        return context;
    }
}
