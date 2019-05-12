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

import android.content.Context;
import android.os.Bundle;

import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class FlutterFirebaseAnalyticsPlugin extends UniversalFirebaseAnalyticsModule implements MethodCallHandler {
  private static final String SERVICE_NAME = "Analytics";
  private final UniversalFirebaseAnalyticsModule module;

  private FlutterFirebaseAnalyticsPlugin(Context context) {
    super(context, SERVICE_NAME);
    module = new UniversalFirebaseAnalyticsModule(context, SERVICE_NAME);
  }

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "io.invertase.firebase/analytics");
    channel.setMethodCallHandler(new FlutterFirebaseAnalyticsPlugin(registrar.context()));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    @SuppressWarnings("unchecked")
    Map<String, Object> arguments = (Map<String, Object>) call.arguments;

    switch (call.method) {
      case "logEvent":
        handleLogEvent(arguments, result);
        break;
      default:
        result.notImplemented();
        break;
    }
  }

  void handleLogEvent(Map<String, Object> arguments, Result result) {
    final String name = (String) arguments.get("name");
    @SuppressWarnings("unchecked")
    final Map<String, Object> parameters = (Map<String, Object>) arguments.get("parameters");

    module.logEvent(name, parameters).addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        result.success(null);
      } else {
        result.error("error", "doop", task.getException());
      }
    });
  }
}
