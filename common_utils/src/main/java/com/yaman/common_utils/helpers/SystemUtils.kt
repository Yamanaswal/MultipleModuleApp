package com.yaman.common_utils.helpers

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Service
import android.content.Context
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.IBinder
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.yaman.common_utils.helpers.enums.DeviceType
import com.yaman.common_utils.logging.Logger
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
// Root For System Utils
 **/
object SystemUtils {

    /**  Get Device Id  **/
    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String? {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    /**  Device Type  (Mobile or Tablet) **/
    fun getDeviceType(context: Context): DeviceType {
        return if ((context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE) {
            DeviceType.TABLET
        } else {
            DeviceType.MOBILE
        }
    }

    /**  Show Date and Time Interval (e.g. minute,seconds,months and year ago.) **/
    fun getDateDifferenceFromCurrentDate(date: Date): String? {
        val now: Calendar = Calendar.getInstance()
        val then: Calendar = Calendar.getInstance()
        now.time = Date()
        then.time = date

        // Get the represented date in milliseconds
        val nowMs: Long = now.timeInMillis
        val thenMs: Long = then.timeInMillis

        // Calculate difference in milliseconds
        val diff = nowMs - thenMs

        // Calculate difference in seconds
        val diffMinutes = diff / (60 * 1000)
        val diffHours = diff / (60 * 60 * 1000)
        val diffDays = diff / (24 * 60 * 60 * 1000)

        //show date timeline.
        return if (diffMinutes < 60) {
            if (diffMinutes == 1L) "$diffMinutes minute ago" else "$diffMinutes minutes ago"
        } else if (diffHours < 24) {
            if (diffHours == 1L) "$diffHours hour ago" else "$diffHours hours ago"
        } else if (diffDays < 30) {
            if (diffDays == 1L) "$diffDays day ago" else "$diffDays days ago"
        } else if (diffDays / 30 in 2..11) {
            java.lang.String.format(Locale.getDefault(), "%d months ago", diffDays / 30)
        } else if (diffDays / 365 > 1) {
            java.lang.String.format(Locale.getDefault(), "%d years ago", diffDays / 365)
        } else {
            "a long time ago.."
        }
    }

    /**  Close Keyboard (binding.getRoot().getWindowToken() => windowToken) **/
    fun closeKeyboard(context: Context, windowToken: IBinder) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

    /**  Open Keyboard (requireView()) **/
    fun openKeyboard(context: Context, view: View) {
        val inputMethodManager = context.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInputFromInputMethod(view.windowToken, 0)
    }


    /**  TimeStamp to Date (String) Conversion. **/
    fun convertTimestampToDate(
        dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        timeStamp: String,
        datePattern: String
    ): String? {
        try {
            val format = SimpleDateFormat(dateFormat, Locale.getDefault())
            val defaultTimeZone = TimeZone.getDefault()
            val strDefaultTimeZone = defaultTimeZone.getDisplayName(false, TimeZone.SHORT)
            format.timeZone = TimeZone.getTimeZone(strDefaultTimeZone)
            val date = format.parse(timeStamp)
            return SimpleDateFormat(datePattern, Locale.getDefault()).format(date!!)
        } catch (e: Exception) {
            Log.e("Error: ", "convertTimestampToDate: " + e.message)
        }
        return null
    }

    /**  24 Hours to 12 Hours - Time Conversion **/
    fun convertTime24To12(date: String): String {
        try {
            val input = SimpleDateFormat("hh:mm", Locale.getDefault())
            val data = input.parse(date)
            val output = SimpleDateFormat("hh:mm a", Locale.getDefault())
            return data?.let { it1 -> output.format(it1) }.toString()
        } catch (e: Exception) {
            Logger.e("convertTimeFormat Exception: : ", e.localizedMessage)
        }
        return ""
    }

    /**  12 Hours to 24 Hours - Time Conversion **/
    fun convertTime12To24(date: String): String {
        try {
            val input = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val data = input.parse(date)
            val output = SimpleDateFormat("HH:mm", Locale.getDefault())
            return data?.let { it1 -> output.format(it1) }.toString()
        } catch (e: Exception) {
            Logger.e("convertTimeFormat Exception: : ", e.localizedMessage)
        }
        return ""
    }


    /**  Date String to Date Format - Conversion.
     *  // ex- 2012-12-27 to 27 Dec 2012
     * **/
    fun convertDateString(
        dateString: String,
        patternDate: String = "yyyy-MM-dd",
        formattedDate: String = "dd MMMM yyyy"
    ): String? {
        try {
            Log.e("TAG", "convertDateString: $dateString")
            val date = SimpleDateFormat(patternDate, Locale.getDefault()).parse(dateString)
            return SimpleDateFormat(formattedDate, Locale.getDefault()).format(
                date ?: Calendar.getInstance().timeInMillis
            )
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    /**  Date to TimeStamp (String) Conversion. **/
    fun convertDateToTimestamp(date: String): String {
        val timestamp = date.split("-".toRegex()).toTypedArray()
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar.timeZone = TimeZone.getTimeZone("UTC")
        calendar[timestamp[0].toInt(), timestamp[1].toInt() - 1] = timestamp[2].toInt()
        return (calendar.timeInMillis / 1000).toString()
    }


    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private fun showSettingsDialog(
        context: Context,
        settingDialogData: SettingDialogData,
        alertDialogInterfaces: AlertDialogInterfaces
    ) {
        Log.d("TAG", "showSettingsDialog: Started")
        val builder = AlertDialog.Builder(context)
        builder.setTitle(settingDialogData.title + " (Need Permissions)")
        builder.setMessage(settingDialogData.message + " needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton("Goto settings") { dialog: DialogInterface, which: Int ->
            alertDialogInterfaces.onAlertClick(true)
            dialog.cancel()
        }
        builder.setNegativeButton("Cancel") { dialog: DialogInterface, which: Int ->
            alertDialogInterfaces.onAlertClick(false)
            dialog.cancel()
        }
        builder.show()
    }

    /**
     * Add Hour and Minutes to Given Time. (12:15 AM + 6 Hour & 10 minutes)
     */
    fun addTimeHourMinuteTo12HoursTime(twelveHoursTime: String, hour: Int, min: Int): String {

        try {
            val displayFormat = SimpleDateFormat("HH:mm")
            val parseFormat = SimpleDateFormat("hh:mm a")
            val date = parseFormat.parse(twelveHoursTime)
            println("24 hours: " + displayFormat.format(date))

            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar.add(Calendar.HOUR_OF_DAY, hour)
            calendar.add(Calendar.MINUTE, min)

            val sdfnew = SimpleDateFormat("HH:mm")
            println(sdfnew.format(calendar.time))
            val datestr = sdfnew.format(calendar.time)


            val sdfnewtwo = SimpleDateFormat("hh:mm").parse(datestr)
            println(sdfnewtwo)

            val sdfnewthree = SimpleDateFormat("hh:mm a")
            println(sdfnewthree.format(sdfnewtwo))

            return sdfnewthree.format(sdfnewtwo)

        } catch (e: Exception) {
            Log.e("TAG", "timeConversion12to24: $e")
        }
        return ""
    }

    /**
    Get Device Screen Properties
     * */
    fun getDeviceProperties(context: Context): DisplayMetrics {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics
    }

    /**
    Get Device Screen Width
     * */
    fun getScreenWidth(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    /**
    Get Device Screen Height
     * */
    fun getScreenHeight(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    interface AlertDialogInterfaces {
        fun onAlertClick(alertStatus: Boolean)
    }

    data class SettingDialogData(val title: String = "", val message: String = "")
}


