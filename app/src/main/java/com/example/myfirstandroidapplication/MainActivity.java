package com.example.myfirstandroidapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText passwordInput, messageInput;
    private Button encryptButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordInput = findViewById(R.id.passwordInput);
        messageInput = findViewById(R.id.messageInput);
        encryptButton = findViewById(R.id.encryptButton);
        resultText = findViewById(R.id.resultText);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryptMessage();
            }
        });
    }

    private void encryptMessage() {
        String text = messageInput.getText().toString();
        int shift = 3; // Define the Caesar cipher shift value (e.g., 3 for a simple shift of 3 positions)

        String encryptedText = caesarCipherEncrypt(text, shift);
        resultText.setText("Encrypted Text: " + encryptedText);
    }

    private String caesarCipherEncrypt(String text, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) (base + (c - base + shift) % 26);
            }
            encryptedText.append(c);
        }
        return encryptedText.toString();
    }
}

