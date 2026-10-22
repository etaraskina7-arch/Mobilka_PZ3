package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonAdd, buttonSubstract, buttonDivide, buttonMultiply, buttonClean, buttonExponentiation, buttonRoot;
    private TextView operation, result;
    private EditText number1, number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSubstract = (Button) findViewById(R.id.buttonSubtract);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonClean = (Button) findViewById(R.id.buttonClean);
        buttonExponentiation = (Button) findViewById(R.id.buttonExponentiation);
        buttonRoot = (Button) findViewById(R.id.buttonRoot);

        operation = (TextView) findViewById(R.id.operation);
        result = (TextView) findViewById(R.id.result);

        number1 = (EditText) findViewById(R.id.number1);
        number2 = (EditText) findViewById(R.id.number2);

        buttonAdd.setOnClickListener(this);
        buttonSubstract.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonClean.setOnClickListener(this);
        buttonExponentiation.setOnClickListener(this);
        buttonRoot.setOnClickListener(this);
    };

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.buttonClean) {
            number1.setText("");
            number2.setText("");
            operation.setText("Операция: ");
            result.setText("Результат: ");
            number1.requestFocus();
            return;
        }

        String firstText = number1.getText().toString().trim().replace(',', '.');
        String secondText = number2.getText().toString().trim().replace(',', '.');

        if (firstText.isEmpty() || secondText.isEmpty()){
            Toast.makeText(this, "Введите оба числа", Toast.LENGTH_SHORT).show();
            return;
        }

        double a;
        double b;
        try {
            a = Double.parseDouble(firstText);
            b = Double.parseDouble(secondText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Проверьте введённые числа", Toast.LENGTH_SHORT).show();
            return;
        }

        double answer;
        String sign = "";

        if (id == R.id.buttonAdd) {
            answer = a + b;
            sign = "+";
        } else if (id == R.id.buttonSubtract) {
            answer = a - b;
            sign = "−";
        } else if (id == R.id.buttonMultiply) {
            answer = a * b;
            sign = "×";
        } else if (id == R.id.buttonDivide) {
            if (b == 0) {
                Toast.makeText(this, "На ноль делить нельзя", Toast.LENGTH_SHORT).show();
                return;
            }
            answer = a / b;
            sign = "÷";
        } else if (id == R.id.buttonExponentiation) {
            answer = Math.pow(a, b);
            sign = "^";
        } else if (id == R.id.buttonRoot) {
            if (a >= 0){
                answer = Math.pow(a, 1.0 / b);
                sign = "^ 1/";
                operation.setText("Операция: " + a + " " + sign + "" + b);
                result.setText("Результат: " + answer);
                return;
            }
            Toast.makeText(this, "Подкоренное выражение должно быть не отрицательным", Toast.LENGTH_SHORT).show();
            return;
        } else
            return;

        operation.setText("Операция: " + a + " " + sign + " " + b);
        result.setText("Результат: " + answer);
    }
}