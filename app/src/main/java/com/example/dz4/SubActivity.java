package com.example.dz4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {

    public static final String EDIT_TEXT_CONTENT_KEY = "editTextContent";

    private EditText editText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        editText = findViewById(R.id.changeNameEditText);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EDIT_TEXT_CONTENT_KEY)) {
            editText.setText(extras.getString(EDIT_TEXT_CONTENT_KEY));
        }
    }

    public void goToMainActivityOnClick(View view) {
        Intent data = new Intent();
        String message = editText.getText().toString();
        data.putExtra("RESULT_KEY", message);
        setResult(RESULT_OK, data);
        finish();
    }
}