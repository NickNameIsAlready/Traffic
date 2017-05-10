package traffic.traffic1.fragment;

import android.content.Intent;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import traffic.traffic1.R;
import traffic.traffic1.activity.LoginActivity;

/**
 * Created by feng on 17-5-10.
 */

public class ViewPagerFragment3 extends BaseFragment{
    @BindView(R.id.btn_start) Button startBtn;
    @Override
    protected void initDataAndEvent() {
        RxView.clicks(startBtn).subscribe(v->{
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_view_pager3;
    }
}
