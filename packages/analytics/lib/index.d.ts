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

import {
  ReactNativeFirebaseModule,
  ReactNativeFirebaseNamespace,
  ReactNativeFirebaseModuleAndStatics,
} from '@react-native-firebase/app-types';

/**
 * Firebase Analytics package for React Native.
 *
 * #### Example 1
 *
 * Access the firebase export from the `analytics` package:
 *
 * ```js
 * import { firebase } from '@react-native-firebase/analytics';
 *
 * // firebase.analytics().X
 * ```
 *
 * #### Example 2
 *
 * Using the default export from the `analytics` package:
 *
 * ```js
 * import analytics from '@react-native-firebase/analytics';
 *
 * // analytics().X
 * ```
 *
 * #### Example 3
 *
 * Using the default export from the `app` package:
 *
 * ```js
 * import firebase from '@react-native-firebase/app';
 * import '@react-native-firebase/analytics';
 *
 * // firebase.analytics().X
 * ```
 *
 * @firebase analytics
 */
export namespace Analytics {
  export interface Statics {}

  /**
   * The Firebase Analytics service interface.
   *
   * > This module is available for the default app only.
   *
   * #### Example
   *
   * Get the Analytics service for the default app:
   *
   * ```js
   * const defaultAppAnalytics = firebase.analytics();
   * ```
   */
  export class Module extends ReactNativeFirebaseModule {
    /**
     * Log a custom event with optional params.
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logEvent('product_view', {
     *   id: '1234',
     * });
     * ```
     *
     * > 100 characters is the maximum length for param key names.
     *
     * @param name Event name must not conflict with any Reserved Events.
     * @param params Parameters to be sent and displayed with the event.
     */
    logEvent(name: string, params: { [key: string]: string }): Promise<void>;

    /**
     * If true, allows the device to collect analytical data and send it to Firebase.
     * Useful for GDPR.
     *
     * #### Example
     *
     * ```js
     * // Disable collection
     * await firebase.analytics().setAnalyticsCollectionEnabled(false);
     * ```
     *
     * @param enabled A boolean value representing whether Analytics collection is enabled or disabled. Analytics collection is enabled by default.
     */
    setAnalyticsCollectionEnabled(enabled: boolean): Promise<void>;

    /**
     * Sets the current screen name.
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().setCurrentScreen('ProductScreen', 'ProductScreen');
     * ```
     *
     * > Whilst screenClassOverride is optional, it is recommended it is
     * always sent as your current class name. For example on Android it will always
     * show as 'MainActivity' if you do not specify it.
     *
     * @param screenName A screen name, e.g. Product.
     * @param screenClassOverride On Android, React Native runs in a single activity called
     * 'MainActivity'. Setting this parameter overrides the default name shown on logs.
     */
    setCurrentScreen(screenName: string, screenClassOverride?: string): Promise<void>;

    /**
     * Sets the minimum engagement time required before starting a session.
     *
     * #### Example
     *
     * ```js
     * // 20 seconds
     * await firebase.analytics().setMinimumSessionDuration(20000);
     * ```
     *
     * @param milliseconds The default value is 10000 (10 seconds).
     */
    setMinimumSessionDuration(milliseconds: number): Promise<void>;

    /**
     * Sets the duration of inactivity that terminates the current session.
     *
     * #### Example
     *
     * ```js
     * // 20 minutes
     * await firebase.analytics().setMinimumSessionDuration(900000);
     * ```
     *
     * @param milliseconds The default value is 1800000 (30 minutes).
     */
    setSessionTimeoutDuration(milliseconds: number): Promise<void>;

    /**
     * Gives a user a unique identification.
     *
     * #### Example
     *
     * ```js
     * // Set User
     * await firebase.analytics().setUserId('123456789');
     * // Remove User
     * await firebase.analytics().setUserId(null);
     * ```
     *
     * @param id Set to null to remove a previously assigned id from analytics events
     */
    setUserId(id: string | null): Promise<void>;

    /**
     * Sets a key/value pair of data on the current user.
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().setUserProperty('account_type', 'gold');
     * ```
     *
     * @param name A user property identifier.
     * @param value Set to null to remove a previously assigned id from analytics events.
     */
    setUserProperty(name: string, value: string | null): Promise<void>;

    /**
     * Sets multiple key/value pair of data on the current user.
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().setUserProperties({
     *   account_type: 'gold',
     *   account_name: 'Gold Badge',
     * });
     * ```
     *
     * @react-native-firebase
     * @param properties Set a property value to null to remove it.
     */
    setUserProperties(properties: { [key: string]: string | null }): Promise<void>;

    /**
     * Clears all analytics data for this instance from the device and resets the app instance ID.
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().resetAnalyticsData();
     * ```
     */
    resetAnalyticsData(): Promise<void>;

    /**
     * Add Payment Info event. This event signifies that a user has submitted their payment information to your app.
     *
     * Via: `add_payment_info`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logAddPaymentInfo();
     * ```
     */
    logAddPaymentInfo(): Promise<void>;

    /**
     * E-Commerce Add To Cart event. This event signifies that an item was added to a cart for purchase.
     * Add this event to a funnel with {@link analytics#logEcommercePurchase} to gauge the effectiveness of your checkout process.
     *
     * If you supply the VALUE parameter, you must also supply the CURRENCY parameter so that revenue metrics can be computed accurately.
     *
     * Via: `add_to_cart`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logAddToCart({
     *
     * });
     * ```
     *
     * @param params
     */
    logAddToCart(params: Object<String, String | Number>): Promise<void>;

    /**
     * E-Commerce Add To Wishlist event. This event signifies that an item was added to a wishlist.
     * Use this event to identify popular gift items in your app.
     *
     * If you supply the `value` parameter, you must also supply the `currency` parameter so that revenue metrics can be computed accurately.
     *
     * Via: `add_to_wishlist`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logAddToWishlist({
     *
     * });
     * ```
     *
     * @param params
     */
    logAddToWishlist(params: Object<String, String | Number>): Promise<void>;

    /**
     * App Open event. By logging this event when an App is moved to the foreground, developers can
     * understand how often users leave and return during the course of a Session. Although Sessions
     * are automatically reported, this event can provide further clarification around the continuous
     * engagement of app-users.
     *
     * Via: `app_open`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logAppOpen();
     * ```
     */
    logAppOpen(): Promise<void>;

    /**
     * E-Commerce Begin Checkout event. This event signifies that a user has begun the process of
     * checking out. Add this event to a funnel with your {@link analytics#logEcommercePurchase} event to gauge the
     * effectiveness of your checkout process.
     *
     * If you supply the `value` parameter, you must also supply the `currency` parameter so that revenue metrics can be computed accurately.
     *
     * Via: `begin_checkout`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logBeginCheckout({
     *
     * });
     * ```
     *
     * @param params
     */
    logBeginCheckout(params: Object<String, String | Number>): Promise<void>;

    /**
     * Log this event to supply the referral details of a re-engagement campaign.
     *
     * Via: `campaign_details`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logCampaignDetails({
     *
     * });
     * ```
     *
     * @param params
     */
    logCampaignDetails(params: Object<String, String | Number>): Promise<void>;

    /**
     * Earn Virtual Currency event. This event tracks the awarding of virtual currency in your app. Log this along with
     * {@link analytics.logSpendVirtualCurrency} to better understand your virtual economy.
     *
     * Via: `earn_virtual_currency`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logEarnVirtualCurrency({
     *   virtual_currency_name: 'coins',
     *   value: 100,
     * });
     * ```
     *
     * @param params
     */
    logEarnVirtualCurrency(params: Object<String, String | Number>): Promise<void>;

    /**
     * E-Commerce Purchase event. This event signifies that an item was purchased by a user. This is
     * different from the in-app purchase event, which is reported automatically for Google Play-based apps.
     *
     * If you supply the `value` parameter, you must also supply the `currency` parameter so that revenue metrics can be computed accurately.
     *
     * Via: `ecommerce_purchase`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logEcommercePurchase({
     *
     * });
     * ```
     *
     * @param params
     */
    logEcommercePurchase(params: Object<String, String | Number>): Promise<void>;

    /**
     * Generate Lead event. Log this event when a lead has been generated in the app to understand
     * the efficacy of your install and re-engagement campaigns.
     *
     * If you supply the `value` parameter, you must also supply the `currency` parameter so that revenue metrics can be computed accurately.
     *
     * Via: `generate_lead`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logGenerateLead({
     *
     * });
     * ```
     *
     * @param params
     */
    logGenerateLead(params?: Object<String, String | Number>): Promise<void>;

    /**
     * Join Group event. Log this event when a user joins a group such as a guild, team or family.
     * Use this event to analyze how popular certain groups or social features are in your app
     *
     * Via: `join_group`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logJoinGroup({
     *   group_id: '12345',
     * });
     * ```
     *
     * @param params
     */
    logJoinGroup(params: Object<String, String | Number>): Promise<void>;

    /**
     * Level End event.
     *
     * Via: `level_end`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logLevelEnd({
     *   level: 12,
     *   success: 'true'
     * });
     * ```
     *
     * @param params
     */
    logLevelEnd(params: Object<String, String | Number>): Promise<void>;

    /**
     * Level Start event.
     *
     * Via: `level_start`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logLevelStart({
     *   level: 12,
     * });
     * ```
     *
     * @param params
     */
    logLevelStart(params: Object<String, String | Number>): Promise<void>;

    /**
     * Level Up event. This event signifies that a player has leveled up in your gaming app.
     * It can help you gauge the level distribution of your userbase and help you identify certain levels that are difficult to pass.
     *
     * Via: `level_up`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logLevelUp({
     *   level: 12,
     *   character: 'Snake',
     * });
     * ```
     *
     * @param params
     */
    logLevelUp(params: Object<String, String | Number>): Promise<void>;

    /**
     * Login event. Apps with a login feature can report this event to signify that a user has logged in.
     *
     * Via: `login`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logLogin({
     *   method: 'facebook.com',
     * });
     * ```
     *
     * @param params
     */
    logLogin(params: Object<String, String | Number>): Promise<void>;

    /**
     * Present Offer event. This event signifies that the app has presented a purchase offer to a user.
     * Add this event to a funnel with the {@link analytics#logAddToCart} and {@link analytics#logEcommercePurchase}
     * to gauge your conversion process.
     *
     * If you supply the `value` parameter, you must also supply the `currency` parameter so that revenue metrics can be computed accurately.
     *
     * Via: `present_offer`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logPresentOffer({
     *
     * });
     * ```
     *
     * @param params
     */
    logPresentOffer(params: Object<String, String | Number>): Promise<void>;

    /**
     * E-Commerce Purchase Refund event. This event signifies that an item purchase was refunded.
     *
     * If you supply the `value` parameter, you must also supply the `currency` parameter so that revenue metrics can be computed accurately.
     *
     * Via: `purchase_refund`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logPurchaseRefund({
     *
     * });
     * ```
     *
     * @param params
     */
    logPurchaseRefund(params?: Object<String, String | Number>): Promise<void>;

    /**
     * Remove from cart event.
     *
     * Via: `remove_from_cart`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logRemoveFromCart({
     *
     * });
     * ```
     *
     * @param params
     */
    logRemoveFromCart(params: Object<String, String | Number>): Promise<void>;

    /**
     * Search event. Apps that support search features can use this event to contextualize search
     * operations by supplying the appropriate, corresponding parameters. This event can help you
     * identify the most popular content in your app.
     *
     * Via: `search`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logSearch({
     *  search_term: 't-shirts',
     * });
     * ```
     *
     * @param params
     */
    logSearch(params: Object<String, String | Number>): Promise<void>;

    /**
     * Select Content event. This general purpose event signifies that a user has selected some
     * content of a certain type in an app. The content can be any object in your app. This event
     * can help you identify popular content and categories of content in your app.
     *
     * Via: `select_content`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logSelectContent({
     *
     * });
     * ```
     *
     * @param params
     */
    logSelectContent(params: Object<String, String | Number>): Promise<void>;

    // TODO Set checkout option

    /**
     * Share event. Apps with social features can log the Share event to identify the most viral content.
     *
     * Via: `share`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logShare({
     *   content_type: 't-shirts',
     *   item_id: '12345',
     *   method: 'twitter.com',
     * });
     * ```
     *
     * @param params
     */
    logShare(params: Object<String, String | Number>): Promise<void>;

    /**
     * Sign Up event. This event indicates that a user has signed up for an account in your app.
     * The parameter signifies the method by which the user signed up. Use this event to understand
     * the different behaviors between logged in and logged out users.
     *
     * Via: `sign_up`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logSignUp({
     *   method: 'facebook.com',
     * });
     * ```
     *
     * @param params
     */
    logSignUp(params: Object<String, String | Number>): Promise<void>;

    /**
     * Spend Virtual Currency event. This event tracks the sale of virtual goods in your app and can
     * help you identify which virtual goods are the most popular objects of purchase.
     *
     * Via: `spend_virtual_currency`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logSpendVirtualCurrency({
     *   item_name: 'battle_pass',
     *   virtual_currency_name: 'coins',
     *   value: 100,
     * });
     * ```
     *
     * @param params
     */
    logSpendVirtualCurrency(params: Object<String, String | Number>): Promise<void>;

    /**
     * Tutorial Begin event. This event signifies the start of the on-boarding process in your app.
     * Use this in a funnel with {@link analytics#logTutorialComplete} to understand how many users
     * complete this process and move on to the full app experience.
     *
     * Via: `tutorial_begin`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logTutorialBegin();
     * ```
     */
    logTutorialBegin(): Promise<void>;

    /**
     * Tutorial End event. Use this event to signify the user's completion of your app's on-boarding process.
     * Add this to a funnel with {@link analytics#logTutorialBegin} to understand how many users
     * complete this process and move on to the full app experience.
     *
     * Via: `tutorial_complete`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logTutorialComplete();
     * ```
     */
    logTutorialComplete(): Promise<void>;

    /**
     * Unlock Achievement event. Log this event when the user has unlocked an achievement in your game.
     * Since achievements generally represent the breadth of a gaming experience, this event can help
     * you understand how many users are experiencing all that your game has to offer.
     *
     * Via: `unlock_achievement`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logUnlockAchievement({
     *   achievement_id: '12345',
     * });
     * ```
     *
     * @param params
     */
    logUnlockAchievement(params: Object<String, String | Number>): Promise<void>;

    /**
     * View Item event. This event signifies that some content was shown to the user. This content
     * may be a product, a screen or just a simple image or text. Use the appropriate parameters
     * to contextualize the event. Use this event to discover the most popular items viewed in your app.
     *
     * If you supply the `value` parameter, you must also supply the `currency` parameter so that revenue metrics can be computed accurately.
     *
     * Via: `view_item`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logViewItem({
     *
     * });
     * ```
     *
     * @param params
     */
    logViewItem(params: Object<String, String | Number>): Promise<void>;

    /**
     * View Item List event. Log this event when the user has been presented with a list of items of a certain category.
     *
     * Via: `view_item_list`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logViewItemList({
     *   item_category: 't-shirts',
     * });
     * ```
     *
     * @param params
     */
    logViewItemList(params: Object<String, String | Number>): Promise<void>;

    /**
     * View Search Results event. Log this event when the user has been presented with the results of a search.
     *
     * Via: `view_search_results`
     *
     * #### Example
     *
     * ```js
     * await firebase.analytics().logViewSearchResults({
     *   search_term: 'clothing',
     * });
     * ```
     *
     * @param params
     */
    logViewSearchResults(params: Object<String, String | Number>): Promise<void>;
  }
}

declare module '@react-native-firebase/analytics' {
  import { ReactNativeFirebaseNamespace } from '@react-native-firebase/app-types';

  const FirebaseNamespaceExport: {} & ReactNativeFirebaseNamespace;

  /**
   * ```js
   * import { firebase } from '@react-native-firebase/analytics';
   * firebase.analytics().logEvent(...);
   * ```
   */
  export const firebase = FirebaseNamespaceExport;

  const AnalyticsDefaultExport: ReactNativeFirebaseModuleAndStatics<
    Analytics.Module,
    Analytics.Statics
  >;

  export default AnalyticsDefaultExport;
}

/**
 * Attach namespace to `firebase.` and `FirebaseApp.`.
 */
declare module '@react-native-firebase/app-types' {
  interface ReactNativeFirebaseNamespace {
    analytics: ReactNativeFirebaseModuleAndStatics<
      Analytics.Module,
      Analytics.Statics
    >;
  }

  interface FirebaseApp {
    analytics(): Analytics.Module;
  }
}
