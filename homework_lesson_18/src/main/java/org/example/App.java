package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import org.example.clientsDAO.ClientsDaoImpl;
import org.example.drinkDAO.DrinksDaoImpl;
import org.example.dessertDAO.DessertsDaoImpl;
import org.example.orderdeatilsDAO.OrderDetailsDaoImpl;
import org.example.ordersDAO.OrdersDaoImpl;
import org.example.scheduleDAO.ScheduleDaoImpl;
import org.example.staffDAO.StaffDaoImpl;
import org.example.menu.*;


public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static Connection connection;
    private static DrinksManager drinksManager;
    private static DessertsManager dessertsManager;
    private static StaffManager staffManager;
    private static ClientsManager clientsManager;
    private static ScheduleManager scheduleManager;
    private static OrdersManager ordersManager;


    public static void main(String[] args) {
        try {
            setupDatabaseConnection();
            initializeManagers();

            while (true) {
                System.out.println("\nВыберите категорию:");
                System.out.println("1. Управление напитками");
                System.out.println("2. Управление десертами");
                System.out.println("3. Управление сотрудниками");
                System.out.println("4. Управление клиентами");
                System.out.println("5. Управление расписанием");
                System.out.println("6. Управление заказом");
                System.out.println("7. Выход");
                System.out.print("Ваш выбор: ");

                int category = scanner.nextInt();
                scanner.nextLine();

                switch (category) {
                    case 1:
                        drinksManager.manage();
                        break;
                    case 2:
                        dessertsManager.manage();
                        break;
                    case 3:
                        staffManager.manage();
                        break;
                    case 4:
                        clientsManager.manage();
                        break;
                    case 5:
                        scheduleManager.manage();
                        break;
                    case 6:
                        ordersManager.manage();
                        break;
                    case 7:
                        System.out.println("Выход из программы...");
                        return;
                    default:
                        System.out.println("Неверный ввод, попробуйте еще раз.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void setupDatabaseConnection() throws Exception {
        String url = "jdbc:postgresql://localhost:5436/homework1-db";
        String user = "sa";
        String password = "admin";
        connection = DriverManager.getConnection(url, user, password);
    }

    private static void initializeManagers() {
        drinksManager = new DrinksManager(new DrinksDaoImpl(connection));
        dessertsManager = new DessertsManager(new DessertsDaoImpl(connection));
        staffManager = new StaffManager(new StaffDaoImpl(connection));
        clientsManager = new ClientsManager(new ClientsDaoImpl(connection));
        scheduleManager = new ScheduleManager(new ScheduleDaoImpl(connection));
        OrdersDaoImpl ordersDao = new OrdersDaoImpl(connection);
        OrderDetailsDaoImpl orderDetailsDao = new OrderDetailsDaoImpl(connection);
        ordersManager = new OrdersManager(ordersDao, orderDetailsDao);
    }

}