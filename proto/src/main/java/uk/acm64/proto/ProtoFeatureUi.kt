package uk.acm64.proto

import android.app.Activity
import kotlin.reflect.KClass

data class ProtoFeatureUi(val className: KClass<out Activity>)
