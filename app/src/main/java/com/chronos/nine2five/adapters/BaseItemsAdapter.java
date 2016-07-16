package com.chronos.nine2five.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chronos.nine2five.R;
import com.chronos.nine2five.datastructures.BaseClass;

import java.util.List;

/**
 * Created by user on 13/05/2016.
 */
public class BaseItemsAdapter<T> extends ArrayAdapter<T> {

    private List<T> mBaseItemsList;

//    protected int mSelectedItemPosition ;
//    protected String mSelectedItemCode ;

    public BaseItemsAdapter(Context context, List<T> objects) {
        super(context, 0, objects);
//        this.mSelectedItemPosition = 0;
//        this.mSelectedItemCode = null;
        this.mBaseItemsList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }

    public void clearAll() {
//        for(int i = 0; i < getCount(); i++){
//            T item = getItem(i);
//            if (item instanceof BaseClass) {
//                ((BaseClass) item).setActive(false);
//            }
//        }

        for(T item : mBaseItemsList) {
            if (item instanceof BaseClass) {
                ((BaseClass) item).setActive(false);
            }
        }
    }

//    public int getSelectedItemPosition() {
//        return mSelectedItemPosition;
//    }
//
//    public void setSelectedItemPosition(int mSelectedItempPosition) {
//        this.mSelectedItemPosition = mSelectedItempPosition;
//    }
//
//    public String getSelectedItemCode() {
//        return mSelectedItemCode;
//    }
//
//    public void setSelectedItemCode(String selectedItemCode) {
//        this.mSelectedItemCode = selectedItemCode;
//    }

}
