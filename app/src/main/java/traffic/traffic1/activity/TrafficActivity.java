package traffic.traffic1.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import traffic.traffic1.R;
import traffic.traffic1.fragment.TrafficFragment;
import traffic.traffic1.util.Constants;

/**
 * Created by asdf on 2017/5/16.
 */

public class TrafficActivity extends BaseActivity {
    @BindView(R.id.fl_traffic)FrameLayout fl_traffic;
    @BindView(R.id.rg_traffic)RadioGroup rg_traffic;
    @BindView(R.id.rb_road)RadioButton rb_road;
    @BindView(R.id.rb_eviro)RadioButton rb_eviro;


    @Override
    protected void initEvent() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        rg_traffic.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_road:
                        transaction.replace(R.id.fl_traffic,new TrafficFragment());
                        break;
                    case R.id.rb_eviro:
                        break;

                }
                transaction.commit();
            }

        });
    }

    @Override
    protected void initView() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_traffic,new TrafficFragment());
        fragmentTransaction.commit();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_traffic;
    }

}
