package traffic.traffic1.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.google.gson.Gson;

import traffic.traffic1.activity.App;

/**
 * Created by feng on 17-5-10.
 */

public class SomeTools {
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // Calculate inSampleSize
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static void showToast(String data){
        Toast.makeText(App.getInstance(), data, Toast.LENGTH_SHORT).show();
    }

    public static <T> T gsonParse(Class<T> bean, String jsonData){
        return new Gson().fromJson(jsonData, bean);
    }

    public static boolean filterServerInfo(String serverinfo){
        if (serverinfo == null | serverinfo.equals("timeout！"))return false;
        else  return true;
    }
}
