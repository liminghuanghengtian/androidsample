package com.example.adaministrator.myapplication.hook;

import android.view.View;

/**
 * ProjectName：MyApplication
 * PackageName: com.example.adaministrator.myapplication.hook
 * Description:
 * <p>
 * CreateTime: 2017/12/17 19:11
 * Modifier: Adaministrator
 * ModifyTime: 2017/12/17 19:11
 * Comment:
 *
 * @author Adaministrator
 */

public interface MyOnClickListener {

    void setOnClickListener(View.OnClickListener listener);

    MyOnClickListener getOnClickListener();
}
