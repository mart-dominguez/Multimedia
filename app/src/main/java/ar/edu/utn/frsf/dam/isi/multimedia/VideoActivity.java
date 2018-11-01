package ar.edu.utn.frsf.dam.isi.multimedia;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    static final int REQUEST_VIDEO_CAPTURE = 1;

    Button btnVer;
    Button btnGrabar;
    VideoView videoView;
    Uri videoUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        btnGrabar = (Button) findViewById(R.id.btnGrabarVideo);
        btnVer = (Button) findViewById(R.id.btnVerVideo);
        videoView = (VideoView) findViewById(R.id.videoVer);
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }
            }
        });
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaController mc = new MediaController(VideoActivity.this);
                videoView.setMediaController(mc);
                videoView.setVideoURI(videoUri);
                /* videoView.setVideoPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) +"/movie.mp4");*/
                videoView.requestFocus();
                videoView.start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.d("APP_EJEMPLO","QAA____"+intent.getData().toString());
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            videoUri = intent.getData();

        }
    }




}
