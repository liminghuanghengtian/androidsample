package com.example.adaministrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_rx_java);
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                // e.onNext(2);
                // e.onNext(3);
                // e.onNext(4);
                // e.onNext(5);
                // e.onNext(6);
                // e.onNext(7);
                // e.onNext(8);
                // e.onNext(9);
                // e.onNext(0);
                e.onComplete();
            }
        })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        Log.e("Observable", "Observable filter in thread: " + Thread.currentThread().toString());
                        // 2,5,8
                        // if (integer % 3 == 2) {
                        //     return true;
                        // } else {
                        // 4
                        // if (integer % 4 == 0) {
                            throw new Exception("");
                        //     return false;
                        // }
                        // // 过滤掉1,3
                        // return true;
                        // }
                    }
                }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer i) throws Exception {
                Log.e("Observable", "Observable map in thread: " + Thread.currentThread().toString());
                return String.valueOf(i);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            private static final String TAG = "Observer";

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext -> " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "error occur in thread: " + Thread.currentThread().toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete occur in thread: " + Thread.currentThread().toString());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
