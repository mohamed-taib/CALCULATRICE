package com.example.calculatrice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;





public class MainActivity extends AppCompatActivity {

    private TextView tvExpression;
    private TextView tvResult;

    private StringBuilder expressionBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize views
        tvExpression = findViewById(R.id.tvrut);
        tvResult = findViewById(R.id.tvrut2);

        // Set OnClickListener for numeric buttons
        findViewById(R.id.btn0).setOnClickListener(onClickListener);
        findViewById(R.id.btn1).setOnClickListener(onClickListener);
        findViewById(R.id.btn2).setOnClickListener(onClickListener);
        findViewById(R.id.btn3).setOnClickListener(onClickListener);
        findViewById(R.id.btn4).setOnClickListener(onClickListener);
        findViewById(R.id.btn5).setOnClickListener(onClickListener);
        findViewById(R.id.btn6).setOnClickListener(onClickListener);
        findViewById(R.id.btn7).setOnClickListener(onClickListener);
        findViewById(R.id.btn8).setOnClickListener(onClickListener);
        findViewById(R.id.btn9).setOnClickListener(onClickListener);

        // Set OnClickListener for operator buttons
        findViewById(R.id.btnadd).setOnClickListener(onClickListener);
        findViewById(R.id.btnmins).setOnClickListener(onClickListener);
        findViewById(R.id.btnpr).setOnClickListener(onClickListener);
        findViewById(R.id.btndev).setOnClickListener(onClickListener);
        findViewById(R.id.btndot).setOnClickListener(onClickListener);

        findViewById(R.id.btnParenthesesLeft).setOnClickListener(textViewClickListener);
        findViewById(R.id.btnParenthesesRight).setOnClickListener(textViewClickListener);
        findViewById(R.id.btnPower).setOnClickListener(textViewClickListener);
        findViewById(R.id.btnSquareRoot).setOnClickListener(textViewClickListener);
        findViewById(R.id.btnCos).setOnClickListener(textViewClickListener);
        findViewById(R.id.btnSin).setOnClickListener(textViewClickListener);
        findViewById(R.id.btnTan).setOnClickListener(textViewClickListener);

        // Set OnClickListener for clear button
        findViewById(R.id.btnc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearExpression();
            }
        });

        // Set OnClickListener for equals button
        findViewById(R.id.btnrus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateExpression();
            }
        });

    }



    // OnClickListener for numeric and operator buttons
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            String buttonText = button.getText().toString();
            expressionBuilder.append(buttonText);
            updateExpression();
        }
    };
    private View.OnClickListener textViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            TextView textView = (TextView) v;
            String text = textView.getText().toString();
            expressionBuilder.append(text);
            updateExpression();

        }
    };



    // Update expression text view
    private void updateExpression() {
        tvExpression.setText(expressionBuilder.toString());
    }

    // Clear expression
    private void clearExpression() {
        expressionBuilder.setLength(0);
        tvExpression.setText("");
        tvResult.setText("");
    }


    public void onDeleteButtonClick(View view) {
        String currentExpression = tvExpression.getText().toString();
        if (currentExpression.length() > 0) {
            // Remove the last character from the expression
            String updatedExpression = currentExpression.substring(0, currentExpression.length() - 1);
            tvExpression.setText(updatedExpression);
        }
    }

    // Evaluate expression
    private void evaluateExpression() {
            try {
                String expressionString = tvExpression.getText().toString().replace("÷", "/").replace("×", "*");
                Expression expression = new ExpressionBuilder(expressionString).build();
                double result = expression.evaluate();
                tvResult.setText(String.valueOf(result));
            } catch (ArithmeticException e) {
                // Handle arithmetic exceptions such as division by zero
                tvResult.setText("Error");
            } catch (Exception e) {
                // Handle any other exceptions
                tvResult.setText("Invalid Expression");
            }
        }

    }

