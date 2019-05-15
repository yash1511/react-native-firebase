import 'package:flutter/services.dart';

// TODO move to common
class FlutterFirebase {

  static final RegExp _alpha = RegExp(r'[^a-zA-Z]');
  static final RegExp _alphaNumeric = RegExp(r'[^a-zA-Z0-9]');
  static final RegExp _alphaNumericUnderscore = RegExp(r'[^a-zA-Z0-9_]');

  static MethodChannel getChannel(String name) {
    String channelName = 'io.invertase.firebase/$name';
    return MethodChannel(channelName);
  }

  // Checks whether a given argument value is null
  void notNull(String argument, dynamic value) {
    if (value == null) throw ArgumentError.notNull(argument);
  }

  // Checks whether a given argument string value is empty
  void notEmpty(String argument, String value) {
    if (value != null && value.isEmpty) throw ArgumentError.value(
      value, argument, 'Must not be an empty string.'
    );
  }

  // Checks whether a string contains only alpha characters
  bool isAlpha(String value) {
    return !(value.contains(_alpha));
  }

  // Checks whether a string contains only alphanumeric characters
  bool isAlphaNumeric(String value) {
    return !(value.contains(_alphaNumeric));
  }

  // Checks whether a string contains only alphanumeric + underscore characters
  bool isAlphaNumericUnderscore(String value) {
    return !(value.contains(_alphaNumericUnderscore));
  }
}
