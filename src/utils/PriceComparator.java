package utils;

import domain.Order;

import java.util.Comparator;

public class PriceComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return Double.compare(o1.getPrice(),o2.getPrice());
    }
}
