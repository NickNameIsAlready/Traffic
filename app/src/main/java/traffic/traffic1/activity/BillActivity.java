package traffic.traffic1.activity;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import traffic.traffic1.R;
import traffic.traffic1.adapter.BillAdapter;
import traffic.traffic1.bean.BillBean;

/**
 * Created by feng on 17-5-11.
 */

public class BillActivity extends BaseActivity{
    @BindView(R.id.lv_bill)ListView lvBill;
    List<BillBean> datas;

    @Override
    protected void initEvent() {
    }

    @Override
    protected void initView() {
        datas = new ArrayList<>();
        datas.add(new BillBean("1", "2017.5.11 20:49:35", "feng", "100", "2"));
        datas.add(new BillBean("2", "2017.5.11 20:49:35", "feng", "100", "2"));
        datas.add(new BillBean("3", "2017.5.11 20:49:35", "feng", "100", "2"));
        lvBill.setAdapter(new BillAdapter(this, R.layout.item_bill, datas));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_bill;
    }
}
