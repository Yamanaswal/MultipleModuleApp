package com.yaman.runtime_permissions
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import com.yaman.runtime_permissions.model.PermissionResultListener

/**
 * A simple abstract [Fragment] subclass.
 *
 */
abstract class BasePermissionManager : Fragment() {

    //Request Map
    private val rationalRequest = mutableMapOf<Int, Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() &&
            grantResults.all { it == PackageManager.PERMISSION_GRANTED }
        ) {
            onPermissionResult(PermissionResultListener.PermissionGranted(requestCode))
        } else if (permissions.any { shouldShowRequestPermissionRationale(it) }) {
            onPermissionResult(
                PermissionResultListener.PermissionDenied(requestCode,
                    permissions.filterIndexed { index, _ ->
                        grantResults[index] == PackageManager.PERMISSION_DENIED
                    }
                )
            )
        } else {
            onPermissionResult(
                PermissionResultListener.PermissionDeniedPermanently(requestCode,
                    permissions.filterIndexed { index, _ ->
                        grantResults[index] == PackageManager.PERMISSION_DENIED
                    }
                ))
        }
    }

    protected fun requestPermissions(requestId: Int, vararg permissions: String) {

        rationalRequest[requestId]?.let {
            requestPermissions(permissions, requestId)
            rationalRequest.remove(requestId)
            return
        }

        val notGranted = permissions.filter {
            ContextCompat.checkSelfPermission(
                requireActivity(),
                it
            ) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        when {
            notGranted.isEmpty() -> onPermissionResult(PermissionResultListener.PermissionGranted(requestId))
            notGranted.any { shouldShowRequestPermissionRationale(it) } -> {
                rationalRequest[requestId] = true
                onPermissionResult(PermissionResultListener.ShowRational(requestId))
            }
            else -> {
                requestPermissions(notGranted, requestId)
            }
        }
    }

    protected abstract fun onPermissionResult(permissionResult: PermissionResultListener)
}
