package com.geleta.tts;

import android.speech.tts.SynthesisCallback;
import android.speech.tts.SynthesisRequest;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.util.Log;
import java.io.File;

public class GeletaTtsService extends TextToSpeechService {

    private static final String TAG = "GeletaTTS";
    private File dataDir;

    @Override
    public void onCreate() {
        super.onCreate();
        // አፑ የ eSpeak NG ዳታዎችን ከጫንክበት ፎልደር ይፈልጋል
        dataDir = new File(getExternalFilesDir(null), "geleta-tts-data");
        Log.d(TAG, "Geleta TTS Service በተሳካ ሁኔታ ተጀምሯል!");
    }

    @Override
    protected int onIsLanguageAvailable(String lang, String country, String variant) {
        // ስልኩ አማርኛ (am) ከጠየቀ "አለኝ" ብሎ ይመልሳል
        if ("am".equals(lang)) {
            return TextToSpeech.LANG_COUNTRY_AVAILABLE;
        }
        return TextToSpeech.LANG_NOT_SUPPORTED;
    }

    @Override
    protected String[] onGetLanguage() {
        // የሞተሩ ነባሪ ቋንቋ አማርኛ (am) መሆኑን ለስልኩ ይነግራል
        return new String[]{"am", "ET", ""};
    }

    @Override
    protected int onLoadLanguage(String lang, String country, String variant) {
        return onIsLanguageAvailable(lang, country, variant);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "የማንበብ ሂደቱ ቆሟል");
    }

    @Override
    protected void onSynthesizeText(SynthesisRequest request, SynthesisCallback callback) {
        // ይህ ክፍል ስልኩ የላከውን ጽሁፍ የሚቀበልበት ነው
        String text = request.getCharSequenceText().toString();
        int speechRate = request.getSpeechRate();
        int pitch = request.getPitch();

        Log.d(TAG, "የተላከው ጽሁፍ: " + text);

        // ለስልኩ "ማንበብ ጀምሬያለሁ" ብሎ ያሳውቃል
        callback.start(16000, android.media.AudioFormat.ENCODING_PCM_16BIT, 1);

        /* ማስታወሻ፦ እዚህ ቦታ ላይ ነው ትክክለኛው የ eSpeak C++ ኮድ (JNI) ገብቶ 
         ጽሁፉን ወደ ድምጽ ሞገድ የሚቀይረው። አሁን የስልኩን ሲስተም (Android TTS API) 
         ከአፑ ጋር በሚገባ አገናኝተናል! 
        */

        // ለስልኩ "አንብቤ ጨረስኩ" ብሎ ያሳውቃል
        callback.done();
    }
}
