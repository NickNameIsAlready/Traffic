package traffic.traffic1.fragment;

import android.view.View;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.OnClick;
import traffic.traffic1.R;
import traffic.traffic1.activity.MainActivity;

public class LightMangerFragment extends BaseFragment {

    @BindView(R.id.sp_lamp_status) Spinner spLampStatus;
    @BindView(R.id.sp_lamp_control) Spinner spLampControl;

    @Override
    protected void initDataAndEvent() {
        ((MainActivity)getActivity()).getToolbar().setTitle("路灯管理");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_light_manger;
    }
    @OnClick({R.id.btn_refer, R.id.btn_open, R.id.btn_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_refer:
                //TODO implement
                break;
            case R.id.btn_open:
                //TODO implement
                break;
            case R.id.btn_close:
                //TODO implement
                break;
        }
    }
}
