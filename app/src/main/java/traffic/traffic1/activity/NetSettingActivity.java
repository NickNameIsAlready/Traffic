package traffic.traffic1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import traffic.traffic1.R;

public class NetSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_setting);
        EditText edt = (EditText)findViewById(R.id.d_edt);
        edt.addTextChangedListener(new TextWatcher() {
            String str;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                str = (String) charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(str.length()<5) return;
                int l = str.length();
                int sum = 0;
                for(int i=0;i<l;i++) {
                    char a = str.charAt(i);
                    sum+= a-'0';
                }
                if(sum>65535) {
                    edt.setText("65535");
                }
            }
        });
    }
}
