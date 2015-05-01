package dev.chuross.expandablelayout.sample;

public class CollapseDurationActivity extends BaseCollapseActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_collapse_duration;
    }

    @Override
    protected int getExpandableLayoutId() {
        return R.id.layout_expandable;
    }

    @Override
    protected int getButtonId() {
        return R.id.button;
    }
}
