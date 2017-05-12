package traffic.traffic1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import traffic.traffic1.R;

public class Topic17Activity extends AppCompatActivity implements View.OnClickListener{
    ImageView plot1;
    ImageView plot2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic17);
        Button btn = (Button)findViewById(R.id.btn_17_look);
        btn.setOnClickListener(this);
        plot1 = (ImageView)findViewById(R.id.img_17_plot_1);
        plot2 = (ImageView)findViewById(R.id.img_17_plot_2);
    }

    @Override
    public void onClick(View view) {
        JsonObjectRequest plotCar = new JsonObjectRequest(Request.Method.POST,
                "http://192.168.0.105:8080/transportservice/type/jason/action/GetAllSense.do",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
