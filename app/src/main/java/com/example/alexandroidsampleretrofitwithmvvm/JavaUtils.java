package com.example.alexandroidsampleretrofitwithmvvm;

import android.content.Context;

public class JavaUtils {

    public static String checkErrorRequest(Context mContext, String message) {
        return mContext != null
                && !getStringWithoutNull(message).isEmpty()
                && (message.toLowerCase().startsWith("Unable to resolve host".toLowerCase())
                || message.toLowerCase().startsWith("failed to connect to".toLowerCase())
                || message.toLowerCase().startsWith("timeout".toLowerCase()))
                ? mContext.getString(R.string.please_make_sure_you_are_connected_to_the_internet_and_try_again)
                : message;
    }

    public static String getStringWithoutNull(String string) {
         return string == null ? "" : ("" + string).trim();
    }
}
