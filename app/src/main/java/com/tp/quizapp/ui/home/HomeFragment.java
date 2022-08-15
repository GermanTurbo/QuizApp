package com.tp.quizapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.tp.quizapp.QuestionActivity;

import quizapp.R;
import quizapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private int questionAmount = 0;
    private String questionCategory;

    private FragmentHomeBinding binding;
    private View root;

    private MaterialButtonToggleGroup chooseCategoryToggleGroup;
    private MaterialButtonToggleGroup chooseQuestionAmountToggleGroup;
    private ProgressBar startButtonSpinner;

    private boolean amountButtonSelected;
    private boolean categoryButtonSelected;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chooseCategoryToggleGroup = root.findViewById(R.id.category_button_group);
        chooseQuestionAmountToggleGroup = root.findViewById(R.id.question_amount_button_group);

        startButtonSpinner = root.findViewById(R.id.spinner_start_button);
        startButtonSpinner.setVisibility(View.GONE);


        questionAmount();
        questionCategory();

    }

    private void questionAmount() {
        chooseQuestionAmountToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    amountButtonSelected = true;
                    if (checkedId == R.id.question_amount_button_group_button1) {
                        questionAmount = 5;

                    } else if (checkedId == R.id.question_amount_button_group_button2) {
                        questionAmount = 10;

                    } else if (checkedId == R.id.question_amount_button_group_button3) {
                        questionAmount = 20;

                    }
                }
            }
        });
    }

    private void questionCategory() {
        chooseCategoryToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    categoryButtonSelected = true;
                    if (checkedId == R.id.category_button_group_button1) {
                        questionCategory = "Geography";

                    } else if (checkedId == R.id.category_button_group_button2) {
                        questionCategory = "Animals";

                    } else if (checkedId == R.id.category_button_group_button3) {
                        questionCategory = "Entertainment: Video Games";

                    }
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        startButton();
    }

    private void startButton() {
        Button startButton = getView().findViewById(R.id.start_btn3);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSpinnerActive(true);
                if ((amountButtonSelected && categoryButtonSelected)) {
                    Intent i = new Intent(getActivity(), QuestionActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("questionAmount", questionAmount);
                    bundle.putString("questionCategory", questionCategory);
                    i.putExtras(bundle);
                    startActivity(i);

                } else {
                    setSpinnerActive(false);
                    Toast.makeText(getActivity(), "Select category and amount", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setSpinnerActive(boolean active) {
        if (active) {
            startButtonSpinner.setVisibility(View.VISIBLE);
        } else {
            startButtonSpinner.setVisibility(View.GONE);
        }
    }
}
