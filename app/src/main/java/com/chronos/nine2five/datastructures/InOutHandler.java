package com.chronos.nine2five.datastructures;

import java.util.Date;
import java.util.List;

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
        int position = mInOutList.size();
        mInOutList.get(position).setOutDate(new Date());
    }
}
