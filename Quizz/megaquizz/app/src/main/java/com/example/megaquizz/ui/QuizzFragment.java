package com.example.megaquizz.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.megaquizz.Question.Question;
import com.example.megaquizz.R;
import com.example.megaquizz.model.ResultsFragment;

import java.util.ArrayList;
import java.util.List;

public class QuizzFragment extends Fragment {
    private TextView questionText;
    private Button answer1, answer2, answer3, answer4;
    private int currentQuestionIndex = 0;
    private List<Question> questions;
    private List<UserAnswer> userAnswers = new ArrayList<>();
    private final Handler handler = new Handler(); // Handler pour le délai avant la prochaine question

    public QuizzFragment() {
        // Constructeur vide obligatoire
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quizzfragment, container, false);

        questionText = view.findViewById(R.id.questionText);
        answer1 = view.findViewById(R.id.answer1);
        answer2 = view.findViewById(R.id.answer2);
        answer3 = view.findViewById(R.id.answer3);
        answer4 = view.findViewById(R.id.answer4);

        // Initialiser les questions
        initializeQuestions();
        loadQuestion();

        return view;
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("Quelle est la capitale de la France ?", "Paris", "Londres", "Berlin", "Madrid", "Paris"));
        questions.add(new Question("Combien de continents y a-t-il sur Terre ?", "4", "5", "6", "7", "7"));
        questions.add(new Question("Quel est le plus grand océan du monde ?", "Atlantique", "Indien", "Arctique", "Pacifique", "Pacifique"));
        questions.add(new Question("Quelle planète est la plus proche du Soleil ?", "Mars", "Vénus", "Mercure", "Jupiter", "Mercure"));
        questions.add(new Question("Qui a peint la Joconde ?", "Van Gogh", "Léonard de Vinci", "Monet", "Picasso", "Léonard de Vinci"));
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            questionText.setText(question.getQuestion());
            answer1.setText(question.getOption1());
            answer2.setText(question.getOption2());
            answer3.setText(question.getOption3());
            answer4.setText(question.getOption4());

            resetButtonColors(); // Réinitialiser les couleurs des boutons

            setAnswerClickListener(answer1, question);
            setAnswerClickListener(answer2, question);
            setAnswerClickListener(answer3, question);
            setAnswerClickListener(answer4, question);
        } else {
            showResults();
        }
    }

    private void setAnswerClickListener(Button button, Question question) {
        button.setOnClickListener(v -> {
            boolean isCorrect = button.getText().toString().equals(question.getCorrectAnswer());

            // Ajouter la réponse dans la liste
            userAnswers.add(new UserAnswer(question.getQuestion(), button.getText().toString(), isCorrect));

            // Changer la couleur du bouton sélectionné
            if (isCorrect) {
                button.setBackgroundColor(Color.GREEN);
                Toast.makeText(getActivity(), "✅ Bonne réponse !", Toast.LENGTH_SHORT).show();
            } else {
                button.setBackgroundColor(Color.RED);
                Toast.makeText(getActivity(), "❌ Mauvaise réponse !", Toast.LENGTH_SHORT).show();
            }

            // Attendre 1,5 seconde avant de charger la prochaine question
            handler.postDelayed(() -> {
                currentQuestionIndex++;
                loadQuestion();
            }, 1500); // 1500 millisecondes (1.5 secondes)
        });
    }

    private void resetButtonColors() {
        int defaultColor = Color.parseColor("#3A6EA5"); // Bleu par défaut
        answer1.setBackgroundColor(defaultColor);
        answer2.setBackgroundColor(defaultColor);
        answer3.setBackgroundColor(defaultColor);
        answer4.setBackgroundColor(defaultColor);
    }


    private void showResults() {
        ResultsFragment resultsFragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putSerializable("USER_ANSWERS", new ArrayList<>(userAnswers));
        resultsFragment.setArguments(args);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, resultsFragment);
        transaction.commit();
    }
}
