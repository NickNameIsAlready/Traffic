package traffic.traffic1.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jakewharton.rxbinding2.view.RxView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import butterknife.BindView;
import butterknife.OnClick;
import traffic.traffic1.R;
import traffic.traffic1.util.SharedPreferencesUtil;
import traffic.traffic1.util.SomeTools;

/**
 * Created by feng on 17-5-15.
 */

public class RechargeActivity extends BaseActivity {
    @BindView(R.id.chehao)
    Spinner chehao;
    @BindView(R.id.txt_show_yue)
    TextView txtShowYue;
    @BindView(R.id.chehao_recharge)
    Spinner chehaoRecharge;
    @BindView(R.id.edit_recharge_num)
    EditText editRechargeNum;
    @BindView(R.id.btn_refer)
    Button referBtn;

    @OnClick(R.id.btn_recharge)
    public void recharge() {
        try {
            JSONObject params = new JSONObject();
            params.put("CarId", Integer.valueOf(chehaoRecharge.getSelectedItem().toString()));
            params.put("UserName", "Z0004");
            JsonObjectRequest getBalance = new JsonObjectRequest(Request.Method.POST,
                    "http://192.168.0.110:8080/transportservice/action/GetCarAccountBalance.do"
                    , params, response -> {
                try {
                    if (SomeTools.isSuccess(response)) {
                        int balance = response.getInt("Balance");
                        txtShowYue.setText(String.format(getResources().getString(R.string.balance), balance));
                        Iterator<String> iterator = SharedPreferencesUtil.getThresholdAccount().iterator();
                        int jine = Integer.valueOf(editRechargeNum.getText().toString());
                        int max = Integer.valueOf(iterator.next());
                        if (balance > max) {
                            SomeTools.showToast("账户余额超过设置的阈值");
                        } else if (balance + jine > max) {
                            SomeTools.showToast("充值余额超过设置的阈值, 最大允许充值金额为" + (max - balance));
                        } else {
                            JSONObject paramss = new JSONObject();
                            paramss.put("CarId", Integer.valueOf(chehaoRecharge.getSelectedItem().toString()));
                            paramss.put("Money", Integer.valueOf(editRechargeNum.getText().toString()));
                            JsonObjectRequest rechargeRequest = new JsonObjectRequest(Request.Method.POST,
                                    "http://192.168.0.110:8080/transportservice/action/SetCarAccountRecharge.do"
                                    , paramss, r -> {
                                try {
                                    if (SomeTools.isSuccess(r)) {
                                        SomeTools.showToast("充值成功");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }, error -> SomeTools.showToast("网络异常"));
                            App.getRequestQueue().add(rechargeRequest);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                SomeTools.showToast("网络异常");
            });
            App.getRequestQueue().add(getBalance);
        } catch (Exception e) {
            //who cares?
        }
    }

    @Override
    protected void initEvent() {
        RxView.clicks(referBtn).subscribe(v -> {
            try {
                JSONObject params = new JSONObject();
                params.put("CarId", Integer.valueOf(chehaoRecharge.getSelectedItem().toString()));
                params.put("UserName", "Z0004");
                JsonObjectRequest getBalance = new JsonObjectRequest(Request.Method.POST,
                        "http://192.168.0.110:8080/transportservice/action/GetCarAccountBalance.do"
                        , params, response -> {
                    try {
                        if (SomeTools.isSuccess(response)) {
                            int balance = response.getInt("Balance");
                            txtShowYue.setText(String.format(getResources().getString(R.string.balance), balance));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    SomeTools.showToast("网络异常");
                });
                App.getRequestQueue().add(getBalance);
            } catch (Exception e) {
                //who cares?
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_recharge;
    }
}
