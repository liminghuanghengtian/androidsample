package com.example.adaministrator.myapplication.hook;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * ProjectName：MyApplication
 * PackageName: com.example.adaministrator.myapplication.hook
 * Description:
 * <p>
 * CreateTime: 2017/12/17 19:09
 * Modifier: Adaministrator
 * ModifyTime: 2017/12/17 19:09
 * Comment:
 *
 * @author Adaministrator
 */

public class MyButton extends Button implements MyOnClickListener {

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setOnClickListener(View.OnClickListener listener) {
        super.setOnClickListener(listener);
    }

    @Override
    public MyOnClickListener getOnClickListener() {
        return null;
    }
}
