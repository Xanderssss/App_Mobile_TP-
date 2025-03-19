package com.example.megaquizz.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.text.TextWatcher;
import android.text.Editable;

import com.example.megaquizz.R;
import com.example.megaquizz.ui.QuizzFragment;

public class WelcomeFragment extends Fragment { // ✔ NOM DU FICHIER = NOM DE LA CLASSE

    private EditText userNameInput;
    private Button playButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        userNameInput = view.findViewById(R.id.userName);
        playButton = view.findViewById(R.id.playbutton);

        playButton.setEnabled(false);
        playButton.setAlpha(0.5f);

        userNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                boolean isEmpty = s.toString().trim().isEmpty();
                playButton.setEnabled(!isEmpty);
                playButton.setAlpha(isEmpty ? 0.5f : 1.0f);
            }
        });

        playButton.setOnClickListener(v -> {
            String pseudo = userNameInput.getText().toString().trim();
            if (pseudo.isEmpty()) {
                userNameInput.setError("Veuillez entrer un pseudo");
                return;
            }

            // Transition vers QuizzFragment
            QuizzFragment quizzFragment = new QuizzFragment();
            Bundle args = new Bundle();
            args.putString("USER_PSEUDO", pseudo);
            quizzFragment.setArguments(args);

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, quizzFragment);
            transaction.commit();
        });

        return view;
    }
}
