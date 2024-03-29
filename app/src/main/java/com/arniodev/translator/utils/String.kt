package com.arniodev.translator.utils

fun String.getGoogleTK(): String {

    val text = this

    fun RL(a1: Long, b: String): Long {
        var a = a1
        var t = 'a'
        val Yb = '+'
        var c = 0
        while (c < b.length - 2) {
            val d = b[c + 2]
            val numD = if (d >= t) d.code - 87 else Character.getNumericValue(d)
            val numC = b[c + 1]
            val shiftValue = if (numC == Yb) a ushr numD else a shl numD
            a = if (b[c] == Yb) a + shiftValue and 0xFFFFFFFF else a xor shiftValue
            c += 3
        }
        return a
    }

    fun transformQuery(query: String): IntArray {
        val e = IntArray(3 * query.length)
        var f = 0
        var g = 0
        while (g < query.length) {
            val m = query[g].code
            when {
                m < 128 -> e[f++] = m
                m < 2048 -> {
                    e[f++] = m shr 6 or 0xC0
                    e[f++] = m and 0x3F or 0x80
                }
                0xD800 == (m and 0xFC00) && g + 1 < query.length && 0xDC00 == (query[g + 1].code and 0xFC00) -> {
                    var m2 = (1 shl 16) + ((m and 0x03FF) shl 10) + (query[++g].code and 0x03FF)
                    e[f++] = m2 shr 18 or 0xF0
                    e[f++] = m2 shr 12 and 0x3F or 0x80
                    e[f++] = m2 and 0x3F or 0x80
                }
                else -> {
                    e[f++] = m shr 12 or 0xE0
                    e[f++] = m shr 6 and 0x3F or 0x80
                    e[f++] = m and 0x3F or 0x80
                }
            }
            g++
        }
        return e.copyOfRange(0, f)
    }

    var b = 406644.toLong()
    var b1 = 3293161072
    val jd = "."
    val sb = "+-a^+6"
    val Zb = "+-3^+b+-f"
    var e = transformQuery(text)
    var aa = b
    for (f in e.indices) {
        aa += e[f]
        aa = RL(aa, sb)
    }
    aa = RL(aa, Zb)
    aa = aa xor (b1 or 0)
    if (aa < 0) {
        aa = ((aa and 2147483647) + 2147483648)
    }
    aa %= 1_000_000
    return aa.toString() + jd + (aa xor b)
}

// 计算Google Translate API中的tk（即token，亦称TKK）
// Converted from JavaScript