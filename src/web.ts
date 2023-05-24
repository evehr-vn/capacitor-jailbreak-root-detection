import { WebPlugin } from '@capacitor/core';

import type { CapacitorJailbreakRootDetectionPlugin, JailbreakRootResult } from './definitions';

export class CapacitorJailbreakRootDetectionWeb
  extends WebPlugin
  implements CapacitorJailbreakRootDetectionPlugin
{
  exitApp(): void {
    // Do Nothing
  }

  async isJailbrokenOrRooted(): Promise<JailbreakRootResult> {
    return {
      result: false,
    };
  }
  async isSimulator(): Promise<JailbreakRootResult> {
    return {
      result: false,
    };
  }
  async isDebuggedMode(): Promise<JailbreakRootResult> {
    return {
      result: false,
    };
  }
}
