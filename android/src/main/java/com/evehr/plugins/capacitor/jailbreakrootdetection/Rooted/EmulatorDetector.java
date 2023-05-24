package com.evehr.plugins.capacitor.jailbreakrootdetection.Rooted;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class EmulatorDetector {

    private final Context context;

    public EmulatorDetector(Context ctx) {
        context = ctx;
    }

    public boolean isEmulator() {
        return (
            checkBuildProperties() ||
            checkEmulatorFiles() ||
            checkTelephonyManager() ||
            checkPipes() ||
            checkQEmuDriverFile() ||
            checkQEmuProps()
        );
    }

    public boolean isDebuggedMode() {
        boolean result = false;
        try {
            if ((context.getPackageManager().getPackageInfo(context.getPackageName(), 0).
                    applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0)
                result = true;
            else
                result = false;
        } catch (PackageManager.NameNotFoundException e) {
            result = false;
        }

        return result;
    }

    private boolean checkBuildProperties() {
        String[] knownEmulatorBuildProperties = {
            "ro.hardware",
            "goldfish",
            "ro.hardware",
            "ranchu",
            "ro.kernel.qemu",
            "1",
            "ro.product.model",
            "sdk",
            "ro.product.model",
            "google_sdk",
            "ro.product.model",
            "sdk_x86",
            "ro.product.model",
            "vbox86p"
        };

        for (int i = 0; i < knownEmulatorBuildProperties.length; i += 2) {
            String property = knownEmulatorBuildProperties[i];
            String value = knownEmulatorBuildProperties[i + 1];
            if (value.equals(getSystemProperty(property))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkEmulatorFiles() {
        String[] knownEmulatorFiles = {
            "/dev/socket/qemud",
            "/dev/qemu_pipe",
            "/system/lib/libc_malloc_debug_qemu.so",
            "/sys/qemu_trace",
            "/system/bin/qemu-props"
        };

        for (String path : knownEmulatorFiles) {
            if (new File(path).exists()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkTelephonyManager() {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String networkOperatorName = tm.getNetworkOperatorName();
        return "Android".equalsIgnoreCase(networkOperatorName);
    }

    private boolean checkPipes() {
        String[] knownEmulatorPipes = { "/dev/socket/qemud", "/dev/qemu_pipe" };

        for (String path : knownEmulatorPipes) {
            if (new File(path).exists()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkQEmuDriverFile() {
        File driverFile = new File("/proc/tty/driver");
        if (driverFile.exists() && driverFile.canRead()) {
            byte[] data = new byte[(int) driverFile.length()];
            try {
                String driverData = new BufferedReader(new InputStreamReader(new FileInputStream(driverFile))).readLine();
                return driverData.contains("goldfish") || driverData.contains("qemu");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean checkQEmuProps() {
        int minQemuProps = 6;
        String[] knownQemuProps = {
            "ro.product.device",
            "qemu",
            "ro.product.brand",
            "generic",
            "ro.product.manufacturer",
            "unknown",
            "ro.product.model",
            "sdk",
            "ro.hardware",
            "goldfish",
            "ro.hardware",
            "ranchu"
        };

        int matchCount = 0;
        for (int i = 0; i < knownQemuProps.length; i += 2) {
            String property = knownQemuProps[i];
            String value = knownQemuProps[i + 1];
            if (value.equals(getSystemProperty(property))) {
                matchCount++;
            }
        }
        return matchCount >= minQemuProps;
    }

    private String getSystemProperty(String propertyName) {
        String propertyValue = "";
        try {
            Class<?> systemPropertyClazz = Class.forName("android.os.SystemProperties");
            propertyValue = (String) systemPropertyClazz.getMethod("get", String.class).invoke(systemPropertyClazz, propertyName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertyValue;
    }
}
