package com.samuelbernard.starbhakattendance.helper;

import android.animation.LayoutTransition;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.samuelbernard.starbhakattendance.R;

public class AnimationHelper {
    public static final int DURATION_SHORT = 250;
    public static final int DURATION_MED = 500;
    public static final int DURATION_LONG = 750;
    public static final int DURATION_X_LONG = 1000;

    // Enable animation to layout
    public static void addLayoutAnimation(ViewGroup view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            view.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGE_APPEARING);
//            view.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
//            view.getLayoutTransition().enableTransitionType(LayoutTransition.APPEARING);
//            view.getLayoutTransition().enableTransitionType(LayoutTransition.DISAPPEARING);
            view.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        }
    }

    // Add fade in animation
    public static void addFadeInAnimation(Context context, View view, int duration) {
        Animation animFadeIn = AnimationUtils.loadAnimation(context, R.anim.fadein);
        animFadeIn.setDuration(duration);
        view.setAnimation(animFadeIn);
    }
}