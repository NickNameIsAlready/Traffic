package traffic.traffic1.activity;

import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import traffic.traffic1.R;

/**
 * Created by feng on 17-5-11.
 */

public class SettHresholdActivity extends BaseActivity{
    @BindView(R.id.btn_sudu) Button suduBtn;
    @BindView(R.id.btn_zhanghu) Button zhanghuBtn;
    @BindView(R.id.btn_set_type) Button settypeBtn;
    @BindView(R.id.txt_type) TextView typeTxt;
    @BindView(R.id.txt_hint_result) TextView hintTxt;
    @Override
    protected void initEvent() {
        RxView.clicks(suduBtn).subscribe(v->{
            settypeBtn.setText("设置小车速度阈值");
            typeTxt.setText("小车");
            hintTxt.setText("阈值：最低20h/km--最高60h/km");
        });
        RxView.clicks(zhanghuBtn).subscribe(v->{
            settypeBtn.setText("设置小车阈值");
            typeTxt.setText("账户");
            hintTxt.setText("阈值：最低10元--最高5000元");
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_set_threshold;
    }
}
