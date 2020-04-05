package com.business.cd1236.utils;

import android.app.Activity;

import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.permissions.PermissionChecker;

public class PermissionsUtil {
    public static void checkPermission(Activity context, String[] p) {
//        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION
        PermissionChecker.requestPermissions(context, p, PictureConfig.APPLY_STORAGE_PERMISSIONS_CODE);
    }

}
