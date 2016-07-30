package com.chronos.nine2five.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.chronos.nine2five.datastructures.inout.InOutListItem;
import com.chronos.nine2five.utils.Constants;

import java.util.List;

/**
 * Created by user on 16/07/2016.
 */
public class InOutListAdapter extends ArrayAdapter<InOutListItem> {

    private LayoutInflater mInflater;

    public InOutListAdapter(Context context, List<InOutListItem> items) {
        super(context, 0, items);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getViewTypeCount() {
        return Constants.INOUT_NUMBER_OF_TYPES;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int rowType = getItemViewType(position);

        if (convertView == null) {
            switch (rowType) {
                case Constants.INOUT_HEADER_ITEM:
                    convertView = getItem(position).getView(mInflater, convertView, parent);
                    break;
                case Constants.INOUT_RECORD_ITEM:
                    convertView = getItem(position).getView(mInflater, convertView, parent);
                    break;
            }
        }
        return convertView;
    }

}