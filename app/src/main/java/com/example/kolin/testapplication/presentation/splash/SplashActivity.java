package com.example.kolin.testapplication.presentation.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.presentation.MainActivity;
import com.example.kolin.testapplication.presentation.firststar.FirstStartActivity;

public class SplashActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preferences_first_key), MODE_PRIVATE);
        boolean first = sharedPreferences.getBoolean(getString(R.string.preferences_first_value), false);
        if (first) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            intent = new Intent(this, FirstStartActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
