package com.spacex.rockets.data.net.serializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.spacex.rockets.data.utils.deserializeDate
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

class DateDeserializer : JsonDeserializer<Date> {

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, typeOF: Type,
                             context: JsonDeserializationContext): Date? {

        if (jsonElement.isJsonNull && jsonElement.asString.isEmpty()) return null

        val date = deserializeDate(jsonElement.asString)
        if (date != null) {
            return date
        } else {
            Timber.e("Unparseable date: \"" + jsonElement.asString)
            return null
        }
    }
}
