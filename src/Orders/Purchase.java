package Orders;

import java.io.Serializable;

public class Purchase implements Serializable{
    private String name;
    private double totalPrice;
    private long time;
    public Purchase(String name, double totalPrice, long time){
        this.name=name;
        this.totalPrice=totalPrice;
        this.time=time;
    }

    public String getName() {
        return name;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public long getTime() {
        return time;
    }
}
