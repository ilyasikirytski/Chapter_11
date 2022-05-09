package A_6;

public class Client extends Thread {
    AbstractRestaurant restaurant;

    public Client(String clientNumber, AbstractRestaurant restaurant) {
        super(clientNumber);
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            restaurant.serve();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
