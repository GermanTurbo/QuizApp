package com.tp.quizapp.ui.stats;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import quizapp.R;
import quizapp.databinding.FragmentStatsInformationBinding;

public class StatsFragment extends Fragment {

    private FragmentStatsInformationBinding binding;

    private TextView pointsDisplay;

    private int latestPoints;



    View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStatsInformationBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pointsDisplay = root.findViewById(R.id.points_display);
        try {
            setPoints();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setPoints() throws FileNotFoundException {
        SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("LATEST_POINTS", 0);
        latestPoints = settings.getInt("homeScore", 0);

        pointsDisplay.setText("Latest Points: " + latestPoints);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
