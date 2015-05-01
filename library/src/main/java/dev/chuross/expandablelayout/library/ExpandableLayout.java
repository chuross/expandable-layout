package dev.chuross.expandablelayout.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class ExpandableLayout extends FrameLayout {

    private static final Interpolator DEFAULT_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private static final int DEFAULT_DURATION = 500;
    private int collapseHeight;
    private int collapseTargetId;
    private int duration;
    private int measuredHeight = -1;
    private Scroller scroller;
    private Status status = Status.COLLAPSED;
    private Runnable movingRunnable = new Runnable() {
        @Override
        public void run() {
            if(scroller.computeScrollOffset()) {
                getLayoutParams().height = scroller.getCurrY();
                requestLayout();
                post(this);
                return;
            }
            if(scroller.getCurrY() == getCollapseHeight()) {
                status = Status.COLLAPSED;
            } else {
                status = Status.EXPANDED;
            }
        }
    };

    public ExpandableLayout(final Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public ExpandableLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public ExpandableLayout(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExpandableLayout(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        scroller = new Scroller(getContext(), DEFAULT_INTERPOLATOR);
        if(attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableLayout, defStyleAttr, defStyleRes);
        collapseHeight = typedArray.getDimensionPixelOffset(R.styleable.ExpandableLayout_exl_collapse_height, 0);
        collapseTargetId = typedArray.getResourceId(R.styleable.ExpandableLayout_exl_collapse_target_id, 0);
        duration = typedArray.getInteger(R.styleable.ExpandableLayout_exl_duration, 0);
        boolean initialExpanded = typedArray.getBoolean(R.styleable.ExpandableLayout_exl_expanded, false);
        status = initialExpanded ? Status.EXPANDED : Status.COLLAPSED;
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        if(measuredHeight < 0) {
            measuredHeight = getMeasuredHeight();
        }
        if(isExpanded()) {
            setMeasuredDimension(widthMeasureSpec, measuredHeight);
            return;
        }
        if(isCollapsed()) {
            setMeasuredDimension(measuredWidth, getCollapseHeight());
        } else {
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private int getCollapseHeight() {
        if(collapseHeight > 0) {
            return collapseHeight;
        }
        View view = findViewById(collapseTargetId);
        if(view == null) {
            return 0;
        }
        return view.getTop() - getTop();
    }

    private int getDuration() {
        return duration > 0 ? duration : DEFAULT_DURATION;
    }

    public void expand() {
        if(isExpanded() || isMoving()) {
            return;
        }
        status = Status.MOVING;
        scroller.startScroll(0, getBottom(), 0, measuredHeight - getCollapseHeight(), getDuration());
        post(movingRunnable);
    }

    public void collapse() {
        if(isCollapsed() || isMoving()) {
            return;
        }
        status = Status.MOVING;
        scroller.startScroll(0, measuredHeight, 0, -(measuredHeight - getCollapseHeight()), getDuration());
        post(movingRunnable);
    }

    public Status getStatus() {
        return status;
    }

    public boolean isExpanded() {
        return status != null && status.equals(Status.EXPANDED);
    }

    public boolean isCollapsed() {
        return status != null && status.equals(Status.COLLAPSED);
    }

    public boolean isMoving() {
        return status != null && status.equals(Status.MOVING);
    }

    public enum Status {
        EXPANDED, COLLAPSED, MOVING
    }
}
