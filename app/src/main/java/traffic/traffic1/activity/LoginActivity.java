package traffic.traffic1.activity;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.OnClick;
import traffic.traffic1.R;

/**
 * Created by feng on 17-5-10.
 */

public class LoginActivity extends BaseActivity{
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
