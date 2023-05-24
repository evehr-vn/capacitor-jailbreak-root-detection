#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(CapacitorJailbreakRootDetectionPlugin, "CapacitorJailbreakRootDetection",
           CAP_PLUGIN_METHOD(isJailbrokenOrRooted, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(isSimulator, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(isDebuggedMode, CAPPluginReturnPromise);
)
