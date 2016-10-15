package com.example.kolin.testapplication.presentation.index.addingdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.VitalCharacteristic;
import com.example.kolin.testapplication.domain.groups.DayGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class AddIndexDialogFragment extends AppCompatDialogFragment implements AddIndexDialogView {

    private static final String ARG = "vital_characteristic";

    private Spinner spinner;

    private TextView textViewHeError;
    private TextView textViewGiError;
    private TextView textViewKOneError;
    private TextView textViewKTwoError;

    private EditText editTextHe;
    private EditText editTextGi;
    private EditText editTextKOne;
    private EditText editTextKTwo;

    private Button btnAdd;
    private Button btnCancel;

    private AddIndexDialogPresenter presenter;

    private ArrayAdapter<String> adapter;

    public AddIndexDialogFragment() {
        // Required empty public constructor
    }


    public static AddIndexDialogFragment newInstance(VitalCharacteristic vitalCharacteristic) {
        AddIndexDialogFragment fragment = new AddIndexDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG, vitalCharacteristic);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        List<String> stringList = new ArrayList<>();
        stringList.addAll(DayGroup.getList());

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, stringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        presenter = new AddIndexDialogPresenter();
        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_index_dialog, container, false);
        spinner = (Spinner) view.findViewById(R.id.dialog_add_index_spinner);

        editTextGi = (EditText) view.findViewById(R.id.dialog_add_index_edit_gi);
        editTextHe = (EditText) view.findViewById(R.id.dialog_add_index_edit_he);
        editTextKOne = (EditText) view.findViewById(R.id.dialog_add_index_edit_k_one);
        editTextKTwo = (EditText) view.findViewById(R.id.dialog_add_index_edit_k_two);

        textViewGiError = (TextView) view.findViewById(R.id.dialog_add_index_gi_error);
        setTextWatcherGi();

        textViewHeError = (TextView) view.findViewById(R.id.dialog_add_index_he_error);
        setTextWatcherHe();

        textViewKOneError = (TextView) view.findViewById(R.id.dialog_add_index_k_one_error);
        setTextWatcherKOne();

        textViewKTwoError = (TextView) view.findViewById(R.id.dialog_add_index_k_two_error);
        setTextWatcherKTwo();

        btnAdd = (Button) view.findViewById(R.id.dialog_add_index_btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnAdd();
            }
        });
        btnCancel = (Button) view.findViewById(R.id.dialog_add_index_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        spinner.setAdapter(adapter);
        Bundle arguments = getArguments();
        if (arguments != null) {
            VitalCharacteristic vitalCharacteristic = (VitalCharacteristic) arguments.getSerializable(ARG);
            if (vitalCharacteristic != null) {
                setEditText(vitalCharacteristic);
            }
        }


        return view;
    }

    private void onClickBtnAdd() {
        if (!presenter.isCorrectEditText(editTextGi.getText().toString())
                || !presenter.isCorrectEditText(editTextHe.getText().toString())
                || !presenter.isCorrectEditText(editTextKOne.getText().toString())
                || !presenter.isCorrectEditText(editTextKTwo.getText().toString())) {

            showToast("Заполните пустые поля!");
        } else {

            VitalCharacteristic vitalCharacteristic = new VitalCharacteristic();
            vitalCharacteristic.setName((String) spinner.getSelectedItem());
            vitalCharacteristic.setHe(Double.valueOf(editTextHe.getText().toString()));
            vitalCharacteristic.setGi(Double.valueOf(editTextGi.getText().toString()));
            vitalCharacteristic.setkOne(Double.valueOf(editTextKOne.getText().toString()));
            vitalCharacteristic.setkTwo(Double.valueOf(editTextKTwo.getText().toString()));

            presenter.addVitalCharacteristic(vitalCharacteristic);
            dismiss();
        }
    }

    private void setTextWatcherGi() {
        editTextGi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty() || Double.parseDouble(s.toString()) <= 0) {
                    textViewGiError.setVisibility(View.VISIBLE);
                } else {
                    textViewGiError.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setTextWatcherHe() {
        editTextHe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty() || Double.valueOf(s.toString()) <= 0) {
                    textViewHeError.setVisibility(View.VISIBLE);
                } else {
                    textViewHeError.setVisibility(View.INVISIBLE);
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
                    textViewKOneError.setVisibility(View.VISIBLE);
                } else {
                    textViewKOneError.setVisibility(View.INVISIBLE);
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
                    textViewKTwoError.setVisibility(View.VISIBLE);
                } else {
                    textViewKTwoError.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void unSubAllListeners() {
        editTextGi.addTextChangedListener(null);
        editTextHe.addTextChangedListener(null);
        editTextKOne.addTextChangedListener(null);
        editTextKTwo.addTextChangedListener(null);
        btnAdd.setOnClickListener(null);
        btnCancel.setOnClickListener(null);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        unSubAllListeners();
        super.onDetach();
    }

    @Override
    public void showToast(String title) {
        Toast.makeText(getContext(), title, Toast.LENGTH_LONG).show();
    }

    public void setEditText(VitalCharacteristic vitalCharacteristic){
        Locale locale = Locale.ENGLISH;
        spinner.setSelection(DayGroup.getDayId(vitalCharacteristic.getName()));
        editTextGi.setText(String.format(locale, "%.2f", vitalCharacteristic.getGi()));
        editTextHe.setText(String.format(locale, "%.2f", vitalCharacteristic.getHe()));
        editTextKOne.setText(String.format(locale, "%.2f", vitalCharacteristic.getkOne()));
        editTextKTwo.setText(String.format(locale, "%.2f", vitalCharacteristic.getkTwo()));
    }
}
