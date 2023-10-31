package io.github.yearnlune.multiplemongospring.domain.enum

enum class MemberType {
    UNKNOWN,
    GUEST,
    USER,
    ADMIN;

    companion object : EnumCompanion<MemberType, Int>(
        MemberType.values().associateBy(MemberType::ordinal)
    )
}