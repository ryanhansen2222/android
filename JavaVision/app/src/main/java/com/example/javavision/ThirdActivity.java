package com.example.javavision;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;

import java.io.InputStream;
import java.util.List;

public class ThirdActivity extends AppCompatActivity implements ImageAnalysis.Analyzer, View.OnClickListener{

    TextView tv;
    List<Face> fac;
    TTS tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);



        tv = findViewById(R.id.textView2);
        tts = tts.getInstance(this);
        tts.start();
        
        
        //Init image
        InputStream stream = getResources().openRawResource(R.raw.faces);
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        FaceView overlay = (FaceView) findViewById(R.id.faceView);
        List<Face> initImage = null;
        overlay.setContent(bitmap, initImage);




    }

    public void findFaces(){


        InputStream stream = getResources().openRawResource(R.raw.faces);
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        InputImage image = InputImage.fromBitmap(bitmap, 0);

        FaceDetector detector = FaceDetection.getClient();

        Task<List<Face>> result =
                detector.process(image).addOnSuccessListener(
                        new OnSuccessListener<List<Face>>() {
                            @Override
                            public void onSuccess(@NonNull List<Face> faces) {

                                fac = faces;
                                if(fac == null)
                                    Log.v("draw", "main2 FAXW is null");
                                FaceView overlay = (FaceView) findViewById(R.id.faceView);
                                overlay.setContent(bitmap, fac);

                                tv.setText(faces.size() + " Faces Seen");


                                Bundle b = new Bundle();
                                b.putString("LM", "Found " + faces.size() + " faces");
                                if(tts.handler != null){
                                    Message msg = tts.handler.obtainMessage(0);
                                    msg.setData(b);
                                    tts.handler.sendMessage(msg);

                                }


                            }


                        }

                ).addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        }

                );
    }

    @Override
    public void analyze(@NonNull ImageProxy image) {

    }

    @Override
    public void onClick(View v) {
        switch( v.getId()){
            case R.id.button:

                findFaces();


                break;



        }

    }
}