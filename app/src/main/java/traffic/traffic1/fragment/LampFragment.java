package traffic.traffic1.fragment;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;

import butterknife.BindView;
import traffic.traffic1.R;

/**
 * Created by asdf on 2017/5/15.
 */

public class LampFragment extends BaseFragment {
    @BindView(R.id.model)Switch model;
    @BindView(R.id.toggle)Switch toggle;
    @BindView(R.id.sp)Spinner sp;
    @BindView(R.id.btn)Button btn;
    private ArrayList<Integer>list;

    @Override
    protected void initDataAndEvent() {
        init();
       initListener();
    }

    private void initListener() {
        //模式切换
        model.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //自动
                    toggle.setEnabled(false);
                     //路灯会自动开关
                     AudioToggle();

                }else{
                    //手动
                    toggle.setEnabled(true);
                }
            }
        });
        //
    }

    private void AudioToggle() {



    }

    private void init() {
        list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getActivity(),android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);



    }

    @Override
    public int getLayoutId() {
        return R.layout.lamp;
    }
}
