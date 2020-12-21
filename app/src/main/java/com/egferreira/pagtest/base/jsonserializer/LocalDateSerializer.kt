package com.egferreira.pagtest.base.jsonserializer

import com.google.gson.*
import org.joda.time.LocalDate
import java.lang.reflect.Type

class LocalDateSerializer : JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDate {
        return try {
            LocalDate(json.asString)
        } catch (e: IllegalArgumentException) {
            context.deserialize(json, LocalDate::class.java)
        }
    }

    override fun serialize(
        src: LocalDate,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ) = JsonPrimitive(src.toString())
}