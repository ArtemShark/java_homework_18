package org.example.menu;

import org.example.drinkDAO.DrinksDaoImpl;
import org.example.model.Drink;

import java.math.BigDecimal;
import java.util.List;

import java.util.Scanner;
public class DrinksManager implements Manager {
    private DrinksDaoImpl drinksDao;
    private Scanner scanner;

    public DrinksManager(DrinksDaoImpl drinksDao) {
        this.drinksDao = drinksDao;
        this.scanner = new Scanner(System.in);
    }

    public void manage() {
        while (true) {
            System.out.println("\nВыберите действие для таблицы 'Drinks':");
            System.out.println("1. Показать все напитки");
            System.out.println("2. Добавить новый напиток");
            System.out.println("3. Добавить новый вид кофе");
            System.out.println("4. Изменить напиток");
            System.out.println("5. Изменить название кофе");
            System.out.println("6. Удалить напиток");
            System.out.println("7. Выход");
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
                    addNewCoffee();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    updateCoffeeName();
                    break;
                case 6:
                    delete();
                    break;
                case 7:
                    System.out.println("Выход из программы...");
                    return;
                default:
                    System.out.println("Неверный ввод, попробуйте еще раз.");
                    break;
            }
        }
    }

    @Override
    public void showAll() {
        List<Drink> drinks = drinksDao.findAll();
        if (drinks.isEmpty()) {
            System.out.println("Список напитков пуст.");
        } else {
            for (Drink drink : drinks) {
                System.out.println(drink.getDrinkId() + ": " + drink.getNameEN() + " / " + drink.getNameOtherLanguage() + " - " + drink.getPrice());
            }
        }
    }

    @Override
    public void addNew() {
        System.out.println("Введите название напитка на английском:");
        String nameEN = scanner.nextLine();
        System.out.println("Введите название напитка на другом языке:");
        String nameOtherLanguage = scanner.nextLine();
        System.out.println("Введите цену напитка:");
        BigDecimal price = scanner.nextBigDecimal();
        Drink drink = new Drink(0, nameEN, nameOtherLanguage, price);
        drinksDao.save(drink);
        System.out.println("Напиток добавлен.");
    }

    public void addNewCoffee() {
        System.out.println("Введите название кофе на английском:");
        String nameEN = scanner.nextLine();
        System.out.println("Введите название кофе на другом языке:");
        String nameOtherLanguage = scanner.nextLine();
        System.out.println("Введите цену кофе:");
        BigDecimal price = scanner.nextBigDecimal();
        Drink coffee = new Drink(0, nameEN, nameOtherLanguage, price);
        drinksDao.saveCoffee(coffee);
        System.out.println("Новый вид кофе добавлен.");
    }

    @Override
    public void update() {
        System.out.println("Введите ID напитка, который нужно обновить:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите новое название напитка на английском:");
        String nameEN = scanner.nextLine();
        System.out.println("Введите новое название напитка на другом языке:");
        String nameOtherLanguage = scanner.nextLine();
        System.out.println("Введите новую цену напитка:");
        BigDecimal price = scanner.nextBigDecimal();
        Drink drink = new Drink(id, nameEN, nameOtherLanguage, price);
        drinksDao.update(drink);
        System.out.println("Напиток обновлен.");
    }
    @Override
    public void delete() {
        System.out.println("Введите ID напитка, который нужно удалить:");
        int id = scanner.nextInt();
        drinksDao.delete(id);
        System.out.println("Напиток удален.");
    }

    public void updateCoffeeName() {
        System.out.print("Введите ID напитка, название которого вы хотите изменить: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите новое название напитка на английском: ");
        String newNameEN = scanner.nextLine();
        System.out.print("Введите новое название напитка на другом языке: ");
        String newNameOtherLanguage = scanner.nextLine();

        drinksDao.updateCoffeeName(id, newNameEN, newNameOtherLanguage);
        System.out.println("Название напитка успешно изменено.");
    }
}

