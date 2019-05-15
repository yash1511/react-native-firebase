package io.invertase.firebase.analytics

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

import android.util.Log
import com.google.android.gms.tasks.Task
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import io.invertase.firebase.common.FlutterFirebasePlugin

open class FlutterFirebaseAnalyticsPlugin(registrar: Registrar) : FlutterFirebasePlugin(registrar), MethodCallHandler {

  private var module: UniversalFirebaseAnalyticsModule

  init {
    this.module = UniversalFirebaseAnalyticsModule(registrar.context(), SERVICE_NAME)
  }

  companion object {
    var SERVICE_NAME = "Analytics"
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      MethodChannel(registrar.messenger(), getChannelName("analytics"))
        .setMethodCallHandler(FlutterFirebaseAnalyticsPlugin(registrar))
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    var arguments = emptyMap<String, Any>()

    if (call.arguments != null) {
      @Suppress("UNCHECKED_CAST")
      arguments = call.arguments as Map<String, Any>
    }

    when (call.method) {
      "logEvent" -> logEvent(arguments, result)
      "setAnalyticsCollectionEnabled" -> setAnalyticsCollectionEnabled(arguments, result)
      "setCurrentScreen" -> setCurrentScreen(arguments, result)
      "setMinimumSessionDuration" -> setMinimumSessionDuration(arguments, result)
      "setSessionTimeoutDuration" -> setSessionTimeoutDuration(arguments, result)
      "setUserId" -> setUserId(arguments, result)
      "setUserProperty" -> setUserProperty(arguments, result)
      "setUserProperties" -> setUserProperties(arguments, result)
      "resetAnalyticsData" -> resetAnalyticsData(arguments, result)
    }
  }

  private fun logEvent(arguments: Map<String, Any>, result: Result) {
    val name = arguments["name"] as String
    @Suppress("UNCHECKED_CAST") val parameters = arguments["parameters"] as? Map<String, Any>

    module.logEvent(name, createBundleFromMap(parameters)).addOnCompleteListener { task: Task<Void> ->
      when {
        task.isSuccessful -> result.success(task.result)
        else -> rejectResultWithExceptionMap(result, task.exception)
      }
    }
  }

  private fun setAnalyticsCollectionEnabled(arguments: Map<String, Any>, result: Result) {
    val enabled = arguments["enabled"] as Boolean

    module.setAnalyticsCollectionEnabled(enabled).addOnCompleteListener { task: Task<Void> ->
      when {
        task.isSuccessful -> result.success(task.result)
        else -> rejectResultWithExceptionMap(result, task.exception)
      }
    }
  }

  private fun setCurrentScreen(arguments: Map<String, Any>, result: Result) {
    val screenName = arguments["screenName"] as String
    Log.d("Elliot", "wtf")
    val screenClassOverride = arguments["screenClassOverride "] as String

    module.setCurrentScreen(activity, screenName, screenClassOverride).addOnCompleteListener { task: Task<Void> ->
      when {
        task.isSuccessful -> result.success(task.result)
        else -> rejectResultWithExceptionMap(result, task.exception)
      }
    }
  }

  private fun setMinimumSessionDuration(arguments: Map<String, Any>, result: Result) {
    val milliseconds = arguments["milliseconds"] as Long

    module.setMinimumSessionDuration(milliseconds).addOnCompleteListener { task: Task<Void> ->
      when {
        task.isSuccessful -> result.success(task.result)
        else -> rejectResultWithExceptionMap(result, task.exception)
      }
    }
  }

  private fun setSessionTimeoutDuration(arguments: Map<String, Any>, result: Result) {
    val milliseconds = arguments["milliseconds"] as Long

    module.setSessionTimeoutDuration(milliseconds).addOnCompleteListener { task: Task<Void> ->
      when {
        task.isSuccessful -> result.success(task.result)
        else -> rejectResultWithExceptionMap(result, task.exception)
      }
    }
  }

  private fun setUserId(arguments: Map<String, Any>, result: Result) {
    val id = arguments["id"] as String

    module.setUserId(id).addOnCompleteListener { task: Task<Void> ->
      when {
        task.isSuccessful -> result.success(task.result)
        else -> rejectResultWithExceptionMap(result, task.exception)
      }
    }
  }

  private fun setUserProperty(arguments: Map<String, Any>, result: Result) {
    val name = arguments["name"] as String
    val value = arguments["value"] as String

    module.setUserProperty(name, value).addOnCompleteListener { task: Task<Void> ->
      when {
        task.isSuccessful -> result.success(task.result)
        else -> rejectResultWithExceptionMap(result, task.exception)
      }
    }
  }

  private fun setUserProperties(arguments: Map<String, Any>, result: Result) {
    @Suppress("UNCHECKED_CAST") val properties = arguments["properties"] as Map<String, Any>

    module.setUserProperties(createBundleFromMap(properties)).addOnCompleteListener { task: Task<Void> ->
      when {
        task.isSuccessful -> result.success(task.result)
        else -> rejectResultWithExceptionMap(result, task.exception)
      }
    }
  }

  private fun resetAnalyticsData(@Suppress("UNUSED_PARAMETER") arguments: Map<String, Any>, result: Result) {
    module.resetAnalyticsData().addOnCompleteListener { task: Task<Void> ->
      when {
        task.isSuccessful -> result.success(task.result)
        else -> rejectResultWithExceptionMap(result, task.exception)
      }
    }
  }
}
