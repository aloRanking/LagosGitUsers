package com.alo.lagosgitusers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class UserProfile extends AppCompatActivity implements Animation.AnimationListener{

    private TextView textView1;
    private TextView textView2;
    private ImageView imageView;
    private Button shareButton;

    private String userId;
    private String userUrl;
    private Context context;
    Animation animTopBottom;
    Animation animRightLeft;
    Animation animBounce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Bundle profile = getIntent().getBundleExtra("profile");
        if (profile== null){
            finish();
        }
        else{

            String userId = profile.getString("userId");
            final String userUrl = profile.getString("userProfileUrl");
            String imageUrl = profile.getString("profilePicture");

            animRightLeft = AnimationUtils.loadAnimation(this,
                    R.anim.right_left);


            textView1 = (TextView) findViewById(R.id.user_profile_name);
            textView1.setText(userId);
            animRightLeft.setAnimationListener(this);
            textView1.startAnimation(animRightLeft);

            textView2 = (TextView) findViewById(R.id.user_profile_url);
            textView2.setText(userUrl);
            textView2.startAnimation(animRightLeft);
            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse(userUrl));
                    startActivity(sendIntent);

                }
            });


            imageView = (ImageView) findViewById(R.id.user_profile_pic);

            Picasso.with(context)
                    .load(imageUrl)
                    .into(imageView);
            animTopBottom = AnimationUtils.loadAnimation(this,
                    R.anim.top_bot);
            animTopBottom.setAnimationListener(this);
            imageView.startAnimation(animTopBottom);


        }

        shareButton = (Button) findViewById(R.id.user_profile_button);
        animBounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        animBounce.setAnimationListener(this);
        shareButton.startAnimation(animBounce);


        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                String shareMssg = "Check out this awesome developer @" + userId + ", " + userUrl + ".";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMssg);
                shareIntent.setType("text/plain");
                startActivity(shareIntent);

            }
        });




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
