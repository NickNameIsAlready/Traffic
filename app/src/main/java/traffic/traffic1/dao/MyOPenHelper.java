package traffic.traffic1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asdf on 2017/5/15.
 */

public class MyOPenHelper extends SQLiteOpenHelper {
    private  String sql="Create Table info(varchar id ,varchar number );";

    public MyOPenHelper(Context context) {
        super(context,"BusInfo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert(SQLiteDatabase db,String id,String number){

        db.insert("info",null,createvalues(id,number));
    }

    private ContentValues createvalues(String id, String number) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("number",number);
        return contentValues;

    }


}
