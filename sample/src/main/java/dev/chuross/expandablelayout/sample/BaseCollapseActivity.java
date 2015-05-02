package dev.chuross.expandablelayout.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import dev.chuross.library.ExpandableLayout;

public abstract class BaseCollapseActivity extends Activity {

    private ExpandableLayout expandableLayout;
    private Button button;

    protected abstract int getLayoutResourceId();

    protected abstract int getExpandableLayoutId();

    protected abstract int getButtonId();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        expandableLayout = (ExpandableLayout) findViewById(getExpandableLayoutId());

        button = (Button) findViewById(getButtonId());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(expandableLayout.isExpanded()) {
                    expandableLayout.collapse();
                } else {
                    expandableLayout.expand();
                }
            }
        });
    }

    public ExpandableLayout getExpandableLayout() {
        return expandableLayout;
    }

    public Button getButton() {
        return button;
    }
}
