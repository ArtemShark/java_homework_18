package org.example.menu;

import org.example.clientsDAO.ClientsDaoImpl;
import org.example.model.Client;
import java.time.LocalDate;
import java.util.List;

import java.util.Scanner;
public class ClientsManager implements Manager {
    private ClientsDaoImpl clientsDao;
    private Scanner scanner;

    public ClientsManager(ClientsDaoImpl clientsDao) {
        this.clientsDao = clientsDao;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void manage() {
        while (true) {
            System.out.println("\nУправление клиентами:");
            System.out.println("1. Показать всех клиентов");
            System.out.println("2. Добавить нового клиента");
            System.out.println("3. Изменить информацию клиента");
            System.out.println("4. Удалить клиента");
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
        List<Client> clientsList = clientsDao.findAll();
        if (clientsList.isEmpty()) {
            System.out.println("Список клиентов пуст.");
        } else {
            for (Client client : clientsList) {
                System.out.println(client.getClientId() + ": " + client.getFullName() + ", Email: " + client.getEmail() + ", Телефон: " + client.getPhone() + ", Скидка: " + client.getDiscount() + "%");
            }
        }
    }

    @Override
    public void addNew() {
        System.out.println("Добавление нового клиента:");
        System.out.print("Введите полное имя: ");
        String fullName = scanner.nextLine();
        System.out.print("Введите дату рождения (ГГГГ-ММ-ДД): ");
        String birthDate = scanner.nextLine();
        System.out.print("Введите телефон: ");
        String phone = scanner.nextLine();
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите процент скидки: ");
        int discount = scanner.nextInt();
        scanner.nextLine();

        Client client = new Client(0, fullName, LocalDate.parse(birthDate), phone, email, discount);
        clientsDao.save(client);
        System.out.println("Новый клиент успешно добавлен.");
    }

    @Override
    public void update() {
        System.out.print("Введите ID клиента, которого хотите обновить: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Обновление клиента ID: " + id);
        System.out.print("Введите новое полное имя: ");
        String fullName = scanner.nextLine();
        System.out.print("Введите новую дату рождения (ГГГГ-ММ-ДД): ");
        String birthDate = scanner.nextLine();
        System.out.print("Введите новый телефон: ");
        String phone = scanner.nextLine();
        System.out.print("Введите новый email: ");
        String email = scanner.nextLine();
        System.out.print("Введите новый процент скидки: ");
        int discount = scanner.nextInt();
        scanner.nextLine();

        Client client = new Client(id, fullName, LocalDate.parse(birthDate), phone, email, discount);
        clientsDao.update(client);
        System.out.println("Информация о клиенте успешно обновлена.");
    }

    @Override
    public void delete() {
        System.out.print("Введите ID клиента, которого хотите удалить: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        clientsDao.delete(id);
        System.out.println("Клиент успешно удален.");
    }
}