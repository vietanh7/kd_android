package com.example.klikdokter.data.mapper

import io.reactivex.rxjava3.functions.Function
import kotlin.jvm.Throws

abstract class Mapper<FROM, TO> : Function<FROM, TO> {
    @Throws(Exception::class)
    fun apply(fromList: Collection<FROM>): Collection<TO> {
        val result: MutableCollection<TO> = ArrayList()
        for (from in fromList) {
            val item = apply(from)
            result.add(item)
        }
        return result
    }
}
