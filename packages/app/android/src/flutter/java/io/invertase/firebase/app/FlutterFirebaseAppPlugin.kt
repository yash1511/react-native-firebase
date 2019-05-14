package io.invertase.firebase.app

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterFirebaseAppPlugin : MethodCallHandler {

  override fun onMethodCall(methodCall: MethodCall, result: Result) {

  }

  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      MethodChannel(registrar.messenger(), "analytics")
      // TODO set channel
    }
  }
}
