package traffic.traffic1.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import traffic.traffic1.R;
import traffic.traffic1.fragment.ControlFragment;
import traffic.traffic1.fragment.HistoryFragment;
import traffic.traffic1.fragment.MoneyFragment;

/**
 * Created by asdf on 2017/5/15.
 */

public class MyCarActivity extends  BaseActivity {
    @BindView(R.id.tv_car)TextView tv_car;
    @BindView(R.id.fl_car)FrameLayout fl_car;
    @BindView(R.id.rb_money)RadioButton rb_money;
    @BindView(R.id.rb_control)RadioButton rb_control;
    @BindView(R.id.rb_history)RadioButton rb_history;
    @BindView(R.id.rg)RadioGroup rg;
    private FragmentManager fm;
    private FragmentTransaction transaction;

    @Override
    protected void initEvent() {

      rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
              fm = getSupportFragmentManager();
              transaction= fm.beginTransaction();
              switch (checkedId) {
                  case R.id.rb_money:
                      System.out.println("------------余额-----------");
                      Toast.makeText(MyCarActivity.this, "余额", Toast.LENGTH_SHORT).show();
                      rb_money.setTextSize(18);
                      rb_control.setTextSize(14);
                      rb_history.setTextSize(14);
                      transaction.replace(R.id.fl_car, new MoneyFragment());
                      break;
                  case R.id.rb_control:
                      System.out.println("------------控制-----------");
                      Toast.makeText(MyCarActivity.this, "控制", Toast.LENGTH_SHORT).show();
                      rb_money.setTextSize(14);
                      rb_control.setTextSize(18);
                      rb_history.setTextSize(14);
                      transaction.replace(R.id.fl_car, new ControlFragment());
                      break;
                  case R.id.rb_history:
                      System.out.println("-----历史-----");
                      Toast.makeText(MyCarActivity.this, "历史", Toast.LENGTH_SHORT).show();
                      rb_money.setTextSize(14);
                      rb_control.setTextSize(14);
                      rb_history.setTextSize(18);
                      transaction.replace(R.id.fl_car, new HistoryFragment());
                      break;

              }
              transaction.commit();
          }
      });


    }


    @Override
    protected void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Toast.makeText(MyCarActivity.this, "余额", Toast.LENGTH_SHORT).show();
        rb_money.setTextSize(18);
        rb_control.setTextSize(14);
        rb_history.setTextSize(14);
        fragmentTransaction.replace(R.id.fl_car, new MoneyFragment());
        fragmentTransaction.commit();
    }

    @Override
    public int getLayout() {
        return R.layout.car;
    }
}
