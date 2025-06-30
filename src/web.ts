import { WebPlugin } from '@capacitor/core';

import type { JPushPluginPlugin } from './definitions';

export class JPushPluginWeb extends WebPlugin implements JPushPluginPlugin {
  async init(): Promise<{ success: boolean; message: string }> {
    throw this.unavailable('JPush is not available on web platform');
  }

  async getRegistrationID(): Promise<{ registrationId: string }> {
    throw this.unavailable('JPush is not available on web platform');
  }

  async setAlias(options: { alias: string }): Promise<{ success: boolean; message: string }> {
    console.log('JPush setAlias called on web with:', options);
    throw this.unavailable('JPush is not available on web platform');
  }

  async deleteAlias(): Promise<{ success: boolean; message: string }> {
    throw this.unavailable('JPush is not available on web platform');
  }

  async addTags(options: { tags: string[] }): Promise<{ success: boolean; message: string }> {
    console.log('JPush addTags called on web with:', options);
    throw this.unavailable('JPush is not available on web platform');
  }

  async deleteTags(options: { tags: string[] }): Promise<{ success: boolean; message: string }> {
    console.log('JPush deleteTags called on web with:', options);
    throw this.unavailable('JPush is not available on web platform');
  }

  async cleanTags(): Promise<{ success: boolean; message: string }> {
    throw this.unavailable('JPush is not available on web platform');
  }

  async setBadge(options: { badge: number }): Promise<{ success: boolean; message: string }> {
    console.log('JPush setBadge called on web with:', options);
    throw this.unavailable('JPush is not available on web platform');
  }
}
