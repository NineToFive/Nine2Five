package com.chronos.nine2five.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chronos.nine2five.R;
import com.chronos.nine2five.datastructures.inout.InOutListItem;
import com.chronos.nine2five.datastructures.inout.InOutRecordItem;
import com.chronos.nine2five.utils.Constants;

import java.util.List;

/**
 * Created by user on 16/07/2016.
 */
public class InOutListAdapter extends ArrayAdapter<InOutListItem> {

    private LayoutInflater mInflater;
    private int mNumberOfTypes;

    public InOutListAdapter(Context context, List<InOutListItem> items) {
        super(context, 0, items);
        this.mNumberOfTypes = 1;
        this.mInflater = LayoutInflater.from(context);
    }

    public InOutListAdapter(Context context, List<InOutListItem> items,int numberOfTypes) {
        this(context, items);
        this.mNumberOfTypes = numberOfTypes;
    }

    @Override
    public int getViewTypeCount() {
        return this.mNumberOfTypes;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.inout_record_item, parent, false);
            holder = new ViewHolder();
            holder.textViewInDate = (TextView) convertView.findViewById(R.id.txtInDate);
            holder.textViewOutDate = (TextView) convertView.findViewById(R.id.txtOutDate);
            holder.textViewDuration = (TextView) convertView.findViewById(R.id.txtInOutDuration);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        InOutListItem listItemToDisplay = getItem(position);
        if (listItemToDisplay instanceof InOutRecordItem) {
            holder.textViewInDate.setText(((InOutRecordItem) listItemToDisplay).getInDate());
            holder.textViewOutDate.setText(((InOutRecordItem) listItemToDisplay).getOutDate());
            holder.textViewDuration.setText(((InOutRecordItem) listItemToDisplay).getDuration());
        }

//        int rowType = getItemViewType(position);
//
//        if (convertView == null) {
//            switch (rowType) {
//                case Constants.INOUT_HEADER_ITEM:
//                    convertView = getItem(position).getView(mInflater, convertView, parent);
//                    break;
//                case Constants.INOUT_RECORD_ITEM:
//                    convertView = getItem(position).getView(mInflater, convertView, parent);
//                    break;
//            }
//        }
        return convertView;
    }


    private static class ViewHolder {
        TextView textViewInDate;
        TextView textViewOutDate;
        TextView textViewDuration;
    }
}