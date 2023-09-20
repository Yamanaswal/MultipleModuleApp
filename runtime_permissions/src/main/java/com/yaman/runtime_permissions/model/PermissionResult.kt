package com.yaman.runtime_permissions.model

sealed class PermissionResultListener(val requestCode: Int) {
    class PermissionGranted(requestCode: Int) : PermissionResultListener(requestCode)
    class PermissionDenied(
        requestCode: Int,
        val deniedPermissions: List<String>
    ) : PermissionResultListener(requestCode)

    class ShowRational(requestCode: Int) : PermissionResultListener(requestCode)
    class PermissionDeniedPermanently(
        requestCode: Int,
        val permanentlyDeniedPermissions: List<String>
    ) : PermissionResultListener(requestCode)
}