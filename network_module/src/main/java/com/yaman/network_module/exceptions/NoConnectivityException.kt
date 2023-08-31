package com.yaman.network_module.exceptions

import java.io.IOException


class NoConnectivityException : IOException() {

    override val message: String
        get() = "No Internet Connection."
}