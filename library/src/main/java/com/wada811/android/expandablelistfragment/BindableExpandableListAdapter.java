package com.wada811.android.expandablelistfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import java.util.List;

public abstract class BindableExpandableListAdapter<G, C> extends BaseExpandableListAdapter {

    private LayoutInflater inflater;
    private List<G> groupList;
    private List<List<C>> childList;

    public BindableExpandableListAdapter(Context context, List<G> groupList, List<List<C>> childList){
        this.groupList = groupList;
        this.childList = childList;
        setup(context);
    }

    public void setup(Context context){
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount(){
        return groupList.size();
    }

    @Override
    public G getGroup(int groupPosition){
        return groupList.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition){
        return groupPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return childList.get(groupPosition).size();
    }

    @Override
    public C getChild(int groupPosition, int childPosition){
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition){
        return childPosition;
    }

    @Override
    public boolean hasStableIds(){
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup container){
        if(view == null){
            view = newGroupView(inflater, groupPosition, isExpanded, container);
            if(view == null){
                throw new IllegalStateException("newGroupView result must not be null.");
            }
        }
        bindGroupView(getGroup(groupPosition), groupPosition, view);
        return view;
    }

    public abstract View newGroupView(LayoutInflater inflater, int groupPosition, boolean isExpanded, ViewGroup container);

    public abstract void bindGroupView(G group, int groupPosition, View view);

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup container){
        if(view == null){
            view = newChildView(inflater, groupPosition, childPosition, isLastChild, container);
            if(view == null){
                throw new IllegalStateException("newChildView result must not be null.");
            }
        }
        bindChildView(getChild(groupPosition, childPosition), groupPosition, childPosition, view);
        return view;
    }

    public abstract View newChildView(LayoutInflater inflater, int groupPosition, int childPosition, boolean isLastChild, ViewGroup container);

    public abstract void bindChildView(C child, int groupPosition, int childPosition, View view);
}
