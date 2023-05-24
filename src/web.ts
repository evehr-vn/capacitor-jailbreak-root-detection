import { WebPlugin } from '@capacitor/core';

import type { CapacitorJailbreakRootDetectionPlugin } from './definitions';

export class CapacitorJailbreakRootDetectionWeb
  extends WebPlugin
  implements CapacitorJailbreakRootDetectionPlugin
{
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
