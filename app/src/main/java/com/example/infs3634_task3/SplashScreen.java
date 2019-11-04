package com.example.infs3634_task3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.widget.ProgressBar;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;

public class SplashScreen extends AppCompatActivity {
    private GifImageView gif;
    private ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_home);

        gif = findViewById(R.id.gif);
        progress_bar = findViewById(R.id.progress_bar);
        progress_bar.setVisibility(progress_bar.VISIBLE);

        try {
            InputStream inputStream = getAssets().open("pusheen.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gif.setBytes(bytes);
            gif.startAnimation();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //wait 5 seconds and start MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this, MainActivity.class));
                SplashScreen.this.finish();
            }
        }, 5000); //5000 = 5 seconds
    }
}