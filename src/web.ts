import { WebPlugin } from '@capacitor/core';

import type { JPushPluginPlugin } from './definitions';

export class JPushPluginWeb extends WebPlugin implements JPushPluginPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
