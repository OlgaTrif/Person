import java.io.*;

/**
 * Задание 1: Создайте класс Person с полями name и age. Реализуйте сериализацию и десериализацию этого класса в файл.
 */

public class SerialiseAndDeserialise {
    public static void main(String[] args) {
        Person person = new Person("Ivan", 23);

        try (FileOutputStream fos = new FileOutputStream("person.bin")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(person);
            System.out.println("Успешно сериализован");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileInputStream fis = new FileInputStream("person.bin")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            person = (Person)ois.readObject();
            System.out.println("Успешно сериализован");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Успешно десериализован");
        System.out.println("Имя: " + person.getName());
        System.out.println("Возраст: " + person.getAge());
        System.out.println();
    }
}