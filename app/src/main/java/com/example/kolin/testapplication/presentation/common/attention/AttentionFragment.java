package com.example.kolin.testapplication.presentation.common.attention;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.kolin.testapplication.R;

import static android.content.Context.MODE_PRIVATE;


public class AttentionFragment extends AppCompatDialogFragment {


    private CheckBox checkBox;
    private Button button;

    public AttentionFragment() {
    }


    public static AttentionFragment newInstance() {
        AttentionFragment fragment = new AttentionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attention, container, false);
        checkBox = (CheckBox) view.findViewById(R.id.attention_fragment_check_box);
        button = (Button) view.findViewById(R.id.attention_fragment_btn_ok);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    private void savePreferences(){
        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences(getString(R.string.preferences_attention_key), MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.preferences_attention_value), checkBox.isChecked());
        editor.apply();
    }

    @Override
    public void onStop() {
        savePreferences();
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        button.setOnClickListener(null);
        super.onDetach();
    }

}
