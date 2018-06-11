package com.match.library.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.match.library.R;


public class Loading {
    private Dialog mDialog;
    private Boolean isShowing = false;
    private Context context;

    public Loading(Context context) {
        this.context = context;
        mDialog = new Dialog(context, R.style.loading_dialog);
        mDialog.setContentView(R.layout.progressdialog);
        mDialog.findViewById(R.id.progressBar);
        mDialog.setCancelable(false);
        //description.setWidth(300);
        Window window = mDialog.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams lp = window.getAttributes();

        window.setAttributes(lp);
    }

    public boolean show() {
        if (!isShowing) {
            isShowing = true;
            mDialog.show();
        }
        return true;
    }

    public boolean dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            isShowing = false;
            mDialog.cancel();
        }
        return true;
    }

//    public void show() {
//        mDialog.show();
//    }
}
