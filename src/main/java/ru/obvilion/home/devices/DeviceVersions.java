package ru.obvilion.home.devices;

import ru.obvilion.home.devices.types.BicycleRGB;

public class DeviceVersions {
    public static byte getAuthMethod(UnauthorizedDevice device) {
        return 0;
    }

    public static Device fromUnauthorized(UnauthorizedDevice from) {
        Device result;

        switch (from.device_name) {
            case "BicycleRGB":
                return new BicycleRGB(from);
            default:
                throw new IllegalStateException("Unexpected value: " + from.device_name);
        }
    }
}
