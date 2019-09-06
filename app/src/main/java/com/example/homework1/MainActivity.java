/*
 * Homework 2
 * Main Activity
 * Joshua Hutcheson
 * Ormelia Robinson
 * Hailey Brown
 * */

package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_bmi;
    private TextView tv_weight;
    private TextView tv_height;
    private TextView tv_result;
    private TextView tv_status;

    private Button button_calculate;
    private EditText et_pounds;
    private EditText et_feet;
    private EditText et_inches;

    double bmi;
    double pounds;
    int inches;
    int feet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BMI Calculator");

        tv_bmi = findViewById(R.id.tv_bmi);
        tv_weight = findViewById(R.id.tv_weight);
        tv_height = findViewById(R.id.tv_height);
        tv_result= findViewById(R.id.tv_result);
        tv_status = findViewById(R.id.tv_status);

        button_calculate = findViewById(R.id.button);;
        et_pounds = findViewById(R.id.et_pounds);
        et_feet = findViewById(R.id.et_feet);
        et_inches = findViewById(R.id.et_inches);

        bmi =0.0;
        pounds =0.0;
        inches = 0;
        feet = 0;

        tv_bmi.setText(getResources().getString(R.string.bmi));
        tv_height.setText(getResources().getString(R.string.height));
        tv_weight.setText(getResources().getString(R.string.weight));
        button_calculate.setText(getResources().getString(R.string.calculate_bmi));

        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String tempPounds = et_pounds.getText().toString();
                String tempFeet = et_feet.getText().toString();
                String tempInches = et_inches.getText().toString();
                String[] status_array = getResources().getStringArray(R.array.bmi_options);
                int heightInInches = feet*12+inches;

                //if statement storing if it is zero, no input for weight
                if(tempPounds!=null && !tempPounds.equals(""))
                    pounds = Double.parseDouble(et_pounds.getText().toString());
                //if statement storing if it is zero, no input for height
                if(tempFeet!=null && !tempFeet.equals(""))
                    feet = Integer.parseInt(et_feet.getText().toString());
                if(tempInches!=null && !tempInches.equals(""))
                    inches = Integer.parseInt(et_inches.getText().toString());

                //set error
                if(et_pounds.getText().toString().equals("")){
                    et_pounds.setError("Invalid inputs.");
                }
                if(et_feet.getText().toString().equals("")){
                    et_feet.setError("Invalid inputs.");
                }
                if(et_inches.getText().toString().equals("")){
                    et_inches.setError("Invalid inputs.");
                }

                //Calculation
                bmi=((pounds/(heightInInches*heightInInches))*703);

                bmi=Math.round(bmi*10.0)/10.0;
                tv_result.setText(String.valueOf((float)bmi));

                //test calculation for different text status
                //under weight < 18.5
                if(bmi < 18.5){
                    tv_status.setText(String.valueOf(status_array[0]));
                    //18.5<normal weight<24.9
                }else if(18.5 <= bmi && bmi <= 24.9){
                    tv_status.setText(String.valueOf(status_array[1]));
                    //25<overweight<29.9
                }else if(25 <= bmi && bmi<= 29.9){
                    tv_status.setText(String.valueOf(status_array[2]));
                    //30<obesity
                }else if(30 <= bmi){
                    tv_status.setText(String.valueOf(status_array[3]));
                }



            }
        });
    }
}
