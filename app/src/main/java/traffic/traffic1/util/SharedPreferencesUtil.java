package traffic.traffic1.util;

import android.content.Context;

import java.util.LinkedHashSet;
import java.util.Set;

import traffic.traffic1.activity.App;

/**
 * Created by feng on 17-5-10.
 */

public class SharedPreferencesUtil {
    private static final String FILE_NAME = "traffic";
    private static final String KEY_IS_FIRST = "isfirst";
    private static final String KEY_THRESHOLD_ACCOUNT = "threshol_account";
    private static final String KEY_THRESHOLD_= "threshol";

    private static android.content.SharedPreferences getSP(){
        return App.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void putIsFirst(boolean isFirst){
        getSP().edit().putBoolean(KEY_IS_FIRST, isFirst).apply();
    }

    public static boolean getIsFirst(){
        return getSP().getBoolean(KEY_IS_FIRST, true);
    }

    public static void putThresholdAccount(Set<String> isFirst){
        getSP().edit().putStringSet(KEY_THRESHOLD_ACCOUNT, isFirst).apply();
    }

    public static Set<String> getThresholdAccount(){
        Set<String> threshold = new LinkedHashSet<>();
        threshold.add("10");
        threshold.add("5000");
        return getSP().getStringSet(KEY_THRESHOLD_ACCOUNT, threshold);
    }
}
