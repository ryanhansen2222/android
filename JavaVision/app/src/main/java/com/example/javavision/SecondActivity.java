package com.example.javavision;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    TTS tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button button3 = findViewById(R.id.done);
        button3.setOnClickListener(this);

        Button talkbutton = findViewById(R.id.talkButton);
        talkbutton.setOnClickListener(this);

        tts = tts.getInstance(this);
        tts.start();
    }

    @Override
    public void onClick(View v) {

        switch( v.getId()){
            case R.id.done:
                finish();
                break;
            case R.id.talkButton:
                Bundle b = new Bundle();
                b.putString("LM", "NERDS");
                if(tts.handler != null){
                    Message msg = tts.handler.obtainMessage(0);
                    msg.setData(b);
                    tts.handler.sendMessage(msg);

                }

                break;



        }


    }
}