package com.chronos.nine2five.datastructures.inout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chronos.nine2five.R;
import com.chronos.nine2five.utils.Constants;

/**
 * Created by user on 16/07/2016.
 */
public class InOutHeader implements InOutListItem {

    private ViewHold holder;
    private String mDate;
    private String mTotalDuration;

    public InOutHeader(String mDate, String mTotalDuration) {
        this.mDate = mDate;
        this.mTotalDuration = mTotalDuration;
    }

    @Override
    public int getViewType() {
        return Constants.INOUT_HEADER_ITEM;
    }

//    @Override
//    public View getView(LayoutInflater inflater, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.inout_header_item, parent, false);
//            holder = new ViewHold();
//            holder.textViewDate = (TextView) convertView.findViewById(R.id.txtHeaderDate);
//            holder.textViewTotalDuration = (TextView) convertView.findViewById(R.id.txtHeaderTotalDuration);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHold) convertView.getTag();
//        }
//
//        holder.textViewDate.setText(mDate);
//        holder.textViewTotalDuration.setText(mTotalDuration);
//        return convertView;
//    }

    private static class ViewHold {
        TextView textViewDate;
        TextView textViewTotalDuration;
    }
}
