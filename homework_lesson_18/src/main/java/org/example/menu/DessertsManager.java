package org.example.menu;

import org.example.dessertDAO.DessertsDaoImpl;
import org.example.model.Dessert;

import java.math.BigDecimal;
import java.util.List;

import java.util.Scanner;
public class DessertsManager implements Manager {
    private DessertsDaoImpl dessertsDao;
    private Scanner scanner;

    public DessertsManager(DessertsDaoImpl dessertsDao) {
        this.dessertsDao = dessertsDao;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void manage() {
        while (true) {
            System.out.println("\nУправление десертами:");
            System.out.println("1. Показать все десерты");
            System.out.println("2. Добавить новый десерт");
            System.out.println("3. Изменить десерт");
            System.out.println("4. Удалить десерт");
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
        List<Dessert> desserts = dessertsDao.findAll();
        if (desserts.isEmpty()) {
            System.out.println("Список десертов пуст.");
        } else {
            for (Dessert dessert : desserts) {
                System.out.println(dessert.getDessertId() + ": " + dessert.getNameEN() + " / " + dessert.getNameOtherLanguage() + " - " + dessert.getPrice());
            }
        }
    }

    @Override
    public void addNew() {
        System.out.println("Введите название десерта на английском:");
        String nameEN = scanner.nextLine();
        System.out.println("Введите название десерта на другом языке:");
        String nameOtherLanguage = scanner.nextLine();
        System.out.println("Введите цену десерта:");
        BigDecimal price = scanner.nextBigDecimal();
        Dessert dessert = new Dessert(0, nameEN, nameOtherLanguage, price);
        dessertsDao.save(dessert);
        System.out.println("Десерт добавлен.");
    }

    @Override
    public void update() {
        System.out.println("Введите ID десерта, который нужно обновить:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите новое название десерта на английском:");
        String nameEN = scanner.nextLine();
        System.out.println("Введите новое название десерта на другом языке:");
        String nameOtherLanguage = scanner.nextLine();
        System.out.println("Введите новую цену десерта:");
        BigDecimal price = scanner.nextBigDecimal();
        Dessert dessert = new Dessert(id, nameEN, nameOtherLanguage, price);
        dessertsDao.update(dessert);
        System.out.println("Десерт обновлен.");
    }

    @Override
    public void delete() {
        System.out.println("Введите ID десерта, который нужно удалить:");
        int id = scanner.nextInt();
        dessertsDao.delete(id);
        System.out.println("Десерт удален.");
    }
}