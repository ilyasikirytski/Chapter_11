package A_6;

public class Client extends Thread {
    McDonalds mcDonalds;

    public Client(String clientNumber, McDonalds mcDonalds) {
        super(clientNumber);
        this.mcDonalds = mcDonalds;
    }
/*
написать выбор рандомной кассы, которая возвращает Boolean. и в случае false - переход в другую кассу
 */
    @Override
    public void run() {
        try {
            mcDonalds.getServ();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
