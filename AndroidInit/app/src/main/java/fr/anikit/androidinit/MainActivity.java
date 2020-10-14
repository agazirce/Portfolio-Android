package fr.anikit.androidinit;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String defaut;
    private String megaString;

    EditText weight = null;
    EditText height = null;

    RadioGroup group = null;
    CheckBox function = null;

    Button calc = null;
    Button reset = null;

    TextView result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaut = getString(R.string.result);
        megaString = getString(R.string.mega_string);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);

        group = findViewById(R.id.group);
        function = findViewById(R.id.function);

        calc = findViewById(R.id.calc);
        reset = findViewById(R.id.reset);

        result = findViewById(R.id.result);

        calc.setOnClickListener(calcListener);
        reset.setOnClickListener(resetListener);
        weight.addTextChangedListener(textWatcher);
        height.addTextChangedListener(textWatcher);
        function.setOnClickListener(checkedListener);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(defaut);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private OnClickListener calcListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!function.isChecked()){
                String h = height.getText().toString();
                String w = weight.getText().toString();

                float hValue = Float.valueOf(h);

                if (hValue == 0){
                    Toast.makeText(MainActivity.this, getString(R.string.low_height), Toast.LENGTH_SHORT).show();
                } else {
                    float wValue = Float.valueOf(w);

                    if (group.getCheckedRadioButtonId() == R.id.cm) {
                        hValue = hValue / 100;
                    }

                    hValue = (float)Math.pow(hValue, 2);
                    float imc = wValue / hValue;

                    result.setText(getString(R.string.imc, imc));
                }
            } else {
                result.setText(megaString);
            }
        }
    };

    private OnClickListener resetListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            result.setText(defaut);
            height.getText().clear();
            weight.getText().clear();
        }
    };

    private OnClickListener checkedListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!((CheckBox)v).isChecked() && result.getText().equals(megaString)) {
                result.setText(defaut);
            }
        }
    };
}