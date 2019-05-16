package io.invertase.firebase.common

/*
 * Copyright (c) 2016-present Invertase Limited & Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this library except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


import android.app.Activity
import android.content.Context
import android.os.Bundle
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.util.*

open class FlutterFirebasePlugin(var registrar: Registrar) {

  val context: Context
    get() = registrar.context()

  val activity: Activity
    get() = registrar.activity()

  val applicationContext: Context
    get() = context.applicationContext

  fun rejectResultWithExceptionMap(result: MethodChannel.Result, exception: Exception?) {
    // TODO handle null?
    val exceptionMap = HashMap<String, String>(4)
    val code = "unknown"
    val message = exception?.message ?: "An unknown error occurred"
    exceptionMap["code"] = code
    exceptionMap["message"] = message
    result.error(code, message, exceptionMap)
  }

  companion object {

    fun getChannelName(module: String): String {
      return "io.invertase.firebase/$module"
    }

    fun createBundleFromMap(map: Map<String, Any?>?): Bundle? {
      if (map == null) {
        return null
      }

      val bundle = Bundle()
      for ((key, value) in map) {
        if (value == null) {
          bundle.putString(key, null)
        } else {
          when (value) {
            is String -> bundle.putString(key, value)
            is Int -> bundle.putInt(key, value)
            is Long -> bundle.putLong(key, value)
            is Double -> bundle.putDouble(key, value)
            is Boolean -> bundle.putBoolean(key, value)
            else -> throw IllegalArgumentException(
              "Unsupported value type: " + value.javaClass.canonicalName!!)
          }
        }
      }
      return bundle
    }
  }

}
