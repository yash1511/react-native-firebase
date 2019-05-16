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


import com.facebook.react.bridge.*
import io.invertase.firebase.common.ReactNativeFirebaseModule

class ReactNativeFirebaseAnalyticsModule internal constructor(reactContext: ReactApplicationContext) : ReactNativeFirebaseModule(reactContext, SERVICE_NAME) {
  private val module: UniversalFirebaseAnalyticsModule

  companion object {
    private const val SERVICE_NAME = "Analytics"
  }

  init {
    module = UniversalFirebaseAnalyticsModule(reactContext, SERVICE_NAME)
  }

  @ReactMethod
  fun logEvent(name: String, params: ReadableMap?, promise: Promise) {
    module.logEvent(name, Arguments.toBundle(params)).addOnCompleteListener { task ->
      when {
        task.isSuccessful -> promise.resolve(task.result)
        else -> rejectPromiseWithExceptionMap(promise, task.exception)
      }
    }
  }

  @ReactMethod
  fun setAnalyticsCollectionEnabled(enabled: Boolean, promise: Promise) {
    module.setAnalyticsCollectionEnabled(enabled).addOnCompleteListener { task ->
      when {
        task.isSuccessful -> promise.resolve(task.result)
        else -> rejectPromiseWithExceptionMap(promise, task.exception)
      }
    }
  }

  @ReactMethod
  fun setCurrentScreen(
    screenName: String,
    screenClassOverride: String?,
    promise: Promise
  ) {
    module
      .setCurrentScreen(currentActivity, screenName, screenClassOverride)
      .addOnCompleteListener { task ->
        when {
          task.isSuccessful -> promise.resolve(task.result)
          else -> rejectPromiseWithExceptionMap(promise, task.exception)
        }
      }
  }

  @ReactMethod
  fun setMinimumSessionDuration(milliseconds: Double, promise: Promise) {
    module.setMinimumSessionDuration(milliseconds.toLong()).addOnCompleteListener { task ->
      when {
        task.isSuccessful -> promise.resolve(task.result)
        else -> rejectPromiseWithExceptionMap(promise, task.exception)
      }
    }
  }

  @ReactMethod
  fun setSessionTimeoutDuration(milliseconds: Double, promise: Promise) {
    module.setSessionTimeoutDuration(milliseconds.toLong()).addOnCompleteListener { task ->
      when {
        task.isSuccessful -> promise.resolve(task.result)
        else -> rejectPromiseWithExceptionMap(promise, task.exception)
      }
    }
  }

  @ReactMethod
  fun setUserId(id: String, promise: Promise) {
    module.setUserId(id).addOnCompleteListener { task ->
      when {
        task.isSuccessful -> promise.resolve(task.result)
        else -> rejectPromiseWithExceptionMap(promise, task.exception)
      }
    }
  }

  @ReactMethod
  fun setUserProperty(name: String, value: String, promise: Promise) {
    module.setUserProperty(name, value).addOnCompleteListener { task ->
      when {
        task.isSuccessful -> promise.resolve(task.result)
        else -> rejectPromiseWithExceptionMap(promise, task.exception)
      }
    }
  }


  @ReactMethod
  fun setUserProperties(properties: ReadableMap, promise: Promise) {
    module.setUserProperties(Arguments.toBundle(properties)).addOnCompleteListener { task ->
      when {
        task.isSuccessful -> promise.resolve(task.result)
        else -> rejectPromiseWithExceptionMap(promise, task.exception)
      }
    }
  }

  @ReactMethod
  fun resetAnalyticsData(promise: Promise) {
    module.resetAnalyticsData().addOnCompleteListener { task ->
      when {
        task.isSuccessful -> promise.resolve(task.result)
        else -> rejectPromiseWithExceptionMap(promise, task.exception)
      }
    }
  }
}
