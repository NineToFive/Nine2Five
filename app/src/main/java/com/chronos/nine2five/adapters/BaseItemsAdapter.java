package com.chronos.nine2five.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chronos.nine2five.R;
import com.chronos.nine2five.datastructures.BaseClass;

import java.util.List;

/**
 * Created by user on 13/05/2016.
 */
public class BaseItemsAdapter<T> extends ArrayAdapter<T> {

    protected int mSelectedItemPosition ;

    public BaseItemsAdapter(Context context, List<T> objects) {
        super(context, 0, objects);
        this.mSelectedItemPosition = 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }

    public int getSelectedItemPosition() {
        return mSelectedItemPosition;
    }

    public void setSelectedItemPosition(int mSelectedItempPosition) {
        this.mSelectedItemPosition = mSelectedItempPosition;
    }
}
