package com.brightskiesinc.mangm.utils

import androidx.annotation.StringRes
import java.util.*

data class UIMessage(
    val message: String = "",
    val type: UIMessageType,
    val id: Long = UUID.randomUUID().mostSignificantBits
)

sealed class UIMessageType {
    object Success : UIMessageType()
    object Error : UIMessageType()
    class ResourceError(@StringRes val id: Int) : UIMessageType()
}