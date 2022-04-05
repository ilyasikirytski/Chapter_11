package A_3.sync;

public class Parking {
    private final boolean[] parkingPlaces = new boolean[5];

    public Parking() {
    }

    public void park(Car car) {
        boolean allTaken = false;
        for (int i = 0; i < parkingPlaces.length; i++) {
            allTaken = parkingPlaces[i];
            if (!parkingPlaces[i]) {
                parkingPlaces[i] = true;
                car.parkNumber = i;
                break;
            }
        }
        if (allTaken) {
            System.out.printf("auto %s can't park - all parks are taken", car.carName);
        } else {
            System.out.printf("auto %s - parked on place %s\n", car.carName, car.parkNumber);
        }
    }

    public void unPark(Car car) {
        parkingPlaces[car.parkNumber] = false;
        System.out.printf("auto %s - gone form place %s\n", car.carName, car.parkNumber);
    }
}
