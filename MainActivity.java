package com.example.bmiv5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextWeight, editTextHeight;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = editTextWeight.getText().toString();
        String heightStr = editTextHeight.getText().toString();

        if (TextUtils.isEmpty(weightStr) || TextUtils.isEmpty(heightStr)) {
            Toast.makeText(this, "Please enter weight and height.", Toast.LENGTH_SHORT).show();
            return;
        }

        float weight, height;
        try {
            weight = Float.parseFloat(weightStr);
            height = Float.parseFloat(heightStr) / 100; // Convert cm to m
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid weight or height.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (weight <= 0 || height <= 0) {
            Toast.makeText(this, "Weight and height must be greater than zero.", Toast.LENGTH_SHORT).show();
            return;
        }

        float bmi = weight / (height * height);

        String bmiCategory;
        if (bmi < 18.5) {
            bmiCategory = "Underweight \nRisk: Malnutrition risk";
        } else if (bmi < 25) {
            bmiCategory = "Normal weight \nRisk: Low risk";
        } else if (bmi < 30) {
            bmiCategory = "Overweight \nRisk: Enhanced risk";
        }
        else if(bmi >= 30 && bmi <= 34.9){
            bmiCategory = "Moderately Obese \nRisk: Medium risk";
        }
        else if(bmi >= 35 && bmi <= 39.9){
            bmiCategory = "Severely Obese \nRisk: High risk";
        }

        else {
            bmiCategory = "Very severely obese \nRisk: Very High risk";
        }

        String result = String.format("BMI: %.1f\nCategory: %s", bmi, bmiCategory);
        textViewResult.setText(result);
    }

    private void displayAboutUs() {
        String about = "BMI Calculator App\nVersion 5.0\n\nDeveloped by: Muhammad Aiman Ashraf Bin Azmi \nID: 202279239 \nGroup: D1 CS2405A \n\nGithub Link: https://github.com/Nvzna \n\n©2023 BMI Calculator App. All Rights Reserved" ;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About Developer")
                .setMessage(about)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            displayAboutUs();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
