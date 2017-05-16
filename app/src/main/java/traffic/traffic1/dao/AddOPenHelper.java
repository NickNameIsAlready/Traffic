package traffic.traffic1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asdf on 2017/5/15.
 */

public class AddOPenHelper extends SQLiteOpenHelper {
    private String sql="Create Table moneyInfo (id varchar,CarId varchar,money varchar,date varchar);";
    public AddOPenHelper(Context context) {
        super(context, "MoneyInfo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert(SQLiteDatabase db,String id,String CarId,String money,String date){
        db.insert("moneyInfo",null,contentvalues(id,CarId,money,date));

    }

    private ContentValues contentvalues(String id, String carId, String money, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("CarId",carId);
        contentValues.put("money",money);
        contentValues.put("date",date);
        return  contentValues;
    }
}
