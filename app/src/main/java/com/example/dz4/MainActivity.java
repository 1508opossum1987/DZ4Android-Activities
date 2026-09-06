package com.example.dz4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private final String EDIT_TEXT_CONTENT_KEY = "editTextContent";

    private EditText editText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.enterNameEditText);

        String editTextContent = null;
        if (savedInstanceState != null && savedInstanceState.containsKey(EDIT_TEXT_CONTENT_KEY)) {
            editTextContent = savedInstanceState.getString(EDIT_TEXT_CONTENT_KEY);
        }
        editText.setText(editTextContent);
    }

    public void goToSubActivityOnClick(View view) {
        Intent intent = new Intent(this, SubActivity.class);
        intent.putExtra(SubActivity.EDIT_TEXT_CONTENT_KEY, editText.getText().toString());
        activityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            int code = result.getResultCode();

                            if (result.getData() == null) {
                                throw new NullPointerException("Данные отсутствуют!");
                            }
                            String data = result.getData().getStringExtra("RESULT_KEY");
                            editText.setText(data);
                            Toast.makeText(getBaseContext(), "code: " + code + ", data: " + data, Toast.LENGTH_LONG).show();
                        }
                    });
}