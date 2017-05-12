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
import traffic.traffic1.bean.BillBean;

/**
 * Created by feng on 17-5-11.
 */

public class BillAdapter extends ArrayAdapter<BillBean>{
    private Context mtx;
    private List<BillBean> datas;
    public BillAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<BillBean> objects) {
        super(context, resource, objects);
        mtx = context;
        datas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BillBean bean = datas.get(position);
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mtx).inflate(R.layout.item_bill, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.xuhao.setText(bean.xuhao);
        holder.caozuoren.setText(bean.caozuoren);
        holder.shijian.setText(bean.shijian);
        holder.chehao.setText(bean.chehao);
        holder.jine.setText(bean.jine);
        return convertView;
    }

    class ViewHolder{
        @BindView(R.id.xuhao) TextView xuhao;
        @BindView(R.id.chehao) TextView chehao;
        @BindView(R.id.jine) TextView jine;
        @BindView(R.id.caozuoren) TextView caozuoren;
        @BindView(R.id.shijian) TextView shijian;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
