package com.example.megaquizz.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.megaquizz.R;
import com.example.megaquizz.ui.UserAnswer;

import java.util.ArrayList;

public class ResultsFragment extends Fragment {
    private ArrayList<UserAnswer> userAnswers;

    public ResultsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        TextView resultsTextView = view.findViewById(R.id.resultsTextView);
        Button replayButton = view.findViewById(R.id.replayButton); // Ajout du bouton

        if (getArguments() != null) {
            userAnswers = (ArrayList<UserAnswer>) getArguments().getSerializable("USER_ANSWERS");
            StringBuilder results = new StringBuilder();
            for (UserAnswer answer : userAnswers) {
                results.append(answer.getQuestion())
                        .append("\nVotre réponse: ")
                        .append(answer.getUserAnswer())
                        .append(" - ")
                        .append(answer.isCorrect() ? "Correct" : "Faux")
                        .append("\n\n");
            }
            resultsTextView.setText(results.toString());
        }

        // Action du bouton "Rejouer"
        replayButton.setOnClickListener(v -> {
            WelcomeFragment welcomeFragment = new WelcomeFragment();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, welcomeFragment);
            transaction.commit();
        });

        return view;
    }
}

