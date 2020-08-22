package com.samuelbernard.starbhakattendance.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.samuelbernard.starbhakattendance.R;

public class DialogHelper {
    private Context context;

    public DialogHelper(Context context) {
        this.context = context;
    }

    public void showMessageSnackBar(View parent, String message, int duration) {
        Snackbar.make(parent, message, duration)
                .setAction(context.getString(R.string.dialog_title_confirmation_close), v -> {

                })
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                .show();
    }

    public void showDialogSubmit(@Nullable String title, @Nullable String message, boolean cancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(cancelable);
        builder.setPositiveButton(context.getString(R.string.dialog_title_confirmation_close), null);
        builder.show();
    }

    public void showDialogFinish(Activity activity, @Nullable String title, @Nullable String message, boolean cancelable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(cancelable);
        builder.setPositiveButton(context.getString(R.string.dialog_title_confirmation_close), (dialogInterface, i) -> activity.finish());
        builder.show();
    }
}