package com.egferreira.pagtest.extensions

fun <T, V> MutableMap<T, V>.putIfNotAlreadyThere(key: T, value: V) {
    if (get(key) == null) this[key] = value
}