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

import android.app.Activity
import android.content.Context
import android.os.Bundle

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.analytics.FirebaseAnalytics

import io.invertase.firebase.common.UniversalFirebaseModule

class UniversalFirebaseAnalyticsModule internal constructor(context: Context, serviceName: String) : UniversalFirebaseModule(context, serviceName) {

  internal fun logEvent(name: String, parameters: Bundle?): Task<Void> {
    return Tasks.call {
      FirebaseAnalytics.getInstance(context).logEvent(name, parameters)
      return@call null
    }
  }

  internal fun setAnalyticsCollectionEnabled(enabled: Boolean): Task<Void> {
    return Tasks.call {
      FirebaseAnalytics.getInstance(context).setAnalyticsCollectionEnabled(enabled)
      return@call null
    }
  }

  internal fun setCurrentScreen(
    currentActivity: Activity?,
    screenName: String,
    screenClassOverride: String?
  ): Task<Void> {
    return Tasks.call {
      if (currentActivity == null) return@call null
      FirebaseAnalytics
        .getInstance(context)
        .setCurrentScreen(currentActivity, screenName, screenClassOverride)
      return@call null
    }
  }

  internal fun setMinimumSessionDuration(milliseconds: Long): Task<Void> {
    return Tasks.call {
      FirebaseAnalytics.getInstance(context).setMinimumSessionDuration(milliseconds)
      return@call null
    }
  }

  internal fun setSessionTimeoutDuration(milliseconds: Long): Task<Void> {
    return Tasks.call {
      FirebaseAnalytics.getInstance(context).setSessionTimeoutDuration(milliseconds)
      return@call null
    }
  }

  internal fun setUserId(id: String?): Task<Void> {
    return Tasks.call {
      FirebaseAnalytics.getInstance(context).setUserId(id)
      return@call null
    }
  }

  internal fun setUserProperty(name: String, value: String?): Task<Void> {
    return Tasks.call {
      FirebaseAnalytics.getInstance(context).setUserProperty(name, value)
      return@call null
    }
  }

  internal fun setUserProperties(properties: Bundle?): Task<Void> {
    return Tasks.call {
      val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

      properties?.keySet()?.forEach { bundleKey ->
        firebaseAnalytics.setUserProperty(
          bundleKey,
          properties[bundleKey] as? String
        )
      }

      return@call null
    }
  }

  internal fun resetAnalyticsData(): Task<Void> {
    return Tasks.call {
      FirebaseAnalytics.getInstance(context).resetAnalyticsData()
      return@call null
    }
  }
}
