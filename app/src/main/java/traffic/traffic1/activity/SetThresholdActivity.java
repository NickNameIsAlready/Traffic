package traffic.traffic1.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import traffic.traffic1.R;
import traffic.traffic1.util.SharedPreferencesUtil;
import traffic.traffic1.util.SomeTools;

/**
 * Created by feng on 17-5-11.
 * todo 实时检测查询
 */

public class SetThresholdActivity extends BaseActivity{
    @BindView(R.id.btn_sudu) Button suduBtn;
    @BindView(R.id.btn_zhanghu) Button zhanghuBtn;
    @BindView(R.id.btn_set_type) Button settypeBtn;
    @BindView(R.id.txt_type) TextView typeTxt;
    @BindView(R.id.txt_hint_result) TextView hintTxt;
    @BindView(R.id.edit_max) EditText maxEdit;
    @BindView(R.id.edit_min) EditText minEdit;
    @BindView(R.id.btn_refer_type) Button referTypeBtn;
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
        RxView.clicks(settypeBtn).subscribe(v->{
            if (typeTxt.getText().toString().equals("账户")){
                Set<String> data = new LinkedHashSet<String>();
                data.add(maxEdit.getText().toString());
                data.add(minEdit.getText().toString());
                SharedPreferencesUtil.putThresholdAccount(data);
                SomeTools.showToast("设置完成");
            }
        });
        RxView.clicks(referTypeBtn).subscribe(v->{
            Iterator<String> iterator = SharedPreferencesUtil.getThresholdAccount().iterator();
            hintTxt.setText(String.format(getString(R.string.account_threshold), iterator.next(), iterator.next()));
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
