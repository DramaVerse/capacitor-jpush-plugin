export interface JPushPluginPlugin {
  /**
   * Initialize JPush SDK
   */
  init(): Promise<{ success: boolean; message: string }>;

  /**
   * Get JPush registration ID
   */
  getRegistrationID(): Promise<{ registrationId: string }>;

  /**
   * Set alias for the device
   */
  setAlias(options: { alias: string }): Promise<{ success: boolean; message: string }>;

  /**
   * Delete alias for the device
   */
  deleteAlias(): Promise<{ success: boolean; message: string }>;

  /**
   * Add tags to the device
   */
  addTags(options: { tags: string[] }): Promise<{ success: boolean; message: string }>;

  /**
   * Delete specific tags from the device
   */
  deleteTags(options: { tags: string[] }): Promise<{ success: boolean; message: string }>;

  /**
   * Clean all tags from the device
   */
  cleanTags(): Promise<{ success: boolean; message: string }>;

  /**
   * Set badge number (mainly for iOS)
   */
  setBadge(options: { badge: number }): Promise<{ success: boolean; message: string }>;
}

// Event listener types
export interface JPushRegistrationEvent {
  registrationId: string;
}

export interface JPushMessageEvent {
  messageId: string;
  title: string;
  message: string;
  extra: string;
  contentType: string;
}

export interface JPushNotificationEvent {
  messageId: string;
  title: string;
  content: string;
  extra: string;
  notificationId: number;
}

export interface JPushConnectionEvent {
  isConnected: boolean;
}

export interface JPushTagOperationEvent {
  sequence: number;
  errorCode: number;
  tags: string;
}

export interface JPushAliasOperationEvent {
  sequence: number;
  errorCode: number;
  alias: string;
}

export interface JPushMobileNumberOperationEvent {
  sequence: number;
  errorCode: number;
  mobileNumber: string;
}
