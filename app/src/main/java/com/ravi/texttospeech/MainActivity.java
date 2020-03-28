package com.ravi.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import static java.util.Locale.getISOCountries;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToSpeech = new TextToSpeech(this,this);

        button = findViewById(R.id.submitbtn);
        editText = findViewById(R.id.textinput);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voiceOutput();
            }
        });
    }

    private void voiceOutput(){
        CharSequence text = editText.getText();
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,"id");
    }
    @Override
    public void onInit(int status){
        if(status == TextToSpeech.SUCCESS){
            int result = textToSpeech.setLanguage(Locale.UK);

            if(result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(MainActivity.this,"Language Not Supported", Toast.LENGTH_SHORT).show();
            }else {
                button.setEnabled(true);
                voiceOutput();
            }
        }
        else{
            Toast.makeText(MainActivity.this, "Initialization Failed", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public  void onDestroy(){
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
