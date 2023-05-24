package com.evehr.plugins.capacitor.jailbreakrootdetection;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapacitorJailbreakRootDetection")
public class CapacitorJailbreakRootDetectionPlugin extends Plugin {

    private CapacitorJailbreakRootDetection implementation = new CapacitorJailbreakRootDetection();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }
}
