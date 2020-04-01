//package com.business.cd1236.view.anim;
//
//import android.animation.Animator;
//import android.os.Build;
//import android.view.View;
//import android.view.ViewAnimationUtils;
//
//public class HomeButtonAnim {
//    public static void startAnim(View animateView){
//        // 显示
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            /**
//             * createCircularReveal 方法参数
//             * view 执行动画的view
//             * centerX 圆心横坐标
//             * centerY 圆心纵坐标
//             * startRadius 动画开始时圆的半径
//             * endRadius 动画结束时圆的半径
//             */
//            final Animator animator = ViewAnimationUtils.createCircularReveal(animateView,
//                    animateView.getWidth() / 2,
//                    animateView.getHeight() / 2,
//                    0,
//                    (float) Math.hypot(animateView.getWidth(), animateView.getHeight()));
//            // Math.hypot确定圆的半径（算长宽的斜边长，这样半径不会太短也不会很长效果比较舒服）
//
//            animatorHide.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//                    animateView.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//
//                }
//            });
//            animatorHide.setDuration(5000);
//            animatorHide.start();
//        } else {
//            animateView.setVisibility(View.VISIBLE);
//        }
//        animateView.setEnabled(true);
//    }
//}
