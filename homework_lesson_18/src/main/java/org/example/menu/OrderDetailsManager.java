package org.example.menu;

import org.example.orderdeatilsDAO.OrderDetailsDaoImpl;
import org.example.model.OrderDetail;
import java.util.List;

import java.util.Scanner;
public class OrderDetailsManager implements Manager {
    private OrderDetailsDaoImpl orderDetailsDao;
    private Scanner scanner;

    public OrderDetailsManager(OrderDetailsDaoImpl orderDetailsDao) {
        this.orderDetailsDao = orderDetailsDao;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void manage() {
        while (true) {
            System.out.println("\nУправление деталями заказа:");
            System.out.println("1. Показать все детали заказов");
            System.out.println("2. Добавить деталь к заказу");
            System.out.println("3. Изменить деталь заказа");
            System.out.println("4. Удалить деталь из заказа");
            System.out.println("5. Вернуться в предыдущее меню");
            System.out.print("Ваш выбор: ");

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    showAll();
                    break;
                case 2:
                    addNew();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Неверный ввод, попробуйте еще раз.");
                    break;
            }
        }
    }

    @Override
    public void showAll() {
        List<OrderDetail> orderDetails = orderDetailsDao.findAll();
        if (orderDetails.isEmpty()) {
            System.out.println("Список деталей заказов пуст.");
        } else {
            for (OrderDetail detail : orderDetails) {
                System.out.println("Деталь заказа ID: " + detail.getOrderDetailId() + ", Заказ ID: " + detail.getOrderId() +
                        ", Тип: " + detail.getItemType() + ", ID товара: " + detail.getItemId() + ", Количество: " + detail.getQuantity());
            }
        }
    }


    @Override
    public void addNew() {
        System.out.println("Добавление детали к заказу:");
        System.out.print("Введите ID заказа: ");
        int orderId = scanner.nextInt();
        System.out.print("Введите тип детали (например, Drink или Dessert): ");
        String itemType = scanner.next();
        System.out.print("Введите ID товара: ");
        int itemId = scanner.nextInt();
        System.out.print("Введите количество: ");
        int quantity = scanner.nextInt();

        OrderDetail orderDetail = new OrderDetail(0, orderId, itemType, itemId, quantity);
        orderDetailsDao.save(orderDetail);
        System.out.println("Деталь успешно добавлена к заказу.");
    }

    @Override
    public void update() {
        System.out.print("Введите ID детали заказа, которую хотите обновить: ");
        int orderDetailId = scanner.nextInt();
        System.out.print("Введите новый ID заказа: ");
        int orderId = scanner.nextInt();
        System.out.print("Введите новый тип детали (например, Drink или Dessert): ");
        String itemType = scanner.next();
        System.out.print("Введите новый ID товара: ");
        int itemId = scanner.nextInt();
        System.out.print("Введите новое количество: ");
        int quantity = scanner.nextInt();

        OrderDetail orderDetail = new OrderDetail(orderDetailId, orderId, itemType, itemId, quantity);
        orderDetailsDao.update(orderDetail);
        System.out.println("Деталь заказа успешно обновлена.");
    }

    @Override
    public void delete() {
        System.out.print("Введите ID детали заказа, которую хотите удалить: ");
        int orderDetailId = scanner.nextInt();

        orderDetailsDao.delete(orderDetailId);
        System.out.println("Деталь заказа успешно удалена.");
    }
}
