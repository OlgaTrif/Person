/***
 * Задание 2: Используя JPA, создайте базу данных для хранения объектов класса Person.
 * Реализуйте методы для добавления, обновления и удаления объектов Person.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonJPAApp {
    private static final String FILE_JSON = "person.json";

    public static void main(String[] args) {
        List<Person> persons;
        File f = new File(FILE_JSON);
        if (f.exists() && !f.isDirectory()){
            persons = load();
        } else {
            persons = preparePersons();
            save(persons);
        }
        printPersonList(persons);
        Scanner scanner = new Scanner(System.in);
        showMainMenu();
        String choise = scanner.nextLine();
        switch (choise) {
            case "1":
                PersonListApp.addNewPerson(scanner, persons);
                break;
            case "2":
                PersonListApp.updatePerson(scanner, persons);
                break;
            case "3":
                PersonListApp.deletePerson(scanner, persons);
                break;
            case "4":
                save(persons);
                System.exit(0);
            default:
                System.out.println("Проверьте правильность введения команды");
                break;
        }
    }

    public static List<Person> preparePersons(){
        List<Person> list = new ArrayList<>();
        list.add(new Person("Petr", 16));
        list.add(new Person("Anna", 40));
        list.add(new Person("Luka", 21));
        return list;
    }

    private static void save(List<Person> list){
        PersonListApp.savePersonsToFile(FILE_JSON, list);
    }

    private static List<Person> load(){
        return PersonListApp.loadPersonsFromFile(FILE_JSON);
    }

    private static void printPersonList(List<Person> list){
        PersonListApp.printPersons(list);
    }

    private static void showMainMenu(){
        System.out.println("Выберите действие: \n");
        System.out.println("1 - добавить человека");
        System.out.println("2 - редактировать данные ");
        System.out.println("3 - удалить данные ");
        System.out.println("4 - выход ");
    }
}
