package com.chronos.nine2five.datastructures;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 16/07/2016.
 */
public interface InOutListItem {
    public int getViewType();
    public View getView(LayoutInflater inflater, View convertView, ViewGroup parent);
}
