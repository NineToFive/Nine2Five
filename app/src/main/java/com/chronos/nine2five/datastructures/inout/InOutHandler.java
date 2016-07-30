package com.chronos.nine2five.datastructures.inout;

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

    private List<InOut> mInOutList;
    private User mUser;

    public InOutHandler(User user, List<InOut> mInOutList) {
        this.mInOutList = mInOutList;
        this.mUser = user;
    }

    public void punchIn() {
        InOut inOutToAdd = new InOut(mUser, new Date(), null);
        mInOutList.add(inOutToAdd);
    }

    public void punchOut() {
        int position = mInOutList.size() - 1;
        mInOutList.get(position).setOutDate(new Date());
    }

    public List<InOutListItem> getInOutListItems() {
        List<InOutListItem> inOutListItems = new ArrayList<>();

        boolean isNewDate = true;
        SimpleDateFormat dateOnlyFormat = new SimpleDateFormat(Constants.DATE_ONLY_FORMAT, Locale.CANADA);
        String previousDate = "";
        String currentDate = "";
        long totalInOutHeader = 0L;
        long totalInOutRecord = 0L;
        long inTime,outTime;

        for (InOut inOut : mInOutList) {
            currentDate = dateOnlyFormat.format(inOut.getInDate());

            inTime = (inOut.getInDate() == null) ? 0 : inOut.getInDate().getTime();
            outTime = (inOut.getOutDate() == null ) ? 0 : inOut.getOutDate().getTime();
            totalInOutRecord = Helpers.calculateDuration(inOut.getInDate(),inOut.getOutDate());

            if (currentDate.equals(previousDate)) {
                InOutListItem recordToAdd = new InOutRecordItem(Helpers.FormatTimeString(inTime),Helpers.FormatTimeString(outTime),Helpers.FormatTimeString(totalInOutRecord));
                totalInOutHeader += totalInOutRecord;
                inOutListItems.add(recordToAdd);

            } else {
                InOutListItem headerToAdd = new InOutHeader(currentDate,Helpers.FormatTimeString(totalInOutHeader));
                previousDate = currentDate;
                totalInOutHeader = 0;
                inOutListItems.add(headerToAdd);
            }


        }
        return inOutListItems;
    }
}
