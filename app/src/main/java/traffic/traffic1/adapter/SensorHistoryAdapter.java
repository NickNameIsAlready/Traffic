package traffic.traffic1.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import traffic.traffic1.R;
import traffic.traffic1.bean.SensorHistoryBean;

/**
 * Created by feng on 17-5-11.
 */

public class SensorHistoryAdapter extends ArrayAdapter<SensorHistoryBean>{
    private Context mtx;
    private List<SensorHistoryBean> datas;
    public SensorHistoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<SensorHistoryBean> objects) {
        super(context, resource, objects);
        mtx = context;
        datas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SensorHistoryBean bean = datas.get(position);
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mtx).inflate(R.layout.item_sensor_history, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.shijian.setText(bean.shijian);
        holder.iszhengchang.setText(bean.iszhengchang);
        holder.shuju.setText(bean.shuju);
        holder.sensor.setText(bean.sensor);
        return convertView;
    }
    class ViewHolder{
        @BindView(R.id.sensor) TextView sensor;
        @BindView(R.id.shuju) TextView shuju;
        @BindView(R.id.iszhengchang) TextView iszhengchang;
        @BindView(R.id.shijian) TextView shijian;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
