package ru.obvilion.home.packets;

import ru.obvilion.home.devices.Device;

import java.io.DataInputStream;
import java.io.IOException;

public interface PacketHandler {
    /**
     * После того, как пакет отпарсен, известна его длина и тип, вызывается этод метод
     *
     * @param device Девайс, с которого был сделан запрос
     * @param is Входящие данные
     * @param length Длина пакета
     * @throws IOException Ошибка чтения данных пакета
     */
    void handle(Device device, DataInputStream is, int length) throws IOException;

    /**
     * Возращает минимальное число байт, для проверки целостности пакета
     *
     * @return Число байт, меньше которых не вызывается метод {@link #handle(Device, DataInputStream, int)} )}
     */
    default int getMinLength() {
        return 0;
    }
}
