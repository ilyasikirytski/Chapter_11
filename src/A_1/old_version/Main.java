package A_1.old_version;/*
Разработать многопоточное приложение.
Использовать возможности, предоставляемые пакетом java.util.concurrent.
Не использовать слово synchronized.
Все сущности, желающие получить доступ к ресурсу, должны быть потоками.
Использовать возможности ООП.
Не использовать графический интерфейс. Приложение должно быть консольным.

1. Порт. Корабли заходят в порт для разгрузки/загрузки контейнеров. Число
контейнеров, находящихся в текущий момент в порту и на корабле, должно
быть неотрицательным и превышающим заданную грузоподъемность судна и вместимость порта.
В порту работает несколько причалов. У одного
причала может стоять один корабль. Корабль может загружаться у причала,
разгружаться или выполнять оба действия.
 */
/*
сделать через треды
на основе парковки найти задачу рабочую
 */

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1, true);
        Port port1 = new Port("port1", 100);
        port1.setCountOfContainers(100);
        Port port2 = new Port("port2", 50);

        for (int i = 0; i < 6; i++) {
            new Ship(semaphore,10, port1, port2).start();
        }
    }
}