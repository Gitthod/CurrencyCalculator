package com.example.currencycalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

enum Operations{
    ADDITION, MULTIPLICATION, DIVISION, SUBTRACTION, NOOP
}


public class MainActivity extends AppCompatActivity  {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonConvert, buttonEqual, buttonC, backspace;
    Spinner spinner1, spinner2;
    EditText shownText;
    Currencies apiCurs;
    float accumulator = 0;

    Operations currentOp = Operations.NOOP;

    @SuppressLint("SetTextI18n")
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
        backspace = findViewById(R.id.backspace);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        apiCurs = new Currencies();
        addItemsOnSpinners();
        shownText.setText(null);

        button1.setOnClickListener(v -> shownText.setText(shownText.getText() + "1"));

        button2.setOnClickListener(v -> shownText.setText(shownText.getText() + "2"));

        button3.setOnClickListener(v -> shownText.setText(shownText.getText() + "3"));

        button4.setOnClickListener(v -> shownText.setText(shownText.getText() + "4"));

        button5.setOnClickListener(v -> shownText.setText(shownText.getText() + "5"));

        button6.setOnClickListener(v -> shownText.setText(shownText.getText() + "6"));

        button7.setOnClickListener(v -> shownText.setText(shownText.getText() + "7"));

        button8.setOnClickListener(v -> shownText.setText(shownText.getText() + "8"));

        button9.setOnClickListener(v -> shownText.setText(shownText.getText() + "9"));

        button0.setOnClickListener(v -> shownText.setText(shownText.getText() + "0"));

        buttonAdd.setOnClickListener(v -> {
            /* currentOp is NOOP in the beginning or after clicking on C button. */
            if (currentOp == Operations.NOOP) {
                try {
                    accumulator = Float.parseFloat(shownText.getText() + "");
                }
                catch (NumberFormatException e) {
                    /* Pass. */
                }

            }
            evaluateCurrent ();
            currentOp = Operations.ADDITION;

            /* Print the value of the accumulator in the first line. */
            if (accumulator != 0) {
                shownText.setText(accumulator + " +\n");
            }
        });

        buttonSub.setOnClickListener(v -> {
            /* currentOp is NOOP in the beginning or after clicking on C button. */
            if (currentOp == Operations.NOOP) {
                try {
                    accumulator = Float.parseFloat(shownText.getText() + "");
                }
                catch (NumberFormatException e) {
                    /* Pass. */
                }
            }
            evaluateCurrent ();
            currentOp = Operations.SUBTRACTION;

            /* Print the value of the accumulator in the first line. */
            if (accumulator != 0) {
                shownText.setText(accumulator + " -\n");
            }
        });

        buttonMul.setOnClickListener(v -> {
            /* currentOp is NOOP in the beginning or after clicking on C button. */
            if (currentOp == Operations.NOOP) {
                try {
                    accumulator = Float.parseFloat(shownText.getText() + "");
                }
                catch (NumberFormatException e) {
                    /* Pass. */
                }
            }
            evaluateCurrent ();
            currentOp = Operations.MULTIPLICATION;

            /* Print the value of the accumulator in the first line. */
            if (accumulator != 0) {
                shownText.setText(accumulator + " *\n");
            }
        });

        buttonDivision.setOnClickListener(v -> {
            /* currentOp is NOOP in the beginning or after clicking on C button. */
            if (currentOp == Operations.NOOP) {
                try {
                    accumulator = Float.parseFloat(shownText.getText() + "");
                }
                catch (NumberFormatException e) {
                    /* Pass. */
                }
            }
            evaluateCurrent ();
            currentOp = Operations.DIVISION;

            /* Print the value of the accumulator in the first line. */
            if (accumulator != 0) {
                shownText.setText(accumulator + " /\n");
            }
        });

        buttonC.setOnClickListener(v -> {
            shownText.setText("");
            accumulator = 0;
        });

        backspace.setOnClickListener(v -> {
            String text =  shownText.getText() + "";
            if (0 < text.length())
            {
                try {
                    /* Check if the new string is an actual number otherwise clear the text. */
                    text = text.substring(0, text.length() - 1);
                    Double.parseDouble(text);
                    shownText.setText(text);
                }
                catch(NumberFormatException e) {
                    shownText.setText(null);
                }
            }
        });

        buttonEqual.setOnClickListener(v -> {

            evaluateCurrent ();
            shownText.setText(accumulator + "");
            currentOp = Operations.NOOP;
        });

        button10.setOnClickListener(v -> {
            try
            {
                /* Check if the new string is an actual number otherwise ignore. */
                String text = shownText.getText() + ".";
                Double.parseDouble(shownText.getText() + ".");
                shownText.setText(text);
            } catch (NumberFormatException e)
            {
                /* Do nothing since new dot doesn't create a valid number */
            }
        });

        buttonConvert.setOnClickListener(v -> {
            String fromCur = spinner1.getSelectedItem().toString();
            String toCur = spinner2.getSelectedItem().toString();
            float fromVal = apiCurs.rates.get(fromCur).getAsFloat();
            float toVal = apiCurs.rates.get(toCur).getAsFloat();
            float modifier = toVal / fromVal;

            try {
                /* Check in case the EditText can't be converted to a float. */
                shownText.setText(Float.parseFloat(shownText.getText() + "") * modifier + "");
            }catch(NumberFormatException e) {
                /* Pass. */
            }
        });
    }

    public void addItemsOnSpinners() {

        spinner2 = findViewById(R.id.spinner2);
        spinner1 = findViewById(R.id.spinner1);

        List<String> list = new ArrayList<>();
        list.addAll(apiCurs.currencies);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
        spinner1.setAdapter(dataAdapter);
    }

    private void evaluateCurrent ()
    {
        float typed;
        try {
            String visibleText = shownText.getText() + "";
            String[] arrOfStr = visibleText.split("\n", 2);
            if (arrOfStr.length == 2){
                typed = Float.parseFloat(arrOfStr[1]);
            }
            else
            {
                typed = Float.parseFloat(visibleText);
            }

        } catch (NumberFormatException e) {
            typed = 0;
        }
        if (shownText.getText().toString().equals("") == false) {
            if (currentOp == Operations.ADDITION) {
                accumulator += typed;
            }

            if (currentOp == Operations.SUBTRACTION) {
                accumulator -= typed;
            }

            if (currentOp == Operations.MULTIPLICATION) {
                accumulator *= typed;
            }

            if (currentOp == Operations.DIVISION) {
                accumulator /= typed;
            }
        }
    }
}
