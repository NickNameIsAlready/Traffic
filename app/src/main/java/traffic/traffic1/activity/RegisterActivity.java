package traffic.traffic1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import traffic.traffic1.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText editText = (EditText)findViewById(R.id.editText6);
        editText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
    }
}
