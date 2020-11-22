package com.example.currencycalculator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity  {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonConvert, buttonEqual, buttonC;
    Spinner spinner1, spinner2;
    EditText shownText;
    Currencies apiCurs;
    float mValueOne, mValueTwo;

    boolean isAddition, mSubtract, isMultiplication, isDivision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button0 =  findViewById(R.id.button0);
        button1 =  findViewById(R.id.button1);
        button2 =  findViewById(R.id.button2);
        button3 =  findViewById(R.id.button3);
        button4 =  findViewById(R.id.button4);
        button5 =  findViewById(R.id.button5);
        button6 =  findViewById(R.id.button6);
        button7 =  findViewById(R.id.button7);
        button8 =  findViewById(R.id.button8);
        button9 =  findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        buttonC = findViewById(R.id.buttonC);
        buttonAdd = findViewById(R.id.buttonadd);
        buttonSub = findViewById(R.id.buttonsub);
        buttonMul = findViewById(R.id.buttonmul);
        buttonDivision = findViewById(R.id.buttondiv);
        buttonConvert = findViewById(R.id.buttonConvert);
        buttonEqual = findViewById(R.id.buttoneql);
        shownText = findViewById(R.id.edt1);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        Currencies curS = new Currencies();
        apiCurs = curS;
        addItemsOnSpinners();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownText.setText(shownText.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownText.setText(shownText.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownText.setText(shownText.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownText.setText(shownText.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownText.setText(shownText.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownText.setText(shownText.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownText.setText(shownText.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownText.setText(shownText.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownText.setText(shownText.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownText.setText(shownText.getText() + "0");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (shownText == null) {
                    shownText.setText("");
                } else {
                    mValueOne = Float.parseFloat(shownText.getText() + "");
                    isAddition = true;
                    shownText.setText(null);
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(shownText.getText() + "");
                mSubtract = true;
                shownText.setText(null);
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(shownText.getText() + "");
                isMultiplication = true;
                shownText.setText(null);
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(shownText.getText() + "");
                isDivision = true;
                shownText.setText(null);
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownText.setText("");
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueTwo = Float.parseFloat(shownText.getText() + "");

                if (isAddition == true) {
                    shownText.setText(mValueOne + mValueTwo + "");
                    isAddition = false;
                }

                if (mSubtract == true) {
                    shownText.setText(mValueOne - mValueTwo + "");
                    mSubtract = false;
                }

                if (isMultiplication == true) {
                    shownText.setText(mValueOne * mValueTwo + "");
                    isMultiplication = false;
                }

                if (isDivision == true) {
                    shownText.setText(mValueOne / mValueTwo + "");
                    isDivision = false;
                }
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownText.setText(shownText.getText() + ".");
            }
        });

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromCur = spinner1.getSelectedItem().toString();
                String toCur = spinner2.getSelectedItem().toString();
                float fromVal = apiCurs.rates.get(fromCur).getAsFloat();
                float toVal = apiCurs.rates.get(toCur).getAsFloat();
                float modifier = toVal / fromVal;
                shownText.setText(Float.parseFloat(shownText.getText() + "") * modifier + "");
            }
        });
    }

    public void addItemsOnSpinners() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        List<String> list = new ArrayList<String>();
        for (String s:apiCurs.currencies)
        {
            list.add(s);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
        spinner1.setAdapter(dataAdapter);
    }
}
