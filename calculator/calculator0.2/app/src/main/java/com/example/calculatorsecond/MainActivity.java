package com.example.calculatorsecond;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
//НЕ ЗАБУДЬ что ты временно поставил протретную ориентацию в манифесте!
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] numberIds = new int[] {
                R.id.button0,
                R.id.button1,
                R.id.button2,
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6,
                R.id.button7,
                R.id.button8,
                R.id.button9
        };

        int[] actionsIds = new int[] {
                R.id.buttonPlus,
                R.id.buttonMinus,
                R.id.buttonMultiply,
                R.id.buttonShares,
                R.id.buttonDelete,
                R.id.buttonReset,
                R.id.buttonPoint,
                R.id.buttonEquals,
                R.id.buttonPercent,
        };

        text = findViewById(R.id.text);

        calculator = new Calculator();

        View.OnClickListener numberButtonClickListener = view -> {
            calculator.onNumPressed(view.getId());
            text.setText(calculator.getText());
        };

        View.OnClickListener actionButtonOnclickListener = view -> {
            calculator.onActionPressed(view.getId());
            text.setText(calculator.getText());
        };

        for (int i = 0; i < numberIds.length; i++) {
            findViewById(numberIds[i]).setOnClickListener(numberButtonClickListener);
        }

        for (int i = 0; i < actionsIds.length; i++) {
            findViewById(actionsIds[i]).setOnClickListener(actionButtonOnclickListener);
        }

        findViewById(R.id.buttonReset).setOnClickListener(view -> {
            calculator.reset();
            text.setText(calculator.getText());
        });
    }
}