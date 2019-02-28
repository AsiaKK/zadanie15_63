package utils;

import domain.Order;
import domain.OrderState;
import domain.UserAction;

import java.io.IOException;
import java.util.*;

public class OrderChecker {

    public static void main(String[] args) throws IOException {
        OrderReader orderReader = new OrderReader();
        StatusValidator statusValidator = new StatusValidator();

        Scanner scan = new Scanner(System.in);

        System.out.println("Wczytuję zamówienia z pliku:");
        List<Order> ordersFromFile = orderReader.getFromFile("OrderFile.txt");

        printOrders(ordersFromFile);

        printOptionsMenu();

        System.out.println("==================================");
        System.out.println("Podaj swój wybór:");
        String userInput = scan.nextLine().toUpperCase();
        UserAction choosedAction = UserAction.valueOf(userInput);

        switch (choosedAction) {
            case POSORTUJ:
                System.out.println("Po czym chcesz sortować: nazwa, cena lub stan.");
                System.out.println("Podaj sój wybór");
                String userInputSorter = scan.nextLine();
                Comparator<Order> comparator = null;

                 if(userInputSorter.equals("nazwa")){
                     comparator = new NameComparator();
                     Collections.sort(ordersFromFile, comparator);
                     printOrders(ordersFromFile);
                 } else if(userInputSorter.equals("cena")){
                     comparator = new PriceComparator();
                     Collections.sort(ordersFromFile, comparator);
                     printOrders(ordersFromFile);
                 } else if(userInputSorter.equals("stan")) {
                     comparator = new StateComparator();
                     Collections.sort(ordersFromFile, comparator);
                     printOrders(ordersFromFile);
                 } else {
                     System.out.println("Sortowanie nie jest mozliwe.");
                     System.out.println("================================");
                 }

                break;
            case ZMIEN:
                System.out.println("Które zamówienie zmieniamy?");
                System.out.println("podaj ID");
                int userInputId = Integer.valueOf(scan.nextInt());
                System.out.println("Na jaki status chcesz zmienić zamówienie");
                System.out.println("Masz do wyboru: prepared, on_transport, finished, canceled");
                scan.nextLine();
                OrderState newState = OrderState.valueOf(scan.nextLine().toUpperCase());
                Order foundedOrder = findOrder(ordersFromFile, userInputId);
                if(foundedOrder == null){
                    System.out.println("Zamówienie nie istnieje");
                } else if(statusValidator.isChangePossible(foundedOrder.getState(), newState)){
                    foundedOrder.setState(newState);
                } else {
                    System.out.println("Zmiana statusu z " + foundedOrder.getState() + "na status " + newState + " " +
                            "jest nieprawidłowa.");
                }
                break;
            case DODAJ:
                System.out.println("Podaj nazwe zamowienia: ");
                String name = scan.nextLine();

                System.out.println("Podaj cene zamowienia: ");
                double price = scan.nextDouble();

                Order newOrder = new Order(ordersFromFile.size() + 1, name, price, OrderState.ORDERED);
                ordersFromFile.add(newOrder);
                break;
            case KONIEC:
                break;
            default:
                System.out.println("Default");
        }

        System.out.println("PO DZIALANIU: ");
        printOrders(ordersFromFile);

        scan.close();

    }

    private static void printOrders(List<Order> orders) {
        System.out.println("ZESTAWIENIE ZAMOWIEN");
        for (Order order : orders) {
            System.out.println(order.toString());
        }
    }

    private static void printOptionsMenu() {
        System.out.println("==================================");
        System.out.println("Lista działań użytkownika");
        System.out.println("__________________________________");
        System.out.println("Masz do wyboru: ");
        for (UserAction userAction : UserAction.values()) {
            System.out.println(userAction.toString());
        }
    }

    private static Order findOrder(List<Order> orders, int id){
        for (Order order : orders) {
            if(order.getId() == id){
                return order;
            }
        }
        return null;
    }

}