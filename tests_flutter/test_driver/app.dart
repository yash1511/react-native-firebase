import 'dart:async';

import 'package:flutter_driver/driver_extension.dart';
import 'package:flutter_firebase_analytics/analytics.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  final Completer<String> completer = Completer<String>();
  enableFlutterDriverExtension(handler: (_) => completer.future);
  tearDownAll(() => completer.complete(null));

  group('Analytics', () {
    group('logEvent', () {
      test('log an event without parameters', () async {
        await Analytics().logEvent('invertase_event', null);
      });

      test('log an event with parameters', () async {
        await Analytics().logEvent('invertase_event', {
          'boolean': true,
          'number': 1,
          'string': 'string',
        });
      });

      test('errors of name not string', () async {
        try {
          await Analytics().logEvent(null);
          throw new Exception('Did not throw');
        } catch (e) {
          // pass
        }
      });

      test('errors on using a reserved name', () async {
        try {
          await Analytics().logEvent('session_start');
          throw new Exception('Did not throw');
        } catch (e) {
          expect(e.message, contains('Event name is reserved'));
        }
      });

      test('errors if name not alphanumeric', () async {
        try {
          await Analytics().logEvent('!@£%^&*');
          throw new Exception('Did not throw');
        } catch (e) {
          expect(e.message,
              contains('Names should contain 1 to 32 alphanumeric characters'));
        }
      });

      test('errors if more than 25 params provided', () async {
        try {
          var params = Map<String, dynamic>();
          for(var i = 0 ; i < 26; i++) {
            params["index-$i"] = 'foobar';
          }
          await Analytics().logEvent('foo', params);
          throw new Exception('Did not throw');
        } catch (e) {
          expect(e.message,
              contains('Must contain no more than 25 key/value pairs.'));
        }
      });
    });

    group('setAnalyticsCollectionEnabled', () {
      test('true', () async {
        await Analytics().setAnalyticsCollectionEnabled(true);
      });

      test('false', () async {
        await Analytics().setAnalyticsCollectionEnabled(false);
      });
    });

    group('resetAnalyticsData', () {
      test('calls native fn without error', () async {
        await Analytics().resetAnalyticsData();
      });
    });

    group('setCurrentScreen', () {
      test('screenName only', () async {
        await Analytics().setCurrentScreen('invertase screen');
      });

      test('screenName with screenClassOverride', () async {
        await Analytics().setCurrentScreen('invertase screen', 'invertase class override');
      });

      test('errors if screenName not a string', () async {
        try {
          await Analytics().setCurrentScreen(null);
        } catch (e) {
          expect(e.message, contains('Must not be null'));
        }
      });
    });

    group('setMinimumSessionDuration', () {
      test('default duration', () async {
        await Analytics().setMinimumSessionDuration();
      });

      test('custom duration', () async {
        await Analytics().setMinimumSessionDuration(13371337);
      });
    });

    group('setSessionTimeoutDuration', () {
      test('default duration', () async {
        await Analytics().setSessionTimeoutDuration();
      });

      test('custom duration', () async {
        await Analytics().setSessionTimeoutDuration(13371337);
      });
    });

    group('setUserId', () {
      test('allows a null values to be set', () async {
        await Analytics().setUserId(null);
      });

      test('errors on empty value', () async {
        try {
          await Analytics().setUserId('');
        } catch (e) {
          expect(e.message, contains('Must not be an empty string.'));
        }
      });

      test('calls fn without error', () async {
        await Analytics().setUserId('flutter');
      });
    });

    group('setUserProperty', () {
      test('allows a null values to be set', () async {
        await Analytics().setUserProperty('invertase', null);
      });

      test('accepts string values', () async {
        await Analytics().setUserProperty('invertase', 'flutter');
      });

      test('errors on empty string value', () async {
        try {
          await Analytics().setUserProperty('invertase', '');
        } catch (e) {
          expect(e.message, contains('Must not be an empty string.'));
        }
      });

      test('errors if name is longer than 24 characters', () async {
        try {
          await Analytics().setUserProperty('invertaseinvertaseinvertaseinvertaseinvertaseinvertase', null);
        } catch (e) {
          expect(e.message, contains('Must contain 1 to 24 alphanumeric characters.'));
        }
      });

      test('errors if name contains none alphanumericunderscore charcters', () async {
        try {
          await Analytics().setUserProperty('invertase!^', null);
        } catch (e) {
          expect(e.message, contains('Must contain 1 to 24 alphanumeric characters.'));
        }
      });
    });

    group('setUserProperties', () {
      test('errors if arg is not an Map', () async {
        try {
          await Analytics().setUserProperties(null);
        } catch (e) {
          expect(e.message, contains('Must not be null'));
        }
      });

      test('allows null values to be set', () async {
        await Analytics().setUserProperties({
          'foo': null,
        });
      });

      test('allows string values to be set', () async {
        await Analytics().setUserProperties({
          'foo': 'bar',
        });
      });
    });
  });
}
