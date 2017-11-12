package com.example.adaministrator.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

public class AnimatorActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        textView = (TextView) this.findViewById(R.id.tv_test);

//        AnimatorSet set = new AnimatorSet();
//        set.playTogether(
//                ObjectAnimator.ofFloat(textView, "rotationX", 0, 360),// 绕X轴旋转360度
//                ObjectAnimator.ofFloat(textView, "rotationY", 0, 180),// 绕Y轴旋转180度
//                ObjectAnimator.ofFloat(textView, "rotation", 0, -90),
//                ObjectAnimator.ofFloat(textView, "translationX", 0, 90),
//                ObjectAnimator.ofFloat(textView, "translationY", 0, 90),
//                ObjectAnimator.ofFloat(textView, "scaleX", 1, 1.5f),// x轴放大1.5倍
//                ObjectAnimator.ofFloat(textView, "scaleY", 1, 0.5f),// y轴缩小为一半
//                ObjectAnimator.ofFloat(textView, "alpha", 1, 0.25f, 1)// 透明度增加再减小
//        );
//        set.setDuration(5000).start();

//        AnimatorSet set1 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_set);
//        set1.setTarget(textView);
//        set1.start();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


        if (hasFocus) {
            //利用AnimatorSet和ObjectAnimator实现缩放动画
            final AnimatorSet animatorSet = new AnimatorSet();
//            textView.setPivotX(textView.getWidth() / 2);
//            textView.setPivotY(textView.getHeight() / 2);
            animatorSet.playTogether(
//                    ObjectAnimator.ofFloat(textView, "scaleX", 1, 0.8f).setDuration(5000),
//                    ObjectAnimator.ofFloat(textView, "scaleY", 1, 0.8f).setDuration(5000),
                    //利用ObjectAnimator实现透明度动画
//                    ObjectAnimator.ofFloat(textView, "alpha", 1, 0, 1).setDuration(2000),
//                    //利用AnimatorSet和ObjectAnimator实现平移动画
//                    ObjectAnimator.ofFloat(textView, "translationX", 0, textView.getWidth(), textView.getWidth() / 2).setDuration(2000),
//                    ObjectAnimator.ofFloat(textView, "translationY", 0, 100, 0).setDuration(2000),
                    ObjectAnimator.ofFloat(textView, "rotation", 0, 180).setDuration(8000),
                    ObjectAnimator.ofInt(textView, "backgroundColor", 0xFFFF8080, 0xFF8080FF));

            animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animatorSet.start();
        }
//        //利用AnimatorSet和ObjectAnimator实现平移动画
//        AnimatorSet animatorSet1 = new AnimatorSet();
//        animatorSet1.playTogether(
//                ObjectAnimator.ofFloat(textView, "translationX", 20, 100).setDuration(2000),
//                ObjectAnimator.ofFloat(textView, "translationY", 20, 100).setDuration(2000));
//        animatorSet1.start();
//        //利用ObjectAnimator实现旋转动画
//        textView.setPivotX(textView.getWidth() / 2);
//        textView.setPivotY(textView.getHeight() / 2);
//        ObjectAnimator.ofFloat(textView, "rotation", 0, 360).setDuration(1000).start();
    }
}
