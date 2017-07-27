package com.vova.mobiledevelopertask.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.ActivityCompat;

public class MarshMallowPermissions {

    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 1;

    public static void requestPermissionForExternalStorage(final Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            showMessageOKCancel(activity, "Please confirm external storage access",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, new String[]{
                                            Manifest.permission.READ_EXTERNAL_STORAGE},
                                    EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
                        }
                    });
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
        }
    }

    public static void showMessageOKCancel(Context context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}
