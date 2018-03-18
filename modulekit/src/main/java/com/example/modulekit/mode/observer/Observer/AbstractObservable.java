package com.example.modulekit.mode.observer.Observer;

/**
 * ProjectName：MyApplication
 * PackageName: com.example.modulekit.mode.observer
 * Description:
 * <p>
 * CreateTime: 2018/3/18 19:21
 * Modifier: Adaministrator
 * ModifyTime: 2018/3/18 19:21
 * Comment:
 *
 * @author Adaministrator
 */

public abstract class AbstractObservable extends java.util.Observable {

    public void update() {
        notifyObservers();
    }

    public void update(Object arg) {
        notifyObservers(arg);
    }

    public abstract void updateInternal();
}
