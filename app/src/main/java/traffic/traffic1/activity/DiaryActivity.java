package traffic.traffic1.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import traffic.traffic1.R;
import traffic.traffic1.fragment.RecommedFragment;
import traffic.traffic1.fragment.RoadLampFragment;

/**
 * Created by asdf on 2017/5/16.
 */

public class DiaryActivity extends BaseActivity {
    @BindView(R.id.fl_diary)FrameLayout fl_diary;
    @BindView(R.id.rg)RadioGroup rg;
    @BindView(R.id.rb_lamp)RadioButton rb_lamp;
    @BindView(R.id.rb_diary)RadioButton rb_diary;


    @Override
    protected void initEvent() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                switch (checkedId){
                    case R.id.rb_lamp:
                        transaction.replace(R.id.fl_diary,new RoadLampFragment());
                        break;
                    case R.id.rb_diary:
                        transaction.replace(R.id.fl_diary,new RecommedFragment());
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
        fragmentTransaction.replace(R.id.fl_diary,new RoadLampFragment());
        fragmentTransaction.commit();
    }

    @Override
    public int getLayout() {
        return R.layout.diary;
    }
}
