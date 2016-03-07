package com.example.young_jin.supportproject.activities;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.young_jin.supportproject.R;

public class SomethingMoreActivity extends AppCompatActivity {

    private ObjectAnimator rotation;
    private long mCurrentPlayTime;
    private ObjectAnimator rotation2;

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.slide_out_left);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_something_more);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getSupportActionBar().getTitle());
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final RelativeLayout mainButton = (RelativeLayout) findViewById(R.id.main_button);
        LinearLayout mainIcon = (LinearLayout) findViewById(R.id.main_icon);
        TextView mainText = (TextView) findViewById(R.id.main_text);

        ObjectAnimator mainIconAnimation = ObjectAnimator.ofFloat(mainIcon, "translationY", 0, 25f);
        mainIconAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mainIconAnimation.setDuration(700);

        ObjectAnimator mainTextAnimation = ObjectAnimator.ofFloat(mainText, View.ALPHA, 1f, 0);
        mainTextAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mainTextAnimation.setDuration(700);


//        ProgressBar horizontalProgressBar = (ProgressBar) findViewById(R.id.horizontal_progressBar);
//        ObjectAnimator horizontalAnimation = ObjectAnimator.ofInt(horizontalProgressBar, "progress", 0, 1000);
//        horizontalAnimation.setRepeatCount(ObjectAnimator.INFINITE);
//        horizontalAnimation.setRepeatMode(ObjectAnimator.REVERSE);
//        horizontalAnimation.setDuration(2000);
//        horizontalAnimation.start();

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

        ImageView button = (ImageView) findViewById(R.id.animation_change);

        rotation = ObjectAnimator.ofFloat(progressBar, "rotation", 0, 360);
//
//        final RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
//                0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
        rotation.setDuration(1000);
        rotation.setRepeatCount(Animation.INFINITE);
        rotation.setRepeatMode(ObjectAnimator.RESTART);
        rotation.setInterpolator(new LinearInterpolator());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotation.start();
            }
        });

        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 1000);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(1500);
        animation.start();

//        progressBar.startAnimation(rotate);

        ObjectAnimator animationReversed = ObjectAnimator.ofInt(progressBar2, "progress", 0, 1000);
        animationReversed.setInterpolator(new AccelerateDecelerateInterpolator());
        animationReversed.setDuration(700);

        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(mainButton, "scaleX", 0.95f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(mainButton, "scaleY", 0.95f);
        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(mainButton, "scaleX", 1.05f);
        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(mainButton, "scaleY", 1.05f);
        scaleDownX.setDuration(100);
        scaleDownY.setDuration(100);
        scaleUpX.setDuration(100);
        scaleUpY.setDuration(100);

        final AnimatorSet scaleUp = new AnimatorSet();
        scaleUp.play(scaleUpX).with(scaleUpY);

        final AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);

        final AnimatorSet iconAnimation = new AnimatorSet();
        iconAnimation.play(animationReversed).with(mainIconAnimation).with(mainTextAnimation);

        final AnimatorSet mainAnimation = new AnimatorSet();
        mainAnimation.play(scaleDown).before(scaleUp).before(iconAnimation);

        iconAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                progressBar2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mainAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mainButton.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainAnimation.start();

            }
        });

        final LinearLayout test = (LinearLayout) findViewById(R.id.test);

        rotation2 = ObjectAnimator.ofFloat(progressBar, "rotation", 0, 360);
        rotation2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                test.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                test.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rotation.isRunning()){
                    rotation.cancel();

                    Float save = (Float) rotation.getAnimatedValue();
                    Log.d("TAG", "Value" + save);
                    rotation2 = ObjectAnimator.ofFloat(progressBar, "rotation", save, 360);
                    rotation2.setDuration((long)(1000*(1-save/360)));
                    rotation2.setRepeatCount(0);
                    rotation2.setInterpolator(new LinearInterpolator());
                    rotation2.start();

                } else {

                    rotation.start();
                }

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return true;
    }
}
