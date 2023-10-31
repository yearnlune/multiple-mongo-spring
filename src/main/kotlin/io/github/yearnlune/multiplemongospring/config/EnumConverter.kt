package io.github.yearnlune.multiplemongospring.config

import io.github.yearnlune.multiplemongospring.domain.enum.MemberType
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.stereotype.Component

@Component
object EnumConverter {
    val memberTypeConverter: MemberTypeConverter = MemberTypeConverter

    object MemberTypeConverter {
        val readConverter: ReadConverter = ReadConverter
        val writeConverter: WriteConverter = WriteConverter

        @ReadingConverter
        object ReadConverter : Converter<Int, MemberType> {
            override fun convert(source: Int): MemberType = MemberType.fromValue(source)
        }

        @WritingConverter
        object WriteConverter : Converter<MemberType, Int> {
            override fun convert(source: MemberType): Int = source.ordinal
        }
    }
}