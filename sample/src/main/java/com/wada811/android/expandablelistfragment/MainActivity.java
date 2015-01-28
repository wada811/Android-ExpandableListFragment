package com.wada811.android.expandablelistfragment;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ExpandableListView;
import com.wada811.android.expandablelistfragment.SampleExpandableListFragment.ExpandableListListener;
import com.wada811.android.expandablelistfragment.SampleExpandableListFragment.ExpandableListListenerProvider;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity implements ExpandableListListenerProvider {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportFragmentManager().findFragmentById(R.id.expandableListFragment) == null){
            SampleExpandableListFragment expandableListFragment = SampleExpandableListFragment.newInstance(getString(R.string.emptyText));
            getSupportFragmentManager().beginTransaction().replace(R.id.expandableListFragment, expandableListFragment).commit();

            List<Group> groupList = new ArrayList<>();
            List<List<Child>> childList = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                Group group = new Group();
                group.name = "Group" + String.valueOf(i);
                groupList.add(group);
                List<Child> children = new ArrayList<>();
                for(int j = 0; j < 10; j++){
                    Child child = new Child();
                    child.name = "Child" + String.valueOf(i) + "-" + String.valueOf(j);
                    children.add(child);
                }
                childList.add(children);
            }
            SampleExpandableListAdapter adapter = new SampleExpandableListAdapter(this, groupList, childList);
            expandableListFragment.setListAdapter(adapter);
        }

    }

    @Override
    public ExpandableListListener getExpandableListListener(){
        return new ExpandableListListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id){
                return false;
            }

            @Override
            public void onGroupCollapse(int groupPosition){

            }

            @Override
            public void onGroupExpand(int groupPosition){

            }

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){
                return false;
            }
        };
    }
}
