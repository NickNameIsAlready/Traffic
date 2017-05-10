package traffic.traffic1.activity;

import android.content.Intent;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import traffic.traffic1.R;
import traffic.traffic1.util.SharedPreferencesUtil;

/**
 * Created by feng on 17-5-10.
 */

public class GuideActivity extends BaseActivity{
    @Override
    protected void initEvent() {
        if (SharedPreferencesUtil.getIsFirst()){
            Logger.d("首次登录");
            SharedPreferencesUtil.putIsFirst(false);
            Observable.timer(1, TimeUnit.SECONDS)
                    .subscribe(aLong -> {
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    });
        }else {
            Logger.d("二次登录");
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_guide;
    }
}
