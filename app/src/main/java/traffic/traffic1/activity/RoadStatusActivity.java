package traffic.traffic1.activity;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import traffic.traffic1.R;

public class RoadStatusActivity extends BaseActivity{

    private Spinner spRoadStatus;
    private TextView txtLevel1;
    private TextView txtLevel2;
    private TextView txtLevel3;
    private TextView txtLevel4;
    private TextView txtLevel5;

    @Override
    protected void initEvent() {
        spRoadStatus = (Spinner) findViewById(R.id.sp_road_status);
        txtLevel1 = (TextView) findViewById(R.id.txt_level_1);
        txtLevel2 = (TextView) findViewById(R.id.txt_level_2);
        txtLevel3 = (TextView) findViewById(R.id.txt_level_3);
        txtLevel4 = (TextView) findViewById(R.id.txt_level_4);
        txtLevel5 = (TextView) findViewById(R.id.txt_level_5);
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_road_status;
    }
    private View getView1(){
        return (View) findViewById(R.id.view_1);
    }

    private View getView2(){
        return (View) findViewById(R.id.view_2);
    }

    private View getView3(){
        return (View) findViewById(R.id.view_3);
    }

    private View getView4(){
        return (View) findViewById(R.id.view_4);
    }

    private View getView5(){
        return (View) findViewById(R.id.view_5);
    }
}
