package com.example.alexandroidsampleretrofitwithmvvm;

import android.app.Activity;

import com.kaopiz.kprogresshud.KProgressHUD;

public class ConstantJava {
    static KProgressHUD hud;

    public static void showHUDailog(Activity activity, boolean status) {
        if (activity != null) {
            if (status) {
                if (hud != null) {
                    if (!hud.isShowing()) {
                        hud = KProgressHUD.create(activity)
                                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                        .setBackgroundColor(activity.getResources().getColor(R.color.colorAccent))
                                .setCancellable(true)
                                .setAnimationSpeed(2)
                                .setDimAmount(0.5f)
                                .show();
                    }
                } else {
                    hud = KProgressHUD.create(activity)
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                        .setBackgroundColor(activity.getResources().getColor(R.color.colorAccent))
                            .setCancellable(true)
                            .setAnimationSpeed(2)
                            .setDimAmount(0.5f)
                            .show();
                }

            } else {
                if (hud != null && hud.isShowing()) {
                    hud.dismiss();
                }
            }
        }


    }


}
