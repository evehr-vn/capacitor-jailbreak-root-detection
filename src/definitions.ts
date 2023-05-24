export interface CapacitorJailbreakRootDetectionPlugin {
  isJailbrokenOrRooted(): Promise<JailbreakRootResult>;
  isSimulator(): Promise<JailbreakRootResult>;
  isDebuggedMode(): Promise<JailbreakRootResult>;
}

export interface JailbreakRootResult {
  result: boolean;
}