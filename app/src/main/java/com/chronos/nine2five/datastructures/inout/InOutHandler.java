package com.chronos.nine2five.datastructures.inout;

import android.util.Log;

import com.chronos.nine2five.datastructures.User;
import com.chronos.nine2five.datastructures.inout.InOut;
import com.chronos.nine2five.utils.Constants;
import com.chronos.nine2five.utils.Helpers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by user on 16/07/2016.
 */
public class InOutHandler {

    private static final String TAG = InOutHandler.class.getSimpleName();

    private List<InOut> mInOutList;
    private User mUser;
    private List<InOutListItem> mInOutListItems;
    private static SimpleDateFormat mDateOnlyFormat =
            new SimpleDateFormat(Constants.DATE_ONLY_FORMAT, Locale.CANADA);

    public InOutHandler(User user, List<InOut> mInOutList) {
        this.mInOutList = mInOutList;
        this.mUser = user;
        this.mInOutListItems = new ArrayList<>();
        // TODO: need to add initializer to mInOutListItems based upon mInOutList.
    }

    public void punchIn() {
        // TODO: add the punch in paparmeter in order to work on the exact punch time
        InOut inOutToAdd = new InOut(mUser, new Date(), null);
        mInOutList.add(inOutToAdd);

        long inTime = (inOutToAdd.getInDate() == null) ? 0 : inOutToAdd.getInDate().getTime();
        long totalInOutRecord = Helpers.calculateDuration(inOutToAdd.getInDate(),inOutToAdd.getOutDate());

        InOutListItem recordToAdd = new InOutRecordItem(Helpers.FormatTimeString(inTime),Helpers.FormatTimeString(0),Helpers.FormatTimeString(totalInOutRecord));
        mInOutListItems.add(recordToAdd);
    }

    public void punchOut() {
        // TODO: add the punch in paparmeter in order to work on the exact punch time
        int position = mInOutList.size() - 1;
        InOut lastInOut = mInOutList.get(position);
        lastInOut.setOutDate(new Date());

        long outTime = (lastInOut.getOutDate() == null ) ? 0 : lastInOut.getOutDate().getTime();
        long totalInOutRecord = Helpers.calculateDuration(lastInOut.getInDate(),lastInOut.getOutDate());

        InOutRecordItem listItemToUpdate = (InOutRecordItem) mInOutListItems.get(position);
        listItemToUpdate.setOutDate(Helpers.FormatTimeString(outTime));
        listItemToUpdate.setDuration(Helpers.FormatTimeString(totalInOutRecord));
    }

    public List<InOutListItem> getInOutListItems() {
        mInOutListItems.clear();

        boolean isNewDate = true;
        String previousDate = "";
        String currentDate = "";
        long totalInOutHeader = 0L;
        long totalInOutRecord = 0L;
        long inTime,outTime;

        for (InOut inOut : mInOutList) {
            currentDate = mDateOnlyFormat.format(inOut.getInDate());

            inTime = (inOut.getInDate() == null) ? 0 : inOut.getInDate().getTime();
            outTime = (inOut.getOutDate() == null ) ? 0 : inOut.getOutDate().getTime();
            totalInOutRecord = Helpers.calculateDuration(inOut.getInDate(),inOut.getOutDate());

            if (currentDate.equals(previousDate)) {
                InOutListItem recordToAdd = new InOutRecordItem(Helpers.FormatTimeString(inTime),Helpers.FormatTimeString(outTime),Helpers.FormatTimeString(totalInOutRecord));
                totalInOutHeader += totalInOutRecord;
                mInOutListItems.add(recordToAdd);

            } else {
                InOutListItem headerToAdd = new InOutHeader(currentDate,Helpers.FormatTimeString(totalInOutHeader));
                previousDate = currentDate;
                totalInOutHeader = 0;
                mInOutListItems.add(headerToAdd);
            }


        }
        return mInOutListItems;
    }

}
