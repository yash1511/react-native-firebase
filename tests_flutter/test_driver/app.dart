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
      test('errors of name not string', () async {
        try {
          await Analytics().logEvent(null, null);
          throw new Exception('Did not throw');
        } catch (e) {
          // pass
        }
      });

      test('errors on using a reserved name', () async {
        try {
          await Analytics().logEvent('session_start', null);
          throw new Exception('Did not throw');
        } catch (e) {
          expect(e.message, contains('Event name is reserved'));
        }
      });

      test('errors if name not alphanumeric', () async {
        try {
          await Analytics().logEvent('!@£%^&*', null);
          throw new Exception('Did not throw');
        } catch (e) {
          expect(e.message,
              contains('Names should contain 1 to 32 alphanumeric characters'));
        }
      });

      // TODO why?
//      test('errors if more than 25 params provided', () async {
//
//      });

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
  });
}
