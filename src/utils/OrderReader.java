package utils;

import domain.Order;
import domain.OrderState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderReader {

    public List<Order> getFromFile(String pathToFile) throws IOException {
        List<Order> orders = new ArrayList<>();

        FileReader fileReader = new FileReader(pathToFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String singleLine;

        while ((singleLine = bufferedReader.readLine()) != null) {
            orders.add(convertToOrder(singleLine));
        }

        bufferedReader.close();
        return orders;
    }

    private Order convertToOrder(String input) {
        String[] values = input.split(";");

        int id = Integer.valueOf(values[0]);
        String name = values[1];
        double price = Double.valueOf(values[2]);
        OrderState state = OrderState.valueOf(values[3]);

        return new Order(id, name, price, state);
    }

}