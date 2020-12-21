package com.egferreira.pagtest.base.jsonserializer

import com.google.gson.*
import java.lang.reflect.Type
import java.math.BigDecimal

class BigDecimalSerializer : JsonSerializer<BigDecimal>, JsonDeserializer<BigDecimal> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?) = BigDecimal(json?.asString)

    override fun serialize(
        src: BigDecimal?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?) = JsonPrimitive(src)
}