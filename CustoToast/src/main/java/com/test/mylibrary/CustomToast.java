package com.test.mylibrary;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

/**
 * @project： temp
 * @package： com.test.toast
 * @class: LwToastUtils
 * @author: 陆伟
 * @Date: 2016/12/31 15:52
 * @desc： 系统toast太丑, 别怪我了哈
 */
public class CustomToast {
    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static final int LENGTH_LONG = Toast.LENGTH_LONG;
    private Toast mToast;
    private ImageView mImageView;
    private TextView mTextView;

    public CustomToast (Context context, String text) {
        this (context, text, LENGTH_LONG);
    }

    public CustomToast (Context context, String text, int duration) {
        this (context, text, null, duration);
    }

    public CustomToast (Context context, String text, Drawable drawable, int duration) {
        View toastView = View.inflate (context, R.layout.view_custom_toast, null);

        toastView.setAlpha (0.0f);
        toastView.setScaleX (0.0f);
        toastView.setScaleY (0.0f);

        toastView.animate ()
                .scaleX (.9f)
                .scaleY (.9f)
                .alpha (0.9f)
                .setDuration (1200)
                .setInterpolator (new OvershootInterpolator ())
                .start ();

        mToast = new Toast (context);

        ShimmerFrameLayout container = (ShimmerFrameLayout) toastView.findViewById (R.id.shimmer_view_container);
        container.setRepeatMode (ObjectAnimator.REVERSE);
        container.setBaseAlpha (0.8f);
        container.startShimmerAnimation ();
        mImageView = (ImageView) toastView.findViewById (R.id.imageview);
        mTextView = (TextView) toastView.findViewById (R.id.text);
        
        setImageView (drawable, mImageView);

        mTextView.setText (text);

        mToast.setView (toastView);
        mToast.setDuration (duration);
        mToast.show ();
    }

    /**
     * 设置文字颜色
     *
     * @param color 颜色
     * @return 当前对象, 方便链式调用
     */
    public CustomToast setTextColor (int color) {
        mTextView.setTextColor (color);
        return this;
    }

    /**
     * 文字View的背景颜色
     *
     * @param color 颜色
     * @return 当前对象, 方便链式调用
     */
    public CustomToast setTextBackground (int color) {
        mTextView.setBackgroundColor (color);
        return this;
    }

    /**
     * 文字View的背景颜色
     *
     * @param background 可以帧动画也是可以的哦
     * @return 当前对象, 方便链式调用
     */
    public CustomToast setTextBackground (Drawable background) {
        if (background == null) {
            return this;
        }
        mTextView.setBackground (background);
        return this;
    }

    /**
     * 动画view的背景颜色
     *
     * @param color 颜色
     * @return 当前对象, 方便链式调用
     */
    public CustomToast setDrawbleBackground (int color) {
        mImageView.setBackgroundColor (color);
        return this;
    }

    /**
     * 动画view的背景颜色
     *
     * @param background 可以帧动画也是可以的哦
     * @return 当前对象, 方便链式调用
     */
    public CustomToast setDrawbleBackground (Drawable background) {
        if (background == null) {
            return this;
        }
        mImageView.setBackground (background);
        return this;
    }

    private void setImageView (Drawable drawable, ImageView imageView) {
        if (drawable == null) {
            imageView.setImageResource (R.drawable.default_anima);
            AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable ();
            animationDrawable.start ();
        } else {
            imageView.setImageDrawable (drawable);
        }
    }



}
