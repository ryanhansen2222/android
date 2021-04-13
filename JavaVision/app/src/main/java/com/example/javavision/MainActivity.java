package com.example.javavision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button toastButton = findViewById(R.id.toastbutton);
        toastButton.setOnClickListener(this);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toastbutton:
                Toast.makeText(this, "TOAST", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                //Toast.makeText(this, "Button 2", Toast.LENGTH_SHORT).show();
                startSecondActivity();
                break;
            case R.id.button3:
                Toast.makeText(this, "Button 3", Toast.LENGTH_SHORT).show();
                startThirdActivity();
                break;
        }

    }

    public void startSecondActivity(){
        Intent second = new Intent( this, SecondActivity.class);
        startActivity(second);
    }

    public void startThirdActivity(){
        Intent second = new Intent( this, ThirdActivity.class);
        startActivity(second);

    }

}