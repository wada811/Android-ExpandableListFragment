package com.wada811.android.expandablelistfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class SampleExpandableListAdapter extends BindableExpandableListAdapter<Group, Child> {

    public SampleExpandableListAdapter(Context context, List<Group> groupList, List<List<Child>> childList){
        super(context, groupList, childList);
    }

    @Override
    public View newGroupView(LayoutInflater inflater, int groupPosition, boolean isExpanded, ViewGroup container){
        View view = inflater.inflate(R.layout.list_item_group, container, false);
        GroupViewHolder holder = new GroupViewHolder(view);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindGroupView(Group group, int groupPosition, View view){
        GroupViewHolder holder = (GroupViewHolder)view.getTag();
        holder.name.setText(group.name);
    }

    public class GroupViewHolder {

        public TextView name;

        public GroupViewHolder(View view){
            name = (TextView)view.findViewById(R.id.name);
        }

    }

    @Override
    public View newChildView(LayoutInflater inflater, int groupPosition, int childPosition, boolean isLastChild, ViewGroup container){
        View view = inflater.inflate(R.layout.list_item_child, container, false);
        ChildViewHolder holder = new ChildViewHolder(view);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindChildView(Child child, int groupPosition, int childPosition, View view){
        ChildViewHolder holder = (ChildViewHolder)view.getTag();
        holder.name.setText(child.name);
    }

    public class ChildViewHolder {

        public TextView name;

        public ChildViewHolder(View view){
            name = (TextView)view.findViewById(R.id.name);
        }

    }
}
