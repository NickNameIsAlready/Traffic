package traffic.traffic1.activity;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by feng on 17-5-10.
 */

public class App extends Application{
    private static App context;

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
