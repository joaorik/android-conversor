package net.leocadio.joao.conversor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class TemperaturasActivity extends AppCompatActivity {

    private Temperaturas selectedEditTxt;
    CustomCelsius customCelsius;
    CustomFar customFar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperaturas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText celsiusValue = (EditText) findViewById(R.id.celciusText);
        final EditText far = (EditText) findViewById(R.id.farText);

        customCelsius = (CustomCelsius) findViewById(R.id.customCelsius);
        customFar = (CustomFar) findViewById(R.id.customFar);

        celsiusValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean hasFocus) {
                selectedEditTxt = Temperaturas.CELSIUS;
                celsiusValue.setFilters(new InputFilter[]{new InputFilterMinMax(-273.15, Double.MAX_VALUE, selectedEditTxt, getApplicationContext()), new InputFilter.LengthFilter(10)});
            }

        });

        far.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                selectedEditTxt = Temperaturas.FAHRENHEIT;
                far.setFilters(new InputFilter[]{new InputFilterMinMax(-459.67, Double.MAX_VALUE, selectedEditTxt, getApplicationContext()), new InputFilter.LengthFilter(10)});
            }
        });

        celsiusValue.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String tempValue = celsiusValue.getText().toString();
                if (!tempValue.equals("") && !tempValue.equals(".") && !tempValue.equals("-") && selectedEditTxt == Temperaturas.CELSIUS) {
                    double temp = Double.parseDouble(tempValue);
                    // fahrenheit
                    double outputFar = (temp * 1.8) + 32;
                    far.setText(String.valueOf(stripDecimal(outputFar)));

                    int farInt = (int) Math.round(outputFar);
                    customFar.setProgress(farInt);

                } else if (selectedEditTxt == Temperaturas.CELSIUS) {
                    far.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        far.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                String tempValue = far.getText().toString();

                if (!tempValue.equals("") && !tempValue.equals(".") && !tempValue.equals("-") && selectedEditTxt == Temperaturas.FAHRENHEIT) {

                    double temp = Double.parseDouble(tempValue);

                    //Celcius
                    double outputCelcius = (temp - 32) / 1.8;
                    celsiusValue.setText(String.valueOf((stripDecimal(outputCelcius))));

                    int celInt = (int) Math.round(outputCelcius);
                    customCelsius.setProgress(celInt);

                } else if (selectedEditTxt == Temperaturas.FAHRENHEIT) {
                    celsiusValue.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    public Double stripDecimal(Double temp) {
        String valor = String.format("%.2f", temp);
        return Double.parseDouble(valor);
    }

}
