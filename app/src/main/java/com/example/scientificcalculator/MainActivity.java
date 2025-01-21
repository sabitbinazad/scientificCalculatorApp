package com.example.scientificcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView display;

    // Variables to store operands and operator
    private double value1 = 0;
    private String operator = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize display
        display = findViewById(R.id.display);

        // Initialize buttons and set click listeners
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonAdd, R.id.buttonSubtract,
                R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonDot,
                R.id.buttonClear, R.id.buttonEqual, R.id.buttonSqrt,
                R.id.buttonPower, R.id.buttonPi, R.id.buttonSin,
                R.id.buttonCos, R.id.buttonTan, R.id.buttonExp
        };

        for (int id : buttonIds) {
            Button btn = findViewById(id);
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String buttonText = btn.getText().toString();
        Log.d("MainActivity", "Button clicked: " + buttonText);

        switch (buttonText) {
            case "C":
                display.setText("");
                value1 = 0;
                operator = "";
                isNewOperation = true;
                break;
            case "=":
                calculateResult();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
                setOperator(buttonText);
                break;
            case "sqrt":
                performSqrt();
                break;
            case "π":
                display.append(String.valueOf(Math.PI));
                isNewOperation = false;
                break;
            case "exp":
                performExp();
                break;
            case "sin":
                performTrigFunction("sin");
                break;
            case "cos":
                performTrigFunction("cos");
                break;
            case "tan":
                performTrigFunction("tan");
                break;
            default:
                if (isNewOperation) {
                    display.setText("");
                    isNewOperation = false;
                }
                display.append(buttonText);
                break;
        }
    }

    private void setOperator(String op) {
        try {
            value1 = Double.parseDouble(display.getText().toString());
            operator = op;
            isNewOperation = true;
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }

    private void calculateResult() {
        try {
            double value2 = Double.parseDouble(display.getText().toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = value1 + value2;
                    break;
                case "-":
                    result = value1 - value2;
                    break;
                case "*":
                    result = value1 * value2;
                    break;
                case "/":
                    if (value2 == 0) {
                        display.setText("Error: Div by 0");
                        return;
                    }
                    result = value1 / value2;
                    break;
                case "^":
                    result = Math.pow(value1, value2);
                    break;
                default:
                    // Handle expressions or other operators if needed
                    display.setText("Error");
                    return;
            }

            display.setText(String.valueOf(result));
            operator = "";
            isNewOperation = true;
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }

    private void performSqrt() {
        try {
            double value = Double.parseDouble(display.getText().toString());
            if (value < 0) {
                display.setText("Error: Negative");
                return;
            }
            double result = Math.sqrt(value);
            display.setText(String.valueOf(result));
            isNewOperation = true;
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }

    private void performExp() {
        try {
            double value = Double.parseDouble(display.getText().toString());
            double result = Math.exp(value);
            display.setText(String.valueOf(result));
            isNewOperation = true;
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }

    private void performTrigFunction(String func) {
        try {
            double value = Double.parseDouble(display.getText().toString());
            double radians = Math.toRadians(value);
            double result = 0;

            switch (func) {
                case "sin":
                    result = Math.sin(radians);
                    break;
                case "cos":
                    result = Math.cos(radians);
                    break;
                case "tan":
                    result = Math.tan(radians);
                    break;
            }

            display.setText(String.valueOf(result));
            isNewOperation = true;
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }
}

