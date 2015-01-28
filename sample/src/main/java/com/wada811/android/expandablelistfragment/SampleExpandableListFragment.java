package com.wada811.android.expandablelistfragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

public class SampleExpandableListFragment extends ExpandableListFragment {

    private ExpandableListListener listener;
    private static final String KEY_EMPTY_TEXT = "KEY_EMPTY_TEXT";

    public static interface ExpandableListListenerProvider {

        public ExpandableListListener getExpandableListListener();

    }

    public static interface ExpandableListListener {

        /**
         * Callback method to be invoked when a group in this expandable list has
         * been clicked.
         *
         * @param parent The ExpandableListConnector where the click happened
         * @param v The view within the expandable list/ListView that was clicked
         * @param groupPosition The group position that was clicked
         * @param id The row id of the group that was clicked
         *
         * @return True if the click was handled
         */
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id);

        /**
         * Callback method to be invoked when a group in this expandable list has
         * been collapsed.
         *
         * @param groupPosition The group position that was collapsed
         */
        public void onGroupCollapse(int groupPosition);

        /**
         * Callback method to be invoked when a group in this expandable list has
         * been expanded.
         *
         * @param groupPosition The group position that was expanded
         */
        public void onGroupExpand(int groupPosition);

        /**
         * Callback method to be invoked when a child in this expandable list has
         * been clicked.
         *
         * @param parent The ExpandableListView where the click happened
         * @param v The view within the expandable list/ListView that was clicked
         * @param groupPosition The group position that contains the child that
         * was clicked
         * @param childPosition The child position within the group
         * @param id The row id of the child that was clicked
         *
         * @return True if the click was handled
         */
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id);
    }

    public static SampleExpandableListFragment newInstance(String emptyText){
        SampleExpandableListFragment expandableListFragment = new SampleExpandableListFragment();
        Bundle args = new Bundle();
        args.putString(KEY_EMPTY_TEXT, emptyText);
        expandableListFragment.setArguments(args);
        return expandableListFragment;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        if(activity instanceof ExpandableListListenerProvider == false){
            throw new ClassCastException(activity.getLocalClassName() + " must implements " + ExpandableListListenerProvider.class.getSimpleName());
        }
        ExpandableListListenerProvider provider = (ExpandableListListenerProvider)activity;
        listener = provider.getExpandableListListener();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setEmptyText(getArguments().getString(KEY_EMPTY_TEXT));
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id){
        return listener.onGroupClick(parent, v, groupPosition, id);
    }

    @Override
    public void onGroupCollapse(int groupPosition){
        super.onGroupCollapse(groupPosition);
        listener.onGroupCollapse(groupPosition);
    }

    @Override
    public void onGroupExpand(int groupPosition){
        super.onGroupExpand(groupPosition);
        listener.onGroupExpand(groupPosition);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){
        return listener.onChildClick(parent, v, groupPosition, childPosition, id);
    }

}
