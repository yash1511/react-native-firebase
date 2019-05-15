import 'dart:async';

import 'package:flutter/services.dart';
// TODO import it as sub dep?
import 'package:flutter_firebase_app/FlutterFirebase.dart';

const List<String> _reservedEventNames = <String>[
  'app_clear_data',
  'app_uninstall',
  'app_update',
  'error',
  'first_open',
  'in_app_purchase',
  'notification_dismiss',
  'notification_foreground',
  'notification_open',
  'notification_receive',
  'os_update',
  'session_start',
  'user_engagement',
];

class Analytics extends FlutterFirebase {
  static MethodChannel _channel = FlutterFirebase.getChannel('analytics');

  Future<void> logEvent(String name, [Map<String, dynamic> parameters]) async {
    notNull('name', name);
    notEmpty('name', name);

    // Cannot be reserved event name
    if (_reservedEventNames.contains(name)) {
      throw ArgumentError.value(
        name, 'name', 'Event name is reserved and cannot be used');
    }

    // name length validation / alphanumeric check
    if (name.length > 32 || !isAlphaNumericUnderscore(name)) {
      throw ArgumentError.value(name, 'name',
        'Event name is invalid. Names should contain 1 to 32 alphanumeric characters or underscores.');
    }

    if (parameters != null && parameters.length > 25) {
      throw ArgumentError.value(name, 'name',
          'Event parameters are invalid. Must contain no more than 25 key/value pairs.');
    }

    await _channel.invokeMethod('logEvent', <String, dynamic>{
      'name': name,
      'parameters': parameters,
    });
  }

  Future<void> setAnalyticsCollectionEnabled(bool enabled) async {
    notNull('enabled', enabled);
    await _channel.invokeMethod('setAnalyticsCollectionEnabled', {
      'enabled': enabled,
    });
  }

  Future<void> setCurrentScreen(String screenName,
    [String screenClassOverride = 'Flutter']) async {
    notNull('screenName', screenName);
    notEmpty('screenName', screenName);
    await _channel.invokeMethod('setCurrentScreen', <String, String>{
      'screenName': screenName,
      'screenClassOverride': screenClassOverride,
    });
  }

  Future<void> setMinimumSessionDuration([int milliseconds = 10000]) async {
    await _channel.invokeMethod('setMinimumSessionDuration', <String, int>{
      'milliseconds': milliseconds,
    });
  }

  Future<void> setSessionTimeoutDuration([int milliseconds = 10000]) async {
    await _channel.invokeMethod('setSessionTimeoutDuration', <String, int>{
      'milliseconds': milliseconds,
    });
  }

  Future<void> setUserId(String id) async {
    notEmpty('id', id);
    await _channel.invokeMethod('setUserId', <String, String>{
      'id': id,
    });
  }

  Future<void> setUserProperty(String name, String value) async {
    notNull('name', name);
    notEmpty('name', name);
    notEmpty('value', value);
    if (name.length > 24 || !isAlphaNumericUnderscore(name)) {
      throw ArgumentError.value(
        name, 'name', 'Must contain 1 to 24 alphanumeric characters.');
    }

    await _channel.invokeMethod('setUserProperty', <String, String>{
      'name': name,
      'value': value,
    });
  }

  Future<void> setUserProperties(Map<String, String> properties) async {
    notNull('properties', properties);

    await _channel.invokeMethod('setUserProperties', {
      'properties': properties,
    });
  }

  Future<void> resetAnalyticsData() async {
    await _channel.invokeMethod('resetAnalyticsData');
  }
}
