package A_3.sync;

public class Car {
    public String carName;
    public int parkNumber;

    public Car(String carName) {
        this.carName = carName;
    }

    /*
    написать метод run
    едет-> паркуется,стоит, уезжает (симуляция принтами)
    Ссылку на паркинг можно добавить или в метод
     */
    public void run(Parking parking){
        System.out.printf("auto %s arrived to parking\n", this.carName);
        if ((this.parkNumber = parking.park()) < 0){
            System.out.printf("auto %s can't park - all parks are taken\n", this.carName);
        } else {
            System.out.printf("auto %s - parked on place %s\n", this.carName, this.parkNumber);
        }
    }
}