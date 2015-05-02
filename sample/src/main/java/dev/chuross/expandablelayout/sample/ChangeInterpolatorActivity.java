package dev.chuross.expandablelayout.sample;

import android.os.Bundle;
import android.view.animation.BounceInterpolator;

public class ChangeInterpolatorActivity extends BaseCollapseActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_basic_expandablelayout;
    }

    @Override
    protected int getExpandableLayoutId() {
        return R.id.layout_expandable;
    }

    @Override
    protected int getButtonId() {
        return R.id.button;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExpandableLayout().setInterpolator(new BounceInterpolator());
    }
}
