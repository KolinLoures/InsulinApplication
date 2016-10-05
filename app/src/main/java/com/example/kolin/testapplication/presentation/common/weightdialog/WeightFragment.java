package com.example.kolin.testapplication.presentation.common.weightdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.Food;

public class WeightFragment extends AppCompatDialogFragment implements WeightView {

    private static final String ARG = "food";

    private TextView textViewTitle;
    private TextView textViewProductName;
    private TextView textViewErrorCount;
    private TextView textViewErrorWeight;


    private EditText editTextWeight;
    private EditText editTextCount;

    private Button btnAdd;
    private Button btnBack;

    private WeightPresenter presenter;
    private TextWatcher watcherCount;
    private TextWatcher watcherWeight;


    public WeightFragment() {
    }


    public static WeightFragment newInstance(Food food) {
        WeightFragment fragment = new WeightFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG, food);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new WeightPresenter();
        presenter.attachView(this);
        presenter.setCurrentFood((Food) getArguments().getSerializable(ARG));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight, container, false);
        textViewTitle = (TextView) view.findViewById(R.id.dialog_weight_title);
        textViewProductName = (TextView) view.findViewById(R.id.dialog_weight_product_name);

        textViewErrorCount = (TextView) view.findViewById(R.id.dialog_weight_error_count);
        textViewErrorCount.setVisibility(View.INVISIBLE);
        textViewErrorWeight = (TextView) view.findViewById(R.id.dialog_weight_error_weight);
        textViewErrorWeight.setVisibility(View.INVISIBLE);

        editTextWeight = (EditText) view.findViewById(R.id.dialog_weight_edit_text);
        editTextCount = (EditText) view.findViewById(R.id.dialog_weight_count);

        btnAdd = (Button) view.findViewById(R.id.dialog_weight_btn_add);
        btnBack = (Button) view.findViewById(R.id.dialog_weight_btn_back);

        editTextCount.addTextChangedListener(getTextWatcherCount());
        editTextWeight.addTextChangedListener(getTextWatcherWeight());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnAdd();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }


    private TextWatcher getTextWatcherCount() {
        watcherCount = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textViewErrorCount.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty() || Double.valueOf(s.toString()) <= 0) {
                    textViewErrorCount.setVisibility(View.VISIBLE);
                } else {
                    textViewErrorCount.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        return watcherCount;
    }

    private TextWatcher getTextWatcherWeight() {
        watcherWeight = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty() || 0 >= Double.valueOf(s.toString())) {
                    textViewErrorWeight.setVisibility(View.VISIBLE);
                } else {
                    textViewErrorWeight.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        return watcherWeight;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.showAllValues();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        watcherCount = null;
        watcherWeight = null;
        editTextCount.addTextChangedListener(null);
        editTextWeight.addTextChangedListener(null);
        btnAdd.setOnClickListener(null);
        btnBack.setOnClickListener(null);
    }

    @Override
    public void showFoodAttribute(Food food) {
        textViewProductName.setText(food.getName() + " (" + food.getOwner() + ")");
        editTextWeight.setText(String.valueOf(food.getWeight()));
        editTextCount.setText("1");
    }

    @Override
    public void showToast(String title) {
        Toast.makeText(getContext(), title, Toast.LENGTH_LONG).show();
    }

    private void onClickBtnAdd() {
        Food food = presenter.getCurrentFood();
        String weight = editTextWeight.getText().toString();
        String count = editTextCount.getText().toString();

        if (weight.isEmpty() || count.isEmpty()) {
            showToast("Заполните пустые поля");
        } else {
            Double aDouble = Double.valueOf(weight);
            Double aDouble1 = Double.valueOf(count);
            if (aDouble == 0 || aDouble1 == 0) {
                showToast("Заполните пустые поля");
            } else {
                food.setWeight(aDouble * aDouble1);
                presenter.setCurrentFood(food);
                presenter.addFoodToCalc();
                dismiss();
            }
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        presenter.onNext();
    }
}
