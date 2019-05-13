package io.invertase.firebase.common;

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
import android.content.Context;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class FlutterFirebasePlugin {
  public Registrar registrar;

  public static String getChannelName(String module) {
    return "io.invertase.firebase/" + module;
  }

  public FlutterFirebasePlugin(Registrar registrar) {
    this.registrar = registrar;
  }

  public Context getContext() {
    return registrar.context();
  }

  public Activity getActivity() {
    return registrar.activity();
  }

  public Context getApplicationContext() {
    return getContext().getApplicationContext();
  }

  public void rejectResultWithExceptionMap(MethodChannel.Result result, Exception exception) {
    // TODO handle null?
    Map<String, String> exceptionMap = new HashMap<>(4);
    String code = "unknown";
    String message = exception.getMessage();
    exceptionMap.put("code", code);
    exceptionMap.put("nativeErrorCode", code); // TODO Needed?
    exceptionMap.put("message", message);
    exceptionMap.put("nativeErrorMessage", message); // TODO Needed?
    result.error(code, message, exceptionMap);
  }

  public static Bundle createBundleFromMap(Map<String, Object> map) {
    if (map == null) {
      return null;
    }

    Bundle bundle = new Bundle();
    for (Map.Entry<String, Object> jsonParam : map.entrySet()) {
      final Object value = jsonParam.getValue();
      final String key = jsonParam.getKey();
      if (value instanceof String) {
        bundle.putString(key, (String) value);
      } else if (value instanceof Integer) {
        bundle.putInt(key, (Integer) value);
      } else if (value instanceof Long) {
        bundle.putLong(key, (Long) value);
      } else if (value instanceof Double) {
        bundle.putDouble(key, (Double) value);
      } else if (value instanceof Boolean) {
        bundle.putBoolean(key, (Boolean) value);
      } else {
        throw new IllegalArgumentException(
          "Unsupported value type: " + value.getClass().getCanonicalName());
      }
    }
    return bundle;
  }

}
