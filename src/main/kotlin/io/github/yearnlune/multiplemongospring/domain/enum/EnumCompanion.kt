package io.github.yearnlune.multiplemongospring.domain.enum

abstract class EnumCompanion<E, V>(
    private val map: Map<V, E>
) {
    fun fromValue(value: V): E = map[value] ?: throw IllegalArgumentException("NOT FOUND ENUM [$value]")
}