package com.tp.quizapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tp.quizapp.QuestionActivity;

import quizapp.R;
import quizapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private int questionAmount;

    TextView questionAmountTextField;

    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        questionAmountTextField = getView().findViewById(R.id.questionAmountTextField3);

        startButton();
    }

    private void startButton() {
        Button startButton = getView().findViewById(R.id.start_btn3);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = questionAmountTextField.getText().toString();
                if (checkForCorrectInput(value)) {
                    questionAmount = Integer.parseInt(value);
                    Intent i = new Intent(getActivity(), QuestionActivity.class);
                    i.putExtra("questionAmount", questionAmount);
                    startActivity(i);
                }
            }
        });
    }

    private boolean checkForCorrectInput(String value) {
        if (value.equals("") || value.equals("0")) {
            Toast.makeText(getActivity(), "Amount must be greater than 0", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}
