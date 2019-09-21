package com.example.tele.custombleservice;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by leger on 2019-09-16.
 */

public class NetUtils {
    /**
     *判断位置信息是否开启
     * @param context
     * @return
     */
    public static boolean isLocationOpen(final Context context){
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //gps定位
        boolean isGpsProvider = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //网络定位
        boolean isNetWorkProvider = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return isGpsProvider|| isNetWorkProvider;
    }
}
