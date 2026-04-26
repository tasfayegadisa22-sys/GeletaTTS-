package com.geleta.tts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // በገጹ ላይ ያሉትን መቆጣጠሪያዎች ማግኘት
        SeekBar seekSpeed = findViewById(R.id.seek_speed);
        SeekBar seekPitch = findViewById(R.id.seek_pitch);
        SeekBar seekVolume = findViewById(R.id.seek_volume);
        Button btnSave = findViewById(R.id.btn_save);

        // ሴቭ ማድረጊያ ቁልፍ ሲነካ የሚሰራ
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ለጊዜው ሴቭ ተደርጓል የሚል መልዕክት እንዲያሳይ
                Toast.makeText(MainActivity.this, "ማስተካከያዎቹ ሴቭ ተደርገዋል!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
