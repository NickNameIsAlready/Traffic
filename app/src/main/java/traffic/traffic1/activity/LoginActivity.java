package traffic.traffic1.activity;

import android.content.Intent;

import butterknife.OnClick;
import traffic.traffic1.R;

/**
 * Created by feng on 17-5-10.
 */

public class LoginActivity extends BaseActivity{
    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.btn_login)
    public void toMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
    }
    @Override
    protected void initView() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }
}
