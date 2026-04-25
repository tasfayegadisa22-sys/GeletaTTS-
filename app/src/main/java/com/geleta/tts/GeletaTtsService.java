package com.geleta.tts;

import android.speech.tts.TextToSpeechService;
import android.speech.tts.SynthesisRequest;
import android.speech.tts.SynthesisCallback;
import android.speech.tts.TextToSpeech;
import android.media.AudioFormat;

public class GeletaTtsService extends TextToSpeechService {

    @Override
    protected int onIsLanguageAvailable(String lang, String country, String variant) {
        return TextToSpeech.LANG_AVAILABLE;
    }

    @Override
    protected String[] onGetLanguage() {
        // ለአማርኛ ቋንቋ
        return new String[]{"amh", "ETH", ""};
    }

    @Override
    protected int onLoadLanguage(String lang, String country, String variant) {
        return TextToSpeech.LANG_AVAILABLE;
    }

    @Override
    protected void onStop() {
        // ድምፅ ማቆሚያ ትዕዛዝ
    }

    @Override
    protected void onSynthesizeText(SynthesisRequest request, SynthesisCallback callback) {
        // ይህ ለጊዜው አፑ ያለምንም ስህተት እንዲገነባ (Compile እንዲያደርግ) የተዘጋጀ መሰረታዊ ኮድ ነው
        callback.start(16000, AudioFormat.ENCODING_PCM_16BIT, 1);
        callback.done();
    }
}    
