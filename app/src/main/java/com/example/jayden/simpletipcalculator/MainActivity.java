package com.example.jayden.simpletipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText inputDallor;
    EditText editOther;
    RadioGroup radioGroup;
    TextView tip;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // connecting java variable with XML component
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        editOther = (EditText) findViewById(R.id.editOther);
        inputDallor = (EditText) findViewById(R.id.editText);
        tip = (TextView) findViewById(R.id.tip);
        total = (TextView) findViewById(R.id.total);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
// Save the user's current game state
        savedInstanceState.putString("input", inputDallor.getText().toString());
        savedInstanceState.putString("other", editOther.getText().toString());
// Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
// Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
// Restore state members from saved instance
        inputDallor.setText((savedInstanceState.getString("input")).toString());
        editOther.setText((savedInstanceState.getString("other")).toString());
    }

    public void onClickRadio(View view) {
        // checking whether you choose other or not. if you choose 'other' it makes editOther visible.
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.other:
                editOther.setVisibility(View.VISIBLE);
                break;
            default:
                editOther.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void onCalculateButtonClicked(View view) {     // event handler: when you click calculate button, you can get in here.
        // initializing
        double calculatedTip = 0.0;
        double calculatedTotal = 0.0;
        DecimalFormat df = new DecimalFormat("0.00");
        calculatedTip = Double.parseDouble(inputDallor.getText().toString());
        calculatedTotal = Double.parseDouble(inputDallor.getText().toString());
        tip.setText("");
        total.setText("");
        // checking what button do you click now.
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.percent15:
                //calculating and showing the result.
                tip.setText("Tip: " + df.format(calculatedTip * 0.15));
                calculatedTotal = calculatedTotal + (calculatedTotal * 0.15);
                total.setText("Total: " + df.format(calculatedTotal));
                break;
            case R.id.percent20:
                //calculating and showing the result.
                tip.setText("Tip : " + df.format(calculatedTip * 0.2));
                calculatedTotal = calculatedTotal + (calculatedTotal * 0.2);
                total.setText("Total: " + df.format(calculatedTotal));
                break;
            case R.id.other:
                editOther = (EditText) findViewById(R.id.editOther);
                //<exception handling>
                if (editOther.getText().toString().compareTo("") == 0)  // No input error
                    total.setText("ERROR: No Input");
                else {
                    double other = Double.parseDouble(editOther.getText().toString());
                    if (other < 1 || other > 100) {
                        total.setText("ERROR: You should input number from 1 to 100");   // incorrect input error. only 1 to 100.
                    } else {
                        //calculating and showing the result.
                        tip.setText("Tip : " + df.format(calculatedTip * (other / 100)));
                        calculatedTotal = calculatedTotal + (calculatedTotal * (other / 100));
                        total.setText("Total: " + df.format(calculatedTotal));
                        editOther.setText("");
                        break;
                    }
                }
        }
    }
}


