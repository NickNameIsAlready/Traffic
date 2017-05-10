package traffic.traffic1.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import traffic.traffic1.R;
import traffic.traffic1.fragment.BaseFragment;
import traffic.traffic1.fragment.ViewPagerFragment1;
import traffic.traffic1.fragment.ViewPagerFragment2;
import traffic.traffic1.fragment.ViewPagerFragment3;
import traffic.traffic1.util.SharedPreferencesUtil;

/**
 * Created by feng on 17-5-10.
 */

public class GuideActivity extends BaseActivity{
    @BindView(R.id.viewpager_guide) ViewPager viewPager;
    @Override
    protected void initEvent() {
//        isFirst();
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    private void isFirst() {
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

    class ViewPagerAdapter extends FragmentPagerAdapter{
        private List<BaseFragment> list;
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            list = new ArrayList<>();
            list.add(new ViewPagerFragment1());
            list.add(new ViewPagerFragment2());
            list.add(new ViewPagerFragment3());
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
