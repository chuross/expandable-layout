package dev.chuross.expandablelayout.sample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

    private static final String[] NAMES = new String[] {
            CollapseHeightActivity.class.getSimpleName()
    };
    private static final Class[] CLASSES = new Class[] {
            CollapseHeightActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, NAMES));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                startActivity(new Intent(MainActivity.this, CLASSES[position]));
            }
        });
    }
}
