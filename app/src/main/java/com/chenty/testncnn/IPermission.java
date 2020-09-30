package com.chenty.testncnn;

import android.app.Activity;
import android.support.annotation.NonNull;

public interface IPermission {
    void performCodeWithPermission(@NonNull Activity activity, @NonNull String permissionDes, PermissionCallback runnable, @NonNull String... permissions);
}
