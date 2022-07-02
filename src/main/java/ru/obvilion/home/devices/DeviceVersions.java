package ru.obvilion.home.devices;

import ru.obvilion.home.devices.types.RGBStrip;
import ru.obvilion.home.utils.AuthorizationMethod;

public class DeviceVersions {
    /**
     * {@link AuthorizationMethod#TOKEN_40_WITHOUT_SSL} - Токен с длиной 40 символов без шифрования
     *
     * @param device Неавторизованный девайс, в нем же версия и тип устройства
     * @return Метод авторизации
     */
    public static AuthorizationMethod getAuthMethod(UnauthorizedDevice device) {
        return AuthorizationMethod.TOKEN_40_WITHOUT_SSL;
    }

    /**
     * Преобразует {@link UnauthorizedDevice} в авторизованное по указанному {@link UnauthorizedDevice#device_name} и
     * {@link UnauthorizedDevice#version}
     *
     * @param from Девайс, с которого нужно копировать данные
     * @return Девайс по указанному {@link UnauthorizedDevice#device_name} и {@link UnauthorizedDevice#version}
     */
    public static Device fromUnauthorized(UnauthorizedDevice from) {
        Device result;

        switch (from.device_name) {
            case "BicycleRGB":
                return new RGBStrip(from);
            default:
                throw new IllegalStateException("Unexpected value: " + from.device_name);
        }
    }
}
