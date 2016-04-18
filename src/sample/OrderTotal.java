package sample;


import javafx.beans.property.DoubleProperty;

public class OrderTotal {

    public DoubleProperty ordertotal;

    public double getOrderTotal() {
        return ordertotal.get();
    }

    public void setOrderTotal(double newOrderTotal) {
        ordertotal.set(newOrderTotal);
    }

    public DoubleProperty OrderTotalProperty() {
        return ordertotal;
    }

}
