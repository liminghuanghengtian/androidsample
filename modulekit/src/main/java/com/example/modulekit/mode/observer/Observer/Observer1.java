package com.example.modulekit.mode.observer.Observer;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * ProjectName：MyApplication
 * PackageName: com.example.modulekit.mode.observer.Observer
 * Description:
 * <p>
 * CreateTime: 2018/3/18 20:02
 * Modifier: Adaministrator
 * ModifyTime: 2018/3/18 20:02
 * Comment:
 *
 * @author Adaministrator
 */

public class Observer1 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        Log.i("Observer1", o.toString() + (arg == null ? "" : arg));
    }
}
