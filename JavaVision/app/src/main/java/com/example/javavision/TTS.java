package com.example.javavision;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

public class TTS extends Thread implements TextToSpeech.OnInitListener {

    public Handler handler;
    Context con;
    public static TTS t;
    TextToSpeech tts;
    String last = "poopmanniac27";

    public static TTS getInstance(Context c){
        if(t == null){
            t = new TTS(c);
        }else{
            t.con = c;
        }
        return t;
    }

    private TTS(Context con){
        this.con = con;
        tts = new TextToSpeech(con, this);
        tts.setLanguage(Locale.US);
        tts.speak("please work", TextToSpeech.QUEUE_ADD, null, null);
    }

    @Override
    public void run(){
        Looper.prepare();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                String aResponse = msg.getData().getString("LM");
                speakOut(aResponse);
            }
        };
        Looper.loop();

    }

    private void speakOut(String text) {

        Toast.makeText(con, text, Toast.LENGTH_LONG).show();

        Log.v("***SPEECH***", text);

        if(true) {
            last = text;
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            while (tts.isSpeaking()) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {

                }

            }
        }
    }



    public void onInit(int status) {
        //Toast.makeText(con, "TTS STATUS - "+ status + "/" + TextToSpeech.QUEUE_ADD, Toast.LENGTH_SHORT).show();

        if(status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);


            if(result == TextToSpeech.LANG_MISSING_DATA ||
            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(con, "Language is not supported", Toast.LENGTH_SHORT).show();
            }
        }

        else{
            Toast.makeText(con, "Init failed", Toast.LENGTH_SHORT).show();

        }




    }
}
