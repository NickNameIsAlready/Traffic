package traffic.traffic1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import traffic.traffic1.R;

/**
 * Created by feng on 17-5-10.
 */

public class ViewPagerFragment1 extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initDataAndEvent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_view_pager1;
    }
}
