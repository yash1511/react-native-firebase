package io.invertase.firebase.analytics;

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

import android.app.Activity;

import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.invertase.firebase.common.FlutterFirebasePlugin;

public class FlutterFirebaseAnalyticsPlugin extends FlutterFirebasePlugin implements MethodCallHandler {
  private static final String SERVICE_NAME = "Analytics";
  private final UniversalFirebaseAnalyticsModule module;

  public FlutterFirebaseAnalyticsPlugin(Registrar registrar) {
    super(registrar);
    module = new UniversalFirebaseAnalyticsModule(registrar.context(), SERVICE_NAME);
  }

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), getChannelName("analytics"));
    channel.setMethodCallHandler(new FlutterFirebaseAnalyticsPlugin(registrar));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    @SuppressWarnings("unchecked")
    Map<String, Object> arguments = (Map<String, Object>) call.arguments;

    switch (call.method) {
      case "logEvent":
        logEvent(arguments, result);
        break;
      case "setAnalyticsCollectionEnabled":
        setAnalyticsCollectionEnabled(arguments, result);
        break;
      case "setCurrentScreen":
        setCurrentScreen(arguments, result);
        break;
      case "setMinimumSessionDuration":
        setMinimumSessionDuration(arguments, result);
        break;
      case "setSessionTimeoutDuration":
        setSessionTimeoutDuration(arguments, result);
        break;
      case "setUserId":
        setUserId(arguments, result);
      case "setUserProperty":
        setUserProperty(arguments, result);
      case "setUserProperties":
        setUserProperties(arguments, result);
      case "resetAnalyticsData":
        resetAnalyticsData(arguments, result);
        break;
      default:
        result.notImplemented();
        break;
    }
  }

  void logEvent(Map<String, Object> arguments, Result result) {
    final String name = (String) arguments.get("name");
    @SuppressWarnings("unchecked") final Map<String, Object> parameters = (Map<String, Object>) arguments.get("parameters");

    module.logEvent(name, createBundleFromMap(parameters)).addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        result.success(null);
      } else {
        rejectResultWithExceptionMap(result, task.getException());
      }
    });
  }

  void setAnalyticsCollectionEnabled(Map<String, Object> arguments, Result result) {
    final Boolean enabled = (Boolean) arguments.get("enabled");

    module.setAnalyticsCollectionEnabled(enabled).addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        result.success(null);
      } else {
        rejectResultWithExceptionMap(result, task.getException());
      }
    });
  }

  void setCurrentScreen(Map<String, Object> arguments, Result result) {
    final Activity activity = getActivity();
    final String screenName = (String) arguments.get("screenName");
    final String screenClassOverride = (String) arguments.get("screenClassOverride");

    module.setCurrentScreen(activity, screenName, screenClassOverride).addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        result.success(null);
      } else {
        rejectResultWithExceptionMap(result, task.getException());
      }
    });
  }

  void setMinimumSessionDuration(Map<String, Object> arguments, Result result) {
    final int milliseconds = (int) arguments.get("milliseconds");

    module.setMinimumSessionDuration((long) milliseconds).addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        result.success(null);
      } else {
        rejectResultWithExceptionMap(result, task.getException());
      }
    });
  }

  void setSessionTimeoutDuration(Map<String, Object> arguments, Result result) {
    final int milliseconds = (int) arguments.get("milliseconds");

    module.setSessionTimeoutDuration((long) milliseconds).addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        result.success(null);
      } else {
        rejectResultWithExceptionMap(result, task.getException());
      }
    });
  }

  void setUserId(Map<String, Object> arguments, Result result) {
    final String id = (String) arguments.get("id");

    module.setUserId(id).addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        result.success(null);
      } else {
        rejectResultWithExceptionMap(result, task.getException());
      }
    });
  }

  void setUserProperty(Map<String, Object> arguments, Result result) {
    final String name = (String) arguments.get("name");
    final String value = (String) arguments.get("value");

    module.setUserProperty(name, value).addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        result.success(null);
      } else {
        rejectResultWithExceptionMap(result, task.getException());
      }
    });
  }

  void setUserProperties(Map<String, Object> arguments, Result result) {
    @SuppressWarnings("unchecked") final Map<String, Object> properties = (Map<String, Object>) arguments.get("properties");

    module.setUserProperties(createBundleFromMap(properties)).addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        result.success(null);
      } else {
        rejectResultWithExceptionMap(result, task.getException());
      }
    });
  }

  void resetAnalyticsData(Map<String, Object> arguments, Result result) {
    module.resetAnalyticsData().addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        result.success(null);
      } else {
        rejectResultWithExceptionMap(result, task.getException());
      }
    });
  }
}
