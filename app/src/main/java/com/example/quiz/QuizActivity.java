package com.example.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private Button buttonStart, buttonSubmit;
    private ToggleButton buttonAnswer;
    private TextView question, score, result;
    private int counterScore = 0, counterQuestion = 0;
    private String[] questionsArray, answersArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        questionsArray = getResources().getStringArray(R.array.questions);
        answersArray = getResources().getStringArray(R.array.answers);

        setContentView(R.layout.start_main);
        buttonStart = findViewById(R.id.startButton);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getQuiz();
            }
        });
    }

    public void getQuiz() {
        setContentView(R.layout.quiz_main);

        question = findViewById(R.id.questions);
        score = findViewById(R.id.scores);

        buttonSubmit = findViewById(R.id.submitButton);
        buttonAnswer = findViewById(R.id.toggleButton);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = buttonAnswer.getText().toString();
                if (answer.equals(answersArray[counterQuestion])) {
                    Toast.makeText(getApplicationContext(), "You are Right!", Toast.LENGTH_LONG).show();
                    counterScore++;
                    score.setText(String.format("%d/10", counterScore));
                } else {
                    Toast.makeText(getApplicationContext(), "You are Wrong!", Toast.LENGTH_LONG).show();
                }
                counterQuestion++;
                if (counterQuestion == questionsArray.length) {
                    getEnd();
                } else {
                    question.setText(questionsArray[counterQuestion]);
                }
            }
        });
    }

    public void getEnd() {
        setContentView(R.layout.result_main);
        result = findViewById(R.id.congratulations);
        result.setText(String.format(getString(R.string.congratulations_message) + "\n\nYour score is: %d/10", counterScore));
    }
}