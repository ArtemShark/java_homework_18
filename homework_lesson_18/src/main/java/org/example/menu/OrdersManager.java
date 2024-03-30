package org.example.menu;

import org.example.orderdeatilsDAO.OrderDetailsDaoImpl;
import org.example.ordersDAO.OrdersDaoImpl;
import org.example.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import java.util.Scanner;
public class OrdersManager implements Manager {
    private OrdersDaoImpl ordersDao;
    private Scanner scanner;

    private OrderDetailsManager orderDetailsManager;

    public OrdersManager(OrdersDaoImpl ordersDao, OrderDetailsDaoImpl orderDetailsDao) {
        this.ordersDao = ordersDao;
        this.orderDetailsManager = new OrderDetailsManager(orderDetailsDao);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void manage() {
        while (true) {
            System.out.println("\nУправление заказами:");
            System.out.println("1. Показать все заказы");
            System.out.println("2. Показать все заказы конкретного десерта");
            System.out.println("3. Показать все заказы конкретного официанта");
            System.out.println("4. Показать все заказы конкретного клиента");
            System.out.println("5. Добавить новый заказ кофе");
            System.out.println("6. Добавить новый заказ десерта");
            System.out.println("7. Изменить заказ");
            System.out.println("8. Удалить заказ");
            System.out.println("9. Удалить заказы конкретного десерта");
            System.out.println("10. Управление деталями заказа");
            System.out.println("11. Вернуться в предыдущее меню");
            System.out.print("Ваш выбор: ");

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    showAll();
                    break;
                case 2:
                    System.out.print("Введите ID десерта: ");
                    int dessertId = scanner.nextInt();
                    showAllDessertOrders(dessertId);
                    break;
                case 3:
                    showAllOrdersOfStaff();
                    break;
                case 4:
                    showAllOrdersOfClient();
                    break;
                case 5:
                    addNewCoffeeOrder();
                    break;
                case 6:
                    addNewDessertOrder();
                    break;
                case 7:
                    update();
                    break;
                case 8:
                    delete();
                    break;
                case 9:
                    deleteOrdersByDessert();
                    break;
                case 10:
                    orderDetailsManager.manage();
                    break;
                case 11:
                    return;
                default:
                    System.out.println("Неверный ввод, попробуйте еще раз.");
                    break;
            }
        }
    }

    @Override
    public void showAll() {
        List<Order> orders = ordersDao.findAll();
        if (orders.isEmpty()) {
            System.out.println("Список заказов пуст.");
        } else {
            for (Order order : orders) {
                System.out.println("Заказ ID: " + order.getOrderId() + ", Клиент ID: " + order.getClientId() + ", Сотрудник ID: " + order.getStaffId() + ", Дата заказа: " + order.getOrderDate() + ", Общая стоимость: " + order.getTotalPrice());
            }
        }
    }

    @Override
    public void addNew() {
        System.out.println("Добавление нового заказа:");
        System.out.print("Введите ID клиента: ");
        int clientId = scanner.nextInt();
        System.out.print("Введите ID сотрудника: ");
        int staffId = scanner.nextInt();
        System.out.print("Введите общую стоимость заказа: ");
        BigDecimal totalPrice = scanner.nextBigDecimal();

        LocalDate orderDate = LocalDate.now();

        Order order = new Order(0, clientId, staffId, orderDate, totalPrice);
        ordersDao.save(order);
        System.out.println("Новый заказ успешно добавлен.");
    }

    @Override
    public void update() {
        System.out.print("Введите ID заказа, который хотите обновить: ");
        int orderId = scanner.nextInt();
        System.out.print("Введите новый ID клиента: ");
        int clientId = scanner.nextInt();
        System.out.print("Введите новый ID сотрудника: ");
        int staffId = scanner.nextInt();
        System.out.print("Введите новую общую стоимость заказа: ");
        BigDecimal totalPrice = scanner.nextBigDecimal();

        Order order = new Order(orderId, clientId, staffId, LocalDate.now(), totalPrice);
        System.out.println("Заказ успешно обновлен.");
    }


    @Override
    public void delete() {
        System.out.print("Введите ID заказа, который хотите удалить: ");
        int orderId = scanner.nextInt();
        ordersDao.delete(orderId);
        System.out.println("Заказ успешно удален.");
    }

    public void addNewCoffeeOrder() {
        System.out.println("Добавление нового заказа кофе:");
        System.out.print("Введите ID клиента: ");
        int clientId = scanner.nextInt();
        System.out.print("Введите ID сотрудника: ");
        int staffId = scanner.nextInt();
        System.out.print("Введите общую стоимость заказа: ");
        BigDecimal totalPrice = scanner.nextBigDecimal();

        LocalDate orderDate = LocalDate.now();

        Order order = new Order(0, clientId, staffId, orderDate, totalPrice);
        ordersDao.saveCoffeeOrder(order);
        System.out.println("Новый заказ кофе успешно добавлен.");
    }

    public void addNewDessertOrder() {
        System.out.println("Добавление нового заказа десерта:");
        System.out.print("Введите ID клиента: ");
        int clientId = scanner.nextInt();
        System.out.print("Введите ID сотрудника: ");
        int staffId = scanner.nextInt();
        System.out.print("Введите общую стоимость заказа: ");
        BigDecimal totalPrice = scanner.nextBigDecimal();

        LocalDate orderDate = LocalDate.now();

        Order order = new Order(0, clientId, staffId, orderDate, totalPrice);
        ordersDao.saveDessertOrder(order);
        System.out.println("Новый заказ десерта успешно добавлен.");
    }

    private void deleteOrdersByDessert() {
        System.out.print("Введите ID десерта, заказы которого вы хотите удалить: ");
        int dessertId = scanner.nextInt();

        ordersDao.deleteOrdersByDessertId(dessertId);
        System.out.println("Заказы конкретного десерта успешно удалены.");
    }

    public void showAllDessertOrders(int dessertId) {
        List<Order> dessertOrders = ordersDao.findByDessertId(dessertId);
        if (dessertOrders.isEmpty()) {
            System.out.println("Для данного десерта нет заказов.");
        } else {
            System.out.println("Заказы для десерта с ID " + dessertId + ":");
            for (Order order : dessertOrders) {
                System.out.println("Заказ ID: " + order.getOrderId() + ", Клиент ID: " + order.getClientId() + ", Сотрудник ID: " + order.getStaffId() + ", Дата заказа: " + order.getOrderDate() + ", Общая стоимость: " + order.getTotalPrice());
            }
        }
    }

    public void showAllOrdersOfStaff() {
        System.out.print("Введите ID официанта, чьи заказы вы хотите увидеть: ");
        int staffId = scanner.nextInt();
        scanner.nextLine();

        List<Order> orders = ordersDao.findAllOrdersOfStaff(staffId);
        if (orders.isEmpty()) {
            System.out.println("Этот официант еще не принимал заказы.");
        } else {
            System.out.println("Заказы официанта с ID " + staffId + ":");
            for (Order order : orders) {
                System.out.println("Заказ ID: " + order.getOrderId() + ", Дата заказа: " + order.getOrderDate() + ", Общая стоимость: " + order.getTotalPrice());
            }
        }
    }

    public void showAllOrdersOfClient() {
        System.out.print("Введите ID клиента, чьи заказы вы хотите увидеть: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        List<Order> orders = ordersDao.findAllOrdersOfClient(clientId);
        if (orders.isEmpty()) {
            System.out.println("У этого клиента еще нету заказов");
        } else {
            System.out.println("Заказы клиента с ID " + clientId + ":");
            for (Order order : orders) {
                System.out.println("Заказ ID: " + order.getOrderId() + ", Дата заказа: " + order.getOrderDate() + ", Общая стоимость: " + order.getTotalPrice());
            }
        }
    }
}