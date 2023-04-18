package com.goofy.sse.common.utis

class RandomUtils {
    companion object {
        val RANDOM_CHARSETS = ('a'..'z') + ('A'..'Z')

        fun getRandomString(range: Int): String {
            return List(range) { RANDOM_CHARSETS }.joinToString("")
        }
    }
}
