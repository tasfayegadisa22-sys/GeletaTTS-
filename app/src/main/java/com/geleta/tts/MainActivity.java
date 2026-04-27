package com.geleta.tts;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ዳታ ፋይሎቹን ወደ ስልኩ ሚሞሪ መገልበጥ
        copyAssetsToStorage();
    }

    private void copyAssetsToStorage() {
        File dataDir = new File(getExternalFilesDir(null), "geleta-tts-data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        try {
            String[] files = getAssets().list("geleta-tts-data");
            if (files != null) {
                for (String filename : files) {
                    try (InputStream in = getAssets().open("geleta-tts-data/" + filename);
                         OutputStream out = new FileOutputStream(new File(dataDir, filename))) {
                        
                        byte[] buffer = new byte[1024];
                        int read;
                        while ((read = in.read(buffer)) != -1) {
                            out.write(buffer, 0, read);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
