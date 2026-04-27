package com.geleta.tts;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;
    private EditText editText;
    private Button buttonSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. የድምፅ ፋይሎቹን ከአሴትስ ወደ ስልኩ ዳታ ፎልደር መገልበጥ
        copyAssetsToStorage();

        // 2. የኢንተርፌስ ዕቃዎችን ማገናኘት
        editText = findViewById(R.id.editText);
        buttonSpeak = findViewById(R.id.buttonSpeak);

        // 3. TTS ማዘጋጀት
        tts = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(Locale.US);
            } else {
                Toast.makeText(this, "TTS ማዘጋጀት አልተቻለም!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonSpeak.setOnClickListener(v -> {
            String text = editText.getText().toString();
            if (!text.isEmpty()) {
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    private void copyAssetsToStorage() {
        // ፋይሎቹ የሚቀመጡበት ቦታ
        File dataDir = new File(getExternalFilesDir(null), "geleta-tts-data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        try {
            // በ 'assets/geleta-tts-data' ውስጥ ያሉትን ፋይሎች ዝርዝር ማግኘት
            String[] files = getAssets().list("geleta-tts-data");
            if (files != null) {
                for (String filename : files) {
                    File outFile = new File(dataDir, filename);
                    if (!outFile.exists()) { // ፋይሉ ቀድሞ ከሌለ ብቻ ይቅዳ
                        try (InputStream in = getAssets().open("geleta-tts-data/" + filename);
                             OutputStream out = new FileOutputStream(outFile)) {
                            byte[] buffer = new byte[1024];
                            int read;
                            while ((read = in.read(buffer)) != -1) {
                                out.write(buffer, 0, read);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
