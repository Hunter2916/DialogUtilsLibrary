/*
* ECARX Technology Limited is the owner of the copyright and the trade secret of this software.
* Without permission, no one has the right to obtain, disclose or use this software in any way.
*/

package com.example.dialog.library;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FormatUtils {
    private static final String TAG = "FormatUtils";

    private static final long ONE_HOUR_IN_SECOND = 60 * 60;
    private static final long ONE_DAY_IN_SECOND = 24 * ONE_HOUR_IN_SECOND;
    private static final long TEN_SECOND = 10;



    /**
     * 倒计时
     * @param milliseconds
     * @return
     */
    public static String formatCountDownTime(long milliseconds) {
        Date dates = new Date(milliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return sdf.format(dates);
    }

    public static String formatToday() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(calendar.getTime());
    }

}
