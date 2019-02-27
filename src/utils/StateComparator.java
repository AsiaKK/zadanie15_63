package utils;

import domain.Order;

import java.util.Comparator;

public class StateComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        return Integer.compare(o1.getState().ordinal(), o2.getState().ordinal());
    }
}
