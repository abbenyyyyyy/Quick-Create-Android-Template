package com.github.abbenyyyyyy.quickcreateandroidtemplate.utils

object CustomPathUtil {
    fun getFirstUpperCasePath(parentPath: String): String {
        val stringbuilder = StringBuilder()
        for (index in parentPath.indices) {
            stringbuilder.append(
                if (index == 0) {
                    parentPath[index].toUpperCase()
                } else {
                    parentPath[index]
                }
            )
        }
        return stringbuilder.toString()
    }


}