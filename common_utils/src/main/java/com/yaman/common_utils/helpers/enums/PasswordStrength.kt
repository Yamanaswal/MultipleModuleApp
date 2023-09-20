package com.yaman.common_utils.helpers.enums

enum class PasswordStrength(val value: String?) {
    LEVEL1("Minimum eight characters, at least one letter and one number. " +
            "Regex:^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"),
    LEVEL2("Minimum eight characters, at least one letter, one number and one special character. " +
            "Regex:^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$"),
    LEVEL3("Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character. " +
            "Regex:^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")
}
