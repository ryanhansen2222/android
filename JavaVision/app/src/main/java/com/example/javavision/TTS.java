package com.example.javavision;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

public class TTS extends Thread implements TextToSpeech.OnInitListener {

    public Handler handler;
    Context con;
    TTS t;
    TextToSpeech tts;

    public static TTS getInstance(Context c){
        if(t == null){
            t = new TTS(c);
        }else{
            t.context = c;
        }
        return t;
    }

    private TTS(Context con){
        this.con = con;
        tts = new TextToSpeech(con, this);
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

    private void speakOut(String aResponse) {

        Toast.makeText(con, "Text 2 Speech" + aResponse, Toast.LENGTH_LONG).show();


        if(last != text) {
            last = text;
            this.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            while (tts.isSpeaking()) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {

                }

            }
        }
    }


    @Override
    public void onInit(int status) {
        Log.v("***SPEECH***", text);




    }
}
