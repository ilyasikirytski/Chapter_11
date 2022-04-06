package A_3.sync;

public class Parking implements ParkingFun {
    private final boolean[] parkingPlaces;

    public Parking(int capacity) {
        parkingPlaces = new boolean[capacity];
    }
/*
метод park без аргументов при вызове возвращает номер свободного места или -1 если мест нет.
 */
    @Override
    public int park() {
        int parkNumber = 0;
        boolean allTaken = false;
        for (int i = 0; i < parkingPlaces.length; i++) {
            allTaken = parkingPlaces[i];
            if (!parkingPlaces[i]) {
                parkingPlaces[i] = true;
                parkNumber = i;
                break;
            }
        }
        if (allTaken) {
            return -1;
        } else {
            return parkNumber;
        }
    }

    @Override
    public void unPark(Car car) {
        parkingPlaces[car.parkNumber] = false;
        System.out.printf("auto %s - gone form place %s\n", car.carName, car.parkNumber);
    }
}
