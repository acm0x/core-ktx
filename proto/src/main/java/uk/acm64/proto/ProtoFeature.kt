package uk.acm64.proto

import android.app.Activity
import kotlin.reflect.KClass

data class ProtoFeature(val name: KClass<out Activity>)
