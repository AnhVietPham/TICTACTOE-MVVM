package com.avp.mvvm_databinding.utils

import java.lang.StringBuilder

class StringUtility {
    companion object {
        fun stringFromNumbers(vararg numbers: Int): String {
            val sNumbers = StringBuilder()
            for (number in numbers) {
                sNumbers.append(number)
            }
            return sNumbers.toString()
        }

        fun isNullOrEmpty(value: String): Boolean {
            return value.isEmpty()
        }
    }
}