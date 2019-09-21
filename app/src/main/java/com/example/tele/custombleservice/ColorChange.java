package com.example.tele.custombleservice;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 98469 on 2019-09-20.
 */

public class ColorChange extends AppCompatActivity {

    CircleView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_color);

        view = findViewById(R.id.custom_circle);
        ObjectAnimator anim = ObjectAnimator.ofObject(view, "color", new ColorEvaluator(),
                "#0000FF", "#FF0000");
        anim.setDuration(8000);
        anim.start();
    }
}
