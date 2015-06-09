package dev.chuross.expandablelayout.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.github.chuross.library.ExpandableLayout;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;

public class ConfigurationChangeActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_change);

        final ExpandableLayout expandableLayout = (ExpandableLayout) findViewById(R.id.layout_expandable);
        TextView longText = (TextView) findViewById(R.id.txt_long);
        InputStream in = null;
        try {
            in = getAssets().open("long_text.txt");
            longText.setText(IOUtils.toString(in));
        } catch(Exception e) {
        } finally {
            IOUtils.closeQuietly(in);
        }
        findViewById(R.id.btn_toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(expandableLayout.isMoving()) {
                    return;
                }
                if(expandableLayout.isExpanded()) {
                    expandableLayout.collapse();
                } else {
                    expandableLayout.expand();
                }
            }
        });
    }
}
