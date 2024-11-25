import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PersonListApp {

    private static final String JSON_FILE = "person.json";
    
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void addNewPerson(Scanner scanner, List<Person> persons){
        System.out.println("Введите имя\n");
        String name = scanner.nextLine();
        System.out.println("Введите возраст\n");
        Integer age = Integer.valueOf(scanner.nextLine());
        persons.add(new Person(name, age));
        savePersonsToFile(JSON_FILE, persons);
    }

    public static void deletePerson(Scanner scanner, List<Person> persons){
        System.out.println("Введите имя\n");
        String name = scanner.nextLine();
        System.out.println("Введите возраст\n");
        Integer age = Integer.valueOf(scanner.nextLine());
        Person per = persons.stream().filter(x -> x.getName().equals(name) && x.getAge().equals(age))
                .findFirst().orElse(null);
        if (per == null) {
            System.out.println("Person с параметрами: " + name + " " + age + " не найден");
        } else {
            persons.remove(per);
            savePersonsToFile(JSON_FILE, persons);
        }
    }

    public static void updatePerson(Scanner scanner, List<Person> persons){
        System.out.println("Введите имя\n");
        String name = scanner.nextLine();
        System.out.println("Введите возраст\n");
        Integer age = Integer.valueOf(scanner.nextLine());
        Person per = persons.stream().filter(x -> x.getName().equals(name) && x.getAge().equals(age))
                .findFirst().orElse(null);
        if (per == null) {
            System.out.println("Person с параметрами: " + name + " " + age + " не найден");
        } else {
            System.out.println("Person найден. Введите новые значения:\n");
            System.out.println("Введите имя\n");
            String newName = scanner.nextLine();
            System.out.println("Введите возраст\n");
            Integer newAge = Integer.valueOf(scanner.nextLine());
            persons.remove(per);
            per.setAge(newAge);
            per.setName(newName);
            persons.add(per);
            savePersonsToFile(JSON_FILE, persons);
        }
        savePersonsToFile(JSON_FILE, persons);
    }

    public static void savePersonsToFile(String fileName, List<Person> persons) {
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            objectMapper.writeValue(new File(fileName), persons);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Person> loadPersonsFromFile(String fileName){
        List<Person> personList = new ArrayList<>();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                personList = objectMapper.readValue(file, objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, Person.class));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return personList;
    }

    public static void printPersons(List<Person> personList){
        System.out.println("Список Persons:\n");
        for (Person per : personList) {
            System.out.println("Имя: " + per.getName() + ", возраст: " + per.getAge());
        }
    }
}
