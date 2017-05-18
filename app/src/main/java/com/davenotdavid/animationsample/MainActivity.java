package com.davenotdavid.animationsample;

import android.animation.Animator;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout activityMain, fabContent;

    private FloatingActionButton mRunCircRevFab;

    private boolean mIsClosed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMain = (RelativeLayout) findViewById(R.id.activity_main);
        fabContent = (RelativeLayout) findViewById(R.id.fab_content);

        mRunCircRevFab = (FloatingActionButton) findViewById(R.id.run_circ_reveal);
    }

    public void onRunCircRevFabClicked(View v) {
        int x = 150;
        int y = 150;

        if (mIsClosed) {
            int startRadius = 0;
            int endRadius = (int) Math.hypot(activityMain.getWidth(), activityMain.getHeight());

            mRunCircRevFab.setBackgroundTintList(
                    ColorStateList.valueOf(
                            ResourcesCompat.getColor(getResources(),
                            R.color.white_pressed, null)));
            mRunCircRevFab.setImageResource(R.drawable.ic_close_black);

            Animator anim = ViewAnimationUtils.createCircularReveal(
                    fabContent,
                    x,
                    y,
                    startRadius,
                    endRadius);

            fabContent.setVisibility(View.VISIBLE);

            anim.start();

            mIsClosed = false;
        } else {
            int startRadius = Math.max(fabContent.getWidth(), fabContent.getHeight());
            int endRadius = 0;

            mRunCircRevFab.setBackgroundTintList(
                    ColorStateList.valueOf(
                            ResourcesCompat.getColor(getResources(),
                            R.color.colorPrimary, null)));
            mRunCircRevFab.setImageResource(R.drawable.ic_run_white);

            Animator anim = ViewAnimationUtils.createCircularReveal(
                    fabContent,
                    x,
                    y,
                    startRadius,
                    endRadius);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {}

                @Override
                public void onAnimationEnd(Animator animator) {
                    fabContent.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {}

                @Override
                public void onAnimationRepeat(Animator animator) {}
            });
            anim.start();

            mIsClosed = true;
        }
    }

    public void onActionAFabClicked(View v) {
        Toast.makeText(getApplicationContext(), "Refresh FAB clicked", Toast.LENGTH_SHORT).show();
    }

    public void onActionBFabClicked(View v) {
        Toast.makeText(getApplicationContext(), "Settings FAB clicked", Toast.LENGTH_SHORT).show();
    }
}