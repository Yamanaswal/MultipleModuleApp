package com.yaman.common_utils.validations

import android.util.Patterns
import com.yaman.common_utils.helpers.enums.PasswordStrength
import java.util.regex.Pattern


/**
 * Basic Form validations
 * */
object FormValidation {

    /**
     * Validate India Mobile Number - 10 digits
     * */
    @JvmStatic
    fun validateIndianMobileNumber(mobileNumber: String?): Boolean {

        if (mobileNumber.isNullOrEmpty()) {
            return false
        } else if (mobileNumber.length != 10) {
            return false
        }

        return true
    }

    /**
     * Name Validation - only alphabets
     * **/
    @JvmStatic
    fun validateName(name: String?): Boolean {

        if (name.isNullOrEmpty()) {
            return false
        }
        else if(!name.matches("^[A-Za-z]+$".toRegex())){
            return false
        }

        return true
    }


    /**
     * Email Validation
     * **/
    @JvmStatic
    fun validateEmail(email: String?): Boolean {

        if (email.isNullOrEmpty()) {
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }

        return true
    }

    /**
     * India PinCode Validation
     * **/
    @JvmStatic
    fun validateIndianPinCode(pinCode: String?): Boolean {

        // Regex to check valid pin code of India.
        val regex = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$"


        if (pinCode.isNullOrEmpty()) {
            return false
        } else if (!Pattern.compile(regex).matcher(pinCode).matches()) {
            return false
        }

        return true
    }


    /**
     * Password Validation
     * **/
    @JvmStatic
    fun validatePasswordStrong(password: String?,passwordStrength: PasswordStrength): Boolean {

        // Regex to check valid pin code of India.
        val regex = passwordStrength.value?.split("Regex:")?.get(1)!!

        if (password.isNullOrEmpty()) {
            return false
        } else if (!Pattern.compile(regex).matcher(password).matches()) {
            return false
        }

        return true
    }



}
