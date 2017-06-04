package se04.task04;

import se04.task04.films.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Дана коллекция фильмов, содержащая информацию об актерах, снимавшихся в главных ролях
 * (один актер мог сниматься и в нескольких фильмах). Необходимо написать приложение, позволяющее при запуске
 * восстанавливать коллекцию фильмов, позволять ее модифицировать, а по завершении работы приложения – сохранять (в файл).
 * Для восстановления/сохранения коллекции использовать  сериализацию/десериализацию.
 */
public class Main {
    public static void main(String[] args) {
        FilmCollection fc = deserialize("src/in/FilmCollection.ser");
        System.out.println("---Deserialized file---");
        fc.print();

        fc.deleteActor(DjangoUnchained.filmName,"Квентин Тарантино");
        fc.addFilm(SinCity.filmName, SinCity.actors);
        fc.addFilm(RED2.filmName, RED2.actors);
        fc.deleteFilm(Inception.filmName);
        fc.deleteAllActors(DjangoUnchained.filmName);
        fc.deleteActors(SinCity.filmName, new ArrayList<>(Arrays.asList("Микки Рурк", "Майкл Кларк Дункан", "Брюс Уиллис")));
        System.out.println("---After modification---");
        fc.print();

        serialize(fc);
    }

    private static FilmCollection deserialize(String name) {
        FilmCollection fc = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name))) {
            fc = (FilmCollection) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return fc;
    }

    private static void serialize(FilmCollection fc) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/out/FilmCollection.ser"))) {
            oos.writeObject(fc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static TreeMap<String,List<String>> addFilms() {
        TreeMap<String, List<String>> films = new TreeMap<>();
        films.put(Inception.filmName, Inception.actors);
        films.put(DjangoUnchained.filmName, DjangoUnchained.actors);
        films.put(PulpFiction.filmName, PulpFiction.actors);

        return films;
    }
}
