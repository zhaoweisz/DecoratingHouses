package yyk.decoratinghouses.composant;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import yyk.decoratinghouses.R;
import yyk.decoratinghouses.util.DensityUtil;

/**
 * Created by wangyao on 2017/7/23.
 */

public class TitleBar extends RelativeLayout {

    private String title = "";
    private float titleTextSize;
    private int titleTextColor = 0xFFFFFFFF;
    private Drawable leftIcon;
    private Drawable rightIcon;
    private boolean leftIconVisible;
    private boolean rightIconVisible;

    private ImageView mLeftImageView;
    private ImageView mRightImageView;
    private TextView mTextView;

    private LayoutParams leftImageViewParams, rightImageViewParams, textViewParams;

    private onLeftClickListener onLeftClickListener;
    private onRightClickListener onRightClickListener;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        title = a.getString(R.styleable.TitleBar_title);
        titleTextSize = a.getDimension(R.styleable.TitleBar_titleTextSize, DensityUtil.sp2pxF(context, 18f));
        titleTextColor = a.getColor(R.styleable.TitleBar_titleTextColor, titleTextColor);
        leftIcon = a.getDrawable(R.styleable.TitleBar_leftIcon);
        rightIcon = a.getDrawable(R.styleable.TitleBar_rightIcon);
        leftIconVisible = a.getBoolean(R.styleable.TitleBar_leftIconVisible, true);
        rightIconVisible = a.getBoolean(R.styleable.TitleBar_rightIconVisible, false);
        a.recycle();
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            setElevation(DensityUtil.dip2px(context, 4));
        }
        mLeftImageView = new ImageView(context);
        mRightImageView = new ImageView(context);

        mTextView = new TextView(context);
        mTextView.setText(title);
        mTextView.getPaint().setTextSize(titleTextSize);
        mTextView.setTextColor(titleTextColor);
        mTextView.setGravity(Gravity.CENTER);

        if (leftIcon == null) {
            mLeftImageView.setBackgroundResource(R.drawable.circle_bg_ripples);
        } else {
            mLeftImageView.setBackgroundDrawable(leftIcon);
        }
        mLeftImageView.setImageResource(R.mipmap.ic_arrow_back_white_24dp);
        mLeftImageView.setScaleType(ImageView.ScaleType.CENTER);
        mLeftImageView.setPadding(
                DensityUtil.dip2px(context, 8f),
                DensityUtil.dip2px(context, 8f),
                DensityUtil.dip2px(context, 8f),
                DensityUtil.dip2px(context, 8f));

        if (rightIcon == null) {
            mRightImageView.setBackgroundResource(R.drawable.circle_bg_ripples);
        } else {
            mRightImageView.setBackgroundDrawable(leftIcon);
        }

        initLayoutParams(context);
    }

    private void initLayoutParams(final Context context) {
        textViewParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewParams.addRule(CENTER_IN_PARENT, TRUE);
        addView(mTextView, textViewParams);

        leftImageViewParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        leftImageViewParams.addRule(CENTER_VERTICAL, TRUE);
        leftImageViewParams.addRule(ALIGN_PARENT_LEFT, TRUE);
        leftImageViewParams.leftMargin = DensityUtil.dip2px(context, 8f);
        addView(mLeftImageView, leftImageViewParams);
        mLeftImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onLeftClickListener.onLeftClick();
            }
        });

        rightImageViewParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rightImageViewParams.addRule(CENTER_VERTICAL, TRUE);
        rightImageViewParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        rightImageViewParams.rightMargin = DensityUtil.dip2px(context, 16f);
        addView(mRightImageView, rightImageViewParams);
        mRightImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onRightClickListener.onRightClick();
            }
        });
    }

    public void setOnLeftClickListener(TitleBar.onLeftClickListener onLeftClickListener) {
        this.onLeftClickListener = onLeftClickListener;
    }

    public void setOnRightClickListener(TitleBar.onRightClickListener onRightClickListener) {
        this.onRightClickListener = onRightClickListener;
    }

    public void setLeftIconVisible(boolean leftIconVisible) {
        this.leftIconVisible = leftIconVisible;
        if (leftIconVisible) {
            mLeftImageView.setVisibility(VISIBLE);
        } else {
            mLeftImageView.setVisibility(GONE);
        }
    }

    public void setRightIconVisible(boolean rightIconVisible) {
        this.rightIconVisible = rightIconVisible;
        if (rightIconVisible) {
            mRightImageView.setVisibility(VISIBLE);
        } else {
            mRightImageView.setVisibility(GONE);
        }
    }

    public interface onLeftClickListener {
        void onLeftClick();
    }

    public interface onRightClickListener {
        void onRightClick();
    }
}
