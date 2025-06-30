import { registerPlugin } from '@capacitor/core';

import type { JPushPluginPlugin } from './definitions';

const JPushPlugin = registerPlugin<JPushPluginPlugin>('JPushPlugin', {
  web: () => import('./web').then((m) => new m.JPushPluginWeb()),
});

export * from './definitions';
export { JPushPlugin };
