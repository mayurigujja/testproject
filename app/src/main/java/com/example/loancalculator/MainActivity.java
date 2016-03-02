package com.example.loancalculator;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends Activity {
    private EditText LoanAmount, InterestRate;
    private TextView MonthlyPaymentResult5, MonthlyPaymentResult10,MonthlyPaymentResult15;
    private TextView MonthlyPaymentResult20,MonthlyPaymentResult25,MonthlyPaymentResult30;


    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Drawable Background = findViewById(R.id.background).getBackground();
        Background.setAlpha(150);
        LoanAmount = (EditText) findViewById(R.id.loan_amount);
        InterestRate = (EditText)findViewById(R.id.interest_rate);

        MonthlyPaymentResult5 = (TextView)findViewById(R.id.monthly_payment_result);

        MonthlyPaymentResult10 = (TextView)findViewById(R.id.monthly_payment_result10);

        MonthlyPaymentResult15 = (TextView)findViewById(R.id.monthly_payment_result15);

        MonthlyPaymentResult20 = (TextView)findViewById(R.id.monthly_payment_result20);

        MonthlyPaymentResult25 = (TextView)findViewById(R.id.monthly_payment_result25);

        MonthlyPaymentResult30 = (TextView)findViewById(R.id.monthly_payment_result30);

        LoanAmount.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((InterestRate.getText().toString().trim().length() !=0 ))
                {
                    if(LoanAmount.getText().toString().trim().length() !=0)
                   show();
                }
            }
        });

        InterestRate.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ( (LoanAmount.getText().toString().trim().length()!=0))
                {
                    if(InterestRate.getText().toString().trim().length() !=0)
                    show();
                }
            }
        });
    }

    public void show()
    {
        double loanAmount = Double.parseDouble(LoanAmount.getText().toString());
        double interestRate = (Double.parseDouble(InterestRate.getText().toString()));
        double loanPeriod = 60.00;
        double r = interestRate/1200;
        double r1 = Math.pow(r+1,loanPeriod);

        double monthlyPayment;
        int i;
        double mpayments[]=new double[7];

        for(i=1;i<7;i++)
        {
            loanPeriod=i*60;
            r1= Math.pow(r+1,loanPeriod);
            if(r1!=1)
                monthlyPayment= (double) ((r+(r/(r1-1))) * loanAmount);
            else
                monthlyPayment=loanAmount/loanPeriod;
            mpayments[i-1]=monthlyPayment;
        }


        MonthlyPaymentResult5.setText("$" + new DecimalFormat("##.##").format(mpayments[0]));


        MonthlyPaymentResult10.setText("$" + new DecimalFormat("##.##").format(mpayments[1]));


        MonthlyPaymentResult15.setText("$" + new DecimalFormat("##.##").format(mpayments[2]));


        MonthlyPaymentResult20.setText("$" + new DecimalFormat("##.##").format(mpayments[3]));


        MonthlyPaymentResult25.setText("$" + new DecimalFormat("##.##").format(mpayments[4]));


        MonthlyPaymentResult30.setText("$"+ new DecimalFormat("##.##").format(mpayments[5]));
    }
}