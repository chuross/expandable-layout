package dev.chuross.expandablelayout.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.github.chuross.library.ExpandableLayout;

public class ListActivity extends Activity {

    private SparseArray<Boolean> expandMap = new SparseArray<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(new ArrayAdapter<Void>(this, 0) {
            @Override
            public View getView(final int position, final View convertView, final ViewGroup parent) {
                final View view = convertView != null ? convertView : LayoutInflater.from(getContext()).inflate(R.layout.adapter_expandable_layout, parent, false);

                ((TextView) view.findViewById(R.id.txt_position)).setText("pos=" + position);

                final ExpandableLayout expandableLayout = (ExpandableLayout) view.findViewById(R.id.layout_expandable);
                if(expandMap.indexOfKey(position) >= 0 && expandMap.get(position)) {
                    expandableLayout.expand(false);
                } else {
                    expandableLayout.collapse(false);
                }

                view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        if(expandableLayout.isExpanded()) {
                            expandableLayout.collapse();
                            expandMap.put(position, false);
                        } else {
                            expandableLayout.expand();
                            expandMap.put(position, true);
                        }
                    }
                });
                return view;
            }

            @Override
            public int getCount() {
                return 10;
            }
        });
    }
}
