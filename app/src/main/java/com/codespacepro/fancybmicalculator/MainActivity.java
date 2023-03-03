package com.codespacepro.fancybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText Weight, HeightFT, HeightIN;
    MaterialButton Calculate;
    TextView Result;

    LottieAnimationView animationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Weight = (TextInputEditText) findViewById(R.id.weight);
        HeightFT = (TextInputEditText) findViewById(R.id.heightft);
        HeightIN = (TextInputEditText) findViewById(R.id.heightin);
        Result = (TextView) findViewById(R.id.result);
        Calculate = (MaterialButton) findViewById(R.id.calculate);

        animationView = (LottieAnimationView) findViewById(R.id.animationView);
        animationView.setImageAssetsFolder("image/");


        Calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                View view = getLayoutInflater().inflate(R.layout.sample_toast, (ViewGroup) findViewById(R.id.layout_sample));
                Toast toast = new Toast(MainActivity.this);
                toast.setView(view);
                TextView txt = view.findViewById(R.id.errorMessage);


                if (Weight.getText().toString().equals("")) {
                    txt.setText("Weight Value can't be Null");
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    // Toast.makeText(MainActivity.this, "Weight Values Can't be Null", Toast.LENGTH_SHORT).show();
                } else if (HeightFT.getText().toString().equals("")) {
                    txt.setText("FT Value can't be Null");
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();

                } else if (HeightIN.getText().toString().equals("")) {
                    txt.setText("Inch Value can't be Null");
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    int wt = Integer.parseInt(Weight.getText().toString());
                    int ft = Integer.parseInt(HeightFT.getText().toString());
                    int in = Integer.parseInt(HeightIN.getText().toString());


                    int totalIn = ft * 12 + in;

                    double totalCM = totalIn * 2.53;
                    double totalM = totalCM / 100;

                    double bmi = wt / (totalM * totalM);

                    animationView.setVisibility(View.VISIBLE);
                    if (bmi > 25) {
                        animationView.setAnimation(R.raw.warning_animation);
                        animationView.playAnimation();
                        Result.setTextColor(getResources().getColor(R.color.red));
                        Result.setVisibility(View.VISIBLE);
                        Result.setText("You're OverWeight.");
                    } else if (bmi < 18) {
                        animationView.setAnimation(R.raw.under);
                        animationView.playAnimation();
                        Result.setTextColor(getResources().getColor(R.color.under));
                        Result.setVisibility(View.VISIBLE);
                        Result.setText("You're UnderWeight.");

                    } else {
                        animationView.setAnimation(R.raw.perfect);
                        animationView.playAnimation();
                        Result.setTextColor(getResources().getColor(R.color.green));
                        Result.setVisibility(View.VISIBLE);
                        Result.setText("You're Perfect.");

                    }
                }
            }
        });
    }
}