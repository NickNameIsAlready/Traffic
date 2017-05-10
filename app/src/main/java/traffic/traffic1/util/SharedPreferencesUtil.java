package traffic.traffic1.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.camera2.CameraCaptureSession;

import traffic.traffic1.activity.App;

/**
 * Created by feng on 17-5-10.
 */

public class SharedPreferencesUtil {
    private static final String FILE_NAME = "traffic";
    private static final String KEY_IS_FIRST = "isfirst";

    private static android.content.SharedPreferences getSP(){
        return App.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void putIsFirst(boolean isFirst){
        getSP().edit().putBoolean(KEY_IS_FIRST, isFirst).apply();
    }

    public static boolean getIsFirst(){
        return getSP().getBoolean(KEY_IS_FIRST, true);
    }
}
