import Foundation
import Capacitor
import UIKit

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapacitorJailbreakRootDetectionPlugin)
public class CapacitorJailbreakRootDetectionPlugin: CAPPlugin {
    private let implementation = CapacitorJailbreakRootDetection()

    @objc func isJailbrokenOrRooted(_ call: CAPPluginCall) {
        call.resolve([
            "result": UIDevice.current.isJailBroken
        ])
    }

    @objc func isSimulator(_ call: CAPPluginCall) {
        call.resolve([
            "result": UIDevice.current.isSimulator
        ])
    }
    
    @objc func isDebuggedMode(_ call: CAPPluginCall) {
        call.resolve([
            "result": UIDevice.current.isDebuggedMode
        ])
    }
    
    @objc func exitApp(_ call: CAPPluginCall) {
        UIControl().sendAction(#selector(NSXPCConnection.suspend), to: UIApplication.shared, for: nil)
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
            exit(EXIT_SUCCESS)
        }
    }
}
