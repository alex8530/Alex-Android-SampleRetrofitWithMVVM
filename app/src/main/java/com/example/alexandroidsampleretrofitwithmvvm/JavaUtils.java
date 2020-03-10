package com.example.alexandroidsampleretrofitwithmvvm;

import android.content.Context;

public class JavaUtils {

    public static String checkErrorRequest(  String message) {
        return
                 !getStringWithoutNull(message).isEmpty()
                && (message.toLowerCase().startsWith("Unable to resolve host".toLowerCase())
                || message.toLowerCase().startsWith("failed to connect to".toLowerCase())
                || message.toLowerCase().startsWith("timeout".toLowerCase()))
                ? "يرجى التأكد من إتصالك بالإنترنت والمحاولة لاحقا"
                : message;
    }

    public static String getStringWithoutNull(String string) {
         return string == null ? "" : ("" + string).trim();
    }
}
