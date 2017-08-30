package com.alo.lagosgitusers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

public class splash extends Activity implements Animation.AnimationListener {
    private static final int SPLASH_DURATION = 3;
    private ProgressBar pd;
    Animation animRotate;
    private TextView gitText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        gitText = (TextView)findViewById(R.id.textView2);

        //this animate the gitText
        animRotate = AnimationUtils.loadAnimation(this,R.anim.rotate_left);
        animRotate.setAnimationListener(this);
        gitText.startAnimation(animRotate);




        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                requestdata();

            }
        }, SPLASH_DURATION * 500);

    }

    private void requestdata() {
        startActivity(new Intent(splash.this, MainActivity.class));
        finish();
    }



    @Override
    public void onAnimationStart(Animation animation) {


    }

    @Override
    public void onAnimationEnd(Animation animation) {


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
