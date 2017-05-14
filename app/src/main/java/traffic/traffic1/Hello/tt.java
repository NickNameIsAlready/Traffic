package traffic.traffic1.Hello;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by asdf on 2017/5/14.
 */

public class tt {
    public void setString(Context mContext, String key, String values){
        SharedPreferences sp = mContext.getSharedPreferences("IP", mContext.MODE_PRIVATE);
        sp.edit().putString(key,values).commit();

    }
    public String getString(Context mContext, String key, String values){
        SharedPreferences sp = mContext.getSharedPreferences("IP", mContext.MODE_PRIVATE);
        String string = sp.getString(key, values);
        return string;
    }
}
