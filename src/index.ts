import { registerPlugin } from '@capacitor/core';

import type { CapacitorJailbreakRootDetectionPlugin } from './definitions';

const CapacitorJailbreakRootDetection =
  registerPlugin<CapacitorJailbreakRootDetectionPlugin>(
    'CapacitorJailbreakRootDetection',
    {
      web: () =>
        import('./web').then(m => new m.CapacitorJailbreakRootDetectionWeb()),
    },
  );

export * from './definitions';
export { CapacitorJailbreakRootDetection };
