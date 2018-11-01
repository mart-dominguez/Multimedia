package ar.edu.utn.frsf.dam.isi.multimedia;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class GrabarReproducirActivity extends AppCompatActivity {

    private Button btnGrabar;
    private Button btnReproducir;
    private static final String LOG_TAG = "AudioRecordTest";
    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    private String mFileName;
    private Boolean grabando = false;
    private Boolean reproduciendo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grabar_reproducir);
        mFileName = Environment.getExternalStorageDirectory()
                .getAbsolutePath()+"/audiorecordtest.3gp";
        btnGrabar = (Button) findViewById(R.id.btnGrabarAudio);
        btnReproducir = (Button) findViewById(R.id.btnReproducir);
        btnGrabar.setOnClickListener(listenerPlayer);
        btnReproducir.setOnClickListener(listenerPlayer);
    }

    View.OnClickListener listenerPlayer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnReproducir:
                    if(reproduciendo){
                        ((Button) view).setText("Reproducir");
                        reproduciendo=false;
                        terminarReproducir();
                    }else{
                        ((Button) view).setText("pausar.....");
                        reproduciendo=true;
                        reproducir();
                    }
                    break;
                case R.id.btnGrabarAudio:
                    if(grabando){
                        ((Button) view).setText("Grabar");
                        grabando=false;
                        terminarGrabar();
                    }else{
                        ((Button) view).setText("grabando.....");
                        grabando=true;
                        grabar();
// en realidad pedir permiso!!!
                    }
                    break;
            }
        }
    };

    private void grabar() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        mRecorder.start();
    }
    private void terminarGrabar() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    private void reproducir() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void terminarReproducir() {
        mPlayer.release();
        mPlayer = null;
    }


}
