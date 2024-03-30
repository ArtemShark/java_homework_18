package org.example.menu;

import org.example.staffDAO.StaffDaoImpl;
import org.example.model.Staff;

import java.util.List;

import java.util.Scanner;
public class StaffManager implements Manager {
    private StaffDaoImpl staffDao;
    private Scanner scanner;

    public StaffManager(StaffDaoImpl staffDao) {
        this.staffDao = staffDao;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void manage() {
        while (true) {
            System.out.println("\nУправление персоналом:");
            System.out.println("1. Показать весь персонал");
            System.out.println("2. Добавить нового сотрудника");
            System.out.println("3. Изменить информацию сотрудника");
            System.out.println("4. Удалить сотрудника");
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
        List<Staff> staffList = staffDao.findAll();
        if (staffList.isEmpty()) {
            System.out.println("Список персонала пуст.");
        } else {
            for (Staff staff : staffList) {
                System.out.println(staff.getStaffId() + ": " + staff.getFullName() + ", " + staff.getPosition());
            }
        }
    }

    @Override
    public void addNew() {
        System.out.println("Добавление нового сотрудника:");
        System.out.print("Введите полное имя: ");
        String fullName = scanner.nextLine();
        System.out.print("Введите телефон: ");
        String phone = scanner.nextLine();
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите позицию: ");
        String position = scanner.nextLine();

        Staff staff = new Staff(0, fullName, phone, email, position);
        staffDao.save(staff);
        System.out.println("Новый сотрудник успешно добавлен.");
    }

    @Override
    public void update() {
        System.out.print("Введите ID сотрудника, которого хотите обновить: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Обновление сотрудника ID: " + id);
        System.out.print("Введите новое полное имя: ");
        String fullName = scanner.nextLine();
        System.out.print("Введите новый телефон: ");
        String phone = scanner.nextLine();
        System.out.print("Введите новый email: ");
        String email = scanner.nextLine();
        System.out.print("Введите новую позицию: ");
        String position = scanner.nextLine();

        Staff staff = new Staff(id, fullName, phone, email, position);
        staffDao.update(staff);
        System.out.println("Информация о сотруднике успешно обновлена.");
    }

    @Override
    public void delete() {
        System.out.print("Введите ID сотрудника, которого хотите удалить: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        staffDao.delete(id);
        System.out.println("Сотрудник успешно удален.");
    }

}