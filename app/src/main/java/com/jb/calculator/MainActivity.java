package com.jb.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mResult;
    private TextView mExpression;
    private String input;
    private static final int[] idArray = {R.id.bt_zero, R.id.bt_one, R.id.bt_two, R.id.bt_three, R.id.bt_four,
            R.id.bt_five, R.id.bt_six, R.id.bt_seven, R.id.bt_eight, R.id.bt_nine, R.id.bt_clear,
            R.id.bt_div, R.id.bt_mul, R.id.bt_add, R.id.bt_sub, R.id.bt_equal, R.id.bt_point};

    private final Button[] button = new Button[idArray.length];
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (i = 0; i < idArray.length; i++) {
            button[i] = (Button) findViewById(idArray[i]);
            button[i].setOnClickListener(this::onHandlerButtonClicked);
        }

        ImageView mIvBackspace = findViewById(R.id.iv_backspace);
        mExpression = findViewById(R.id.tv_expression);
        mIvBackspace.setOnClickListener(this::onBackspaceClicked);

        /*TextViews instances*/
        mResult = findViewById(R.id.tv_result);
        mExpression = findViewById(R.id.tv_expression);
    }

    private void onBackspaceClicked(View view) {
        String screen = mExpression.getText().toString();


        if (!screen.isEmpty()) {
            String substring = screen.substring(0, screen.length() - 1);
            mExpression.setText(substring);
            input = substring;
        }
        mResult.setText("");

    }

    private void onHandlerButtonClicked(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();

        switch (data) {
            case "C":
                input = "";
                mResult.setText("");
                break;
            case "*":
                solve();
                input += "*";
                break;
            case "=":
                solve();
                break;
            default:
                if (input == null) {
                    input = "";
                }
                if (data.equals("+") || data.equals("-") || data.equals("/")) {
                    solve();
                }
                input += data;
        }
        mExpression.setText(input);
    }

    private void solve() {
        if (input.split("\\*").length == 2) {

            String[] number = input.split("\\*");
            try {
                double mul = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
                String s = "";
                s += mul;
                //to remove the last digit .0 from integer result
                String[] n = s.split("\\.");
                if (n.length > 1) {
                    if (n[1].equals("0")) {
                        s = n[0];
                    }
                }
                mResult.setText(s);
            } catch (Exception ignored) {
            }
        } else if (input.split("/").length == 2) {

            String[] number = input.split("/");
            try {
                double div = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
                String s = "";
                s += div;
                //to remove the last digit .0 from integer result
                String[] n = s.split("\\.");
                if (n.length > 1) {
                    if (n[1].equals("0")) {
                        s = n[0];
                    }
                }
                mResult.setText(s);
            } catch (Exception ignored) {
            }
        } else if (input.split("\\+").length == 2) {

            String[] number = input.split("\\+");
            try {
                double sum = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
                String s = "";
                s += sum;
                //to remove the last digit .0 from integer result
                String[] n = s.split("\\.");
                if (n.length > 1) {
                    if (n[1].equals("0")) {
                        s = n[0];
                    }
                }
                mResult.setText(s);
            } catch (Exception ignored) {
            }
        } else if (input.split("-").length == 2) {

            String[] number = input.split("-");
            //to sub from a negative number
            if (number[0].equals("") && number.length == 2) {
                number[0] = 0 + "";
            }
            try {
                double sub = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                String s = "";
                s += sub;
                //to remove the last digit .0 from integer result
                String[] n = s.split("\\.");
                if (n.length > 1) {
                    if (n[1].equals("0")) {
                        s = n[0];
                    }
                }
                mResult.setText(s);
            } catch (Exception ignored) {
            }
        }
    }
}