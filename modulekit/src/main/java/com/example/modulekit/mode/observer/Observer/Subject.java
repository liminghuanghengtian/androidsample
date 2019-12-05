package com.example.modulekit.mode.observer.Observer;

/**
 * ProjectName：MyApplication
 * PackageName: com.example.modulekit.mode.observer.Observer
 * Description:
 * <p>
 * CreateTime: 2018/3/18 20:01
 * Modifier: Adaministrator
 * ModifyTime: 2018/3/18 20:01
 * Comment:
 *
 * @author Adaministrator
 */

public class Subject extends java.util.Observable {

    public void operate() {
        notifyObservers();
    }
}
