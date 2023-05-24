package com.evehr.plugins.capacitor.jailbreakrootdetection;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import android.content.Context;

import com.evehr.plugins.capacitor.jailbreakrootdetection.Rooted.RootedCheck;
import com.evehr.plugins.capacitor.jailbreakrootdetection.Rooted.EmulatorDetector;

@CapacitorPlugin(name = "CapacitorJailbreakRootDetection")
public class CapacitorJailbreakRootDetectionPlugin extends Plugin {
    private CapacitorJailbreakRootDetection implementation = new CapacitorJailbreakRootDetection();
    private RootedCheck rootedCheck;
    private EmulatorDetector emulatorDetector;

    @Override
    public void load() {
        rootedCheck = new RootedCheck(getContext());
        emulatorDetector = new EmulatorDetector(getContext());
    }

    @PluginMethod
    public void isJailbrokenOrRooted(PluginCall call) {
        boolean result = rootedCheck.isJailBroken();

        JSObject ret = new JSObject();
        ret.put("result", implementation.isJailbrokenOrRooted(result));
        call.resolve(ret);
    }
    
    
    @PluginMethod
    public void isSimulator(PluginCall call) {
        boolean result = emulatorDetector.isEmulator();

        JSObject ret = new JSObject();
        ret.put("result", implementation.isSimulator(result));
        call.resolve(ret);
    }
    
    
    @PluginMethod
    public void isDebuggedMode(PluginCall call) {
        boolean result = emulatorDetector.isDebuggedMode();

        JSObject ret = new JSObject();
        ret.put("result", implementation.isDebuggedMode(result));
        call.resolve(ret);
    }
}
