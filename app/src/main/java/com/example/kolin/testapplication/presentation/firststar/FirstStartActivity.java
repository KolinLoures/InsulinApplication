package com.example.kolin.testapplication.presentation.firststar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.VitalCharacteristic;
import com.example.kolin.testapplication.domain.groups.DayGroup;
import com.example.kolin.testapplication.presentation.MainActivity;

public class FirstStartActivity extends AppCompatActivity implements FirstStartView {

    private Toolbar toolbar;

    private TextView textViewErrorHE;
    private TextView textViewErrorGI;
    private TextView textViewErrorKTwo;
    private TextView textViewErrorKOne;

    private EditText editTextHE;
    private EditText editTextGI;
    private EditText editTextKOne;
    private EditText editTextKTwo;

    private Button buttonSave;

    private SharedPreferences sharedPref;

    private FirstStartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);

        sharedPref = getSharedPreferences(getString(R.string.preferences_first_key), MODE_PRIVATE);

        toolbar = (Toolbar) findViewById(R.id.first_start_activity_toolbar);

        setSupportActionBar(toolbar);
        setTitle(getString(R.string.main_indexes));

        textViewErrorHE = (TextView) findViewById(R.id.first_start_he_error);
        textViewErrorGI = (TextView) findViewById(R.id.first_start_gi_error);
        textViewErrorKOne = (TextView) findViewById(R.id.first_start_k_one_error);
        textViewErrorKTwo = (TextView) findViewById(R.id.first_start_k_two_error);

        editTextHE = (EditText) findViewById(R.id.first_start_edit_he);
        setTextWatcherHe();
        editTextGI = (EditText) findViewById(R.id.first_start_edit_gi);
        setTextWatcherGi();
        editTextKOne = (EditText) findViewById(R.id.first_start_edit_k_one);
        setTextWatcherKOne();
        editTextKTwo = (EditText) findViewById(R.id.first_start_edit_k_two);
        setTextWatcherKTwo();

        buttonSave = (Button) findViewById(R.id.first_start_btn_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSave();
            }
        });

        presenter = new FirstStartPresenter();
        presenter.attachView(this);
    }


    private void setTextWatcherGi() {
        editTextGI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty() || Double.parseDouble(s.toString()) <= 0) {
                    textViewErrorGI.setVisibility(View.VISIBLE);
                } else {
                    textViewErrorGI.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setTextWatcherHe() {
        editTextHE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty() || Double.valueOf(s.toString()) <= 0) {
                    textViewErrorHE.setVisibility(View.VISIBLE);
                } else {
                    textViewErrorHE.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setTextWatcherKOne() {
        editTextKOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty() || Double.parseDouble(s.toString()) <= 0) {
                    textViewErrorKOne.setVisibility(View.VISIBLE);
                } else {
                    textViewErrorKOne.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void setTextWatcherKTwo() {
        editTextKTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty() || Double.parseDouble(s.toString()) <= 0) {
                    textViewErrorKTwo.setVisibility(View.VISIBLE);
                } else {
                    textViewErrorKTwo.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        editTextGI.addTextChangedListener(null);
        editTextHE.addTextChangedListener(null);
        editTextKOne.addTextChangedListener(null);
        editTextKTwo.addTextChangedListener(null);
        super.onDestroy();
    }

    @Override
    public void showToast(String title) {
        Toast.makeText(getBaseContext(), title, Toast.LENGTH_LONG).show();
    }


    private void onClickSave(){
        if (!presenter.isCorrectEditText(editTextGI.getText().toString())
                || !presenter.isCorrectEditText(editTextHE.getText().toString())
                || !presenter.isCorrectEditText(editTextKOne.getText().toString())
                || !presenter.isCorrectEditText(editTextKTwo.getText().toString())) {

            showToast("Заполните пустые поля!");
        } else {
            VitalCharacteristic vitalCharacteristic = new VitalCharacteristic();
            vitalCharacteristic.setName(DayGroup.MAIN);
            vitalCharacteristic.setHe(Double.valueOf(editTextHE.getText().toString()));
            vitalCharacteristic.setGi(Double.valueOf(editTextGI.getText().toString()));
            vitalCharacteristic.setkOne(Double.valueOf(editTextKOne.getText().toString()));
            vitalCharacteristic.setkTwo(Double.valueOf(editTextKTwo.getText().toString()));

            presenter.addMainVitalCharacteristic(vitalCharacteristic);
            saveSharedPreferences();
            startMainActivity();
            finish();
        }
    }

    private void saveSharedPreferences(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.preferences_first_value), true);
        editor.apply();
    }

    private void startMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
