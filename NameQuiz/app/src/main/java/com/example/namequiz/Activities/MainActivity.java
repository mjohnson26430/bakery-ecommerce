package com.example.namequiz.Activities;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.namequiz.LeaderboardItem;
import com.example.namequiz.QuestionAnswers;
import com.example.namequiz.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    TextView allQuestionTextView;
    TextView questionTextView;
    TextView elapsedTimeTextView;

    Button ansA;
    Button ansB;
    Button ansC;
    Button ansD;
    Button submitButton;

    int score = 0;
    int allQuestions = QuestionAnswers.question.length;
    int currentQuestionIndex = 0;
    String chosenAnswer = "";
    Button lastClickedButton;

    private long startTime;
    private Handler handler = new Handler();
    private Runnable updateTimer;

    MediaPlayer[] mediaPlayerArray = new MediaPlayer[6];
    int currentTrackIndex = 0;

    private static final String VOLUME_PROGRESS_KEY = "volume_progress";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allQuestionTextView = findViewById(R.id.allquestions);
        questionTextView = findViewById(R.id.question);
        elapsedTimeTextView = findViewById(R.id.elapsedTimeTextView);
        ansA = findViewById(R.id.ans_a);
        ansB = findViewById(R.id.ans_b);
        ansC = findViewById(R.id.ans_c);
        ansD = findViewById(R.id.ans_d);
        submitButton = findViewById(R.id.submit);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        setUpMediaPlayers();
        setupTimer();

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        promptForName();
    }

    private void setUpMediaPlayers() {
        mediaPlayerArray[0] = MediaPlayer.create(this, R.raw.aloelite);
        mediaPlayerArray[1] = MediaPlayer.create(this, R.raw.downwiththeking);
        mediaPlayerArray[2] = MediaPlayer.create(this, R.raw.drifting);
        mediaPlayerArray[3] = MediaPlayer.create(this, R.raw.everythingeverything);
        mediaPlayerArray[4] = MediaPlayer.create(this, R.raw.outro);
        mediaPlayerArray[5] = MediaPlayer.create(this, R.raw.slingshot);


        int progress = sharedPreferences.getInt(VOLUME_PROGRESS_KEY, 50);
        float volume = (float) progress / 100;

        for (MediaPlayer mediaPlayer : mediaPlayerArray) {
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(volume, volume);
        }

        mediaPlayerArray[currentTrackIndex].start();
    }

    private void setupTimer() {
        updateTimer = new Runnable() {
            @Override
            public void run() {
                long elapsedTime = System.currentTimeMillis() - startTime;
                long elapsedSeconds = elapsedTime / 1000;
                long secondsDisplay = elapsedSeconds % 60;
                long elapsedMinutes = elapsedSeconds / 60;
                elapsedTimeTextView.setText(String.format(Locale.getDefault(), "%02d:%02d", elapsedMinutes, secondsDisplay));
                handler.postDelayed(this, 1000);
            }
        };
    }

    private void startTimer() {
        startTime = System.currentTimeMillis();
        handler.postDelayed(updateTimer, 1000);
    }

    private void stopTimer() {
        handler.removeCallbacks(updateTimer);
    }

    private void promptForName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        RelativeLayout customLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.dialog_layout, null);
        builder.setView(customLayout);

        EditText input = customLayout.findViewById(R.id.dialog_input);

        builder.setPositiveButton("OK", (dialogInterface, which) -> {
            String name = input.getText().toString();
            if (!name.isEmpty()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("player_name", name);
                editor.apply();
                startTimer();
                loadNewQuestion();
            }
        });

        builder.setNegativeButton("Cancel", (dialogInterface, which) -> dialogInterface.cancel());

        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(dialogInterface -> {
            dialog.getWindow().setLayout(800, 1000);
        });

        dialog.show();
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit) {
            if (!chosenAnswer.isEmpty()) {
                checkAnswer();
            } else {
                showAlertDialog("Alert", "Please select an answer before submitting.");
            }
        } else {
            updateAnswerButtons(clickedButton);
        }
    }

    private void checkAnswer() {
        if (chosenAnswer.equals(QuestionAnswers.correctAnswers[currentQuestionIndex])) {
            score++;
            showDynamicText("Correct!");
        } else {
            showDynamicText("Incorrect!");
        }
        currentQuestionIndex++;
        if (currentQuestionIndex < allQuestions) {
            loadNewQuestion();
        } else {
            stopTimer();
            finishQuiz();
        }
    }

    private void updateAnswerButtons(Button clickedButton) {
        ansA.setTextColor(Color.WHITE);
        ansB.setTextColor(Color.WHITE);
        ansC.setTextColor(Color.WHITE);
        ansD.setTextColor(Color.WHITE);

        clickedButton.setTextColor(Color.parseColor("#2596be"));
        chosenAnswer = clickedButton.getText().toString();
        lastClickedButton = clickedButton;
    }

    void loadNewQuestion() {
        if (currentQuestionIndex < allQuestions) {
            resetAnswerButtonsColor();

            questionTextView.setText(QuestionAnswers.question[currentQuestionIndex]);
            ansA.setText(QuestionAnswers.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswers.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswers.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswers.choices[currentQuestionIndex][3]);

            allQuestionTextView.setText("Question " + (currentQuestionIndex + 1) + " of " + allQuestions);
        } else {
            finishQuiz();
        }
    }

    private void resetAnswerButtonsColor() {
        ansA.setTextColor(Color.WHITE);
        ansB.setTextColor(Color.WHITE);
        ansC.setTextColor(Color.WHITE);
        ansD.setTextColor(Color.WHITE);
    }

    void finishQuiz() {
        String playerName = sharedPreferences.getString("player_name", "Anonymous");
        uploadScoreToFirebase(playerName, score);

        String resultMessage = calculateResultMessage();
        showAlertDialog(resultMessage, "Your score is " + score + " out of " + allQuestions);
    }

    private String calculateResultMessage() {
        if (score == allQuestions) {
            return "You’re lowkey a genius\n\n";
        } else if (score > allQuestions * 0.60) {
            return "You did great!\n";
        } else {
            return "You can always try again\n";
        }
    }

    private void uploadScoreToFirebase(String playerName, int score) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("leaderboard").child(playerName).setValue(new LeaderboardItem(playerName, score));
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .show();
    }

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        startTimer();
        loadNewQuestion();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
        handler.removeCallbacks(updateTimer);
        for (MediaPlayer mediaPlayer : mediaPlayerArray) {
            mediaPlayer.release();
        }
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(VOLUME_PROGRESS_KEY)) {
            updateMediaVolume(sharedPreferences);
        }
    }

    private void updateMediaVolume(SharedPreferences sharedPreferences) {
        int progress = sharedPreferences.getInt(VOLUME_PROGRESS_KEY, 50);
        float volume = (float) progress / 100;
        for (MediaPlayer mediaPlayer : mediaPlayerArray) {
            mediaPlayer.setVolume(volume, volume);
        }
    }

    private void showDynamicText(String text) {
        TextView dynamicTextView = new TextView(this);
        dynamicTextView.setText(text);
        dynamicTextView.setTextColor(Color.WHITE);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        dynamicTextView.setLayoutParams(layoutParams);

        LinearLayout choicesLayout = findViewById(R.id.choicesLayout);
        choicesLayout.addView(dynamicTextView);

        new Handler().postDelayed(() -> choicesLayout.removeView(dynamicTextView), 1600);
    }
}
