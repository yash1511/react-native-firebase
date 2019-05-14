package io.invertase.firebase.app;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;

import static io.invertase.firebase.common.FlutterFirebasePlugin.getChannelName;

public class FlutterFirebaseAppPlugin implements MethodCallHandler {
  public static void registerWith(PluginRegistry.Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), getChannelName("app"));
//    channel.setMethodCallHandler(new FlutterFirebaseAnalyticsPlugin(registrar));
  }

  @Override
  public void onMethodCall(MethodCall methodCall, Result result) {

  }
}
