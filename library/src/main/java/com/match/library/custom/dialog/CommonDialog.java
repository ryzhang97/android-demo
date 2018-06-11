package com.match.library.custom.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.match.library.R;


/**
 * Author Zhenyu
 * Date 2017/8/25
 * Time 15:22
 * Project Mcr_SH_Android_New
 * 只有文字的Dialog
 */
public class CommonDialog extends Dialog {
    private TextView contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;

    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;
    private boolean isShow = true;

    public CommonDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public CommonDialog(Context context, boolean isShow, String content, OnCloseListener listener) {
        super(context);
        this.mContext = context;
        this.isShow = isShow;
        this.listener = listener;
        this.content = content;
    }

    public CommonDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public CommonDialog(Context context, String content, OnCloseListener listener) {
        super(context);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected CommonDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public CommonDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public CommonDialog setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    public CommonDialog setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        contentTxt = findViewById(R.id.tv_dialog_common_content);
        titleTxt = findViewById(R.id.title);
        submitTxt = findViewById(R.id.btn_dialog_common_submit);

        cancelTxt = findViewById(R.id.btn_dialog_common_cancel);

        contentTxt.setText(content);
        if (!isShow) {
            cancelTxt.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(positiveName)) {
            submitTxt.setText(positiveName);
        }

        if (!TextUtils.isEmpty(negativeName)) {
            cancelTxt.setText(negativeName);
        }

        if (!TextUtils.isEmpty(title)) {
            titleTxt.setText(title);
        }
        submitTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(CommonDialog.this, true);
                }
            }
        });
        cancelTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(CommonDialog.this, true);
                }
            }
        });

    }


    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm);
    }
}
