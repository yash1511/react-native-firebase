import '@react-native-firebase/storage';
import '@react-native-firebase/perf';
import '@react-native-firebase/functions';

import { firebase } from '@react-native-firebase/analytics';

const foo = async () => {

  firebase.analytics().logSearch({
    search_term: '123',
    // number_of_nights: '123',
  });
};

foo();
