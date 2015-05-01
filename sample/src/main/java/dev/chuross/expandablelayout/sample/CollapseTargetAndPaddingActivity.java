package dev.chuross.expandablelayout.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import dev.chuross.expandablelayout.library.ExpandableLayout;

public class CollapseTargetAndPaddingActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse_target_and_padding);

        final ExpandableLayout expandableLayout = (ExpandableLayout) findViewById(R.id.layout_expandable);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(expandableLayout.isCollapsed()) {
                    expandableLayout.expand();
                } else {
                    expandableLayout.collapse();
                }
            }
        });
    }
}
