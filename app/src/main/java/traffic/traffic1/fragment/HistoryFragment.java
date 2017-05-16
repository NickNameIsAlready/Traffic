package traffic.traffic1.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import traffic.traffic1.R;
import traffic.traffic1.bean.AddMoney;
import traffic.traffic1.dao.AddOPenHelper;

/**
 * Created by asdf on 2017/5/15.
 */

public class HistoryFragment extends Fragment {

    private ArrayList<AddMoney> addMoneys;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.history, null);
        ListView listview=(ListView)inflate.findViewById(R.id.listview);
        TextView tv_none=(TextView)inflate.findViewById(R.id.tv_none);
        AddOPenHelper helper=new AddOPenHelper(getActivity());
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("moneyInfo", null, null, null, null, null, null);
        addMoneys = new ArrayList<>();
        while(cursor.moveToNext()){
            AddMoney addMoney = new AddMoney();
            addMoney.setId(cursor.getString(0));
            addMoney.setCarId(cursor.getString(1));
            addMoney.setMoney(cursor.getString(2));
            addMoney.setDate(cursor.getString(3));
            addMoneys.add(addMoney);
        }
        if(addMoneys !=null&& addMoneys.size()>0){
            listview.setAdapter(new Myadapter());
            tv_none.setVisibility(View.GONE);
        }else{
            tv_none.setVisibility(View.VISIBLE);
        }


        return inflate;
    }
    class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return addMoneys.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = View.inflate(getActivity(), R.layout.item_history, null);
            TextView tv_id=(TextView)inflate.findViewById(R.id.tv_id);
            TextView tv_carid=(TextView)inflate.findViewById(R.id.tv_carid);
            TextView tv_money=(TextView)inflate.findViewById(R.id.tv_money);
            TextView tv_date=(TextView)inflate.findViewById(R.id.tv_date);
            AddMoney addMoney = addMoneys.get(position);
            tv_id.setText(addMoney.getId());
            tv_carid.setText(addMoney.getCarId());
            tv_money.setText(addMoney.getMoney());
            tv_date.setText(addMoney.getDate());
            return inflate;
        }
    }
}
