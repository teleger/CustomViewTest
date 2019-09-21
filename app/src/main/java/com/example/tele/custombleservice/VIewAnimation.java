package com.example.tele.custombleservice;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * 关于动画 ， 重新定义 activity  补间动画 (视图动画)
 * Created by leger on 2019-09-19.
 */

public class VIewAnimation extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //重新写 xml 布局文件，文件名为...
        setContentView(R.layout.viewlayout);

        final Button bt = findViewById(R.id.bt_animation);

        //关于bt 这个按钮 的 平移动画，是用xml 文件描述的...
        final Animation translateAnimation = AnimationUtils.loadAnimation(this,R.anim.view_set);

        //java code setting ---
        ObjectAnimator translation = ObjectAnimator.ofFloat(bt,"translationX",0f,360f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(bt,"rotation",0f,360f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(bt,"alpha",1f,0f,1f);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translation).with(rotate).before(alpha);
        animatorSet.setDuration(5000);
        //java code end ---

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bt.startAnimation(translateAnimation);//开始这个动画...
                animatorSet.start();
            }
        });

    }
}
