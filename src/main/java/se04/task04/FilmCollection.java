package se04.task04;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Дана коллекция фильмов, содержащая информацию об актерах, снимавшихся в главных ролях
 * (один актер мог сниматься и в нескольких фильмах). Необходимо написать приложение, позволяющее при запуске
 * восстанавливать коллекцию фильмов, позволять ее модифицировать, а по завершении работы приложения – сохранять (в файл).
 * Для восстановления/сохранения коллекции использовать  сериализацию/десериализацию.
 */
public class FilmCollection implements Serializable {
    private Map<String, List<String>> films;

    FilmCollection() {
        films = new TreeMap<>();
    }

    public void add(TreeMap<String, List<String>> films) {
        this.films = films;
    }

    public void addFilm(String film) {
        films.put(film, null);
    }

    public void addFilm(String film, List<String> actors) {
        films.put(film, actors);
    }

    public void deleteFilm(String film) {
        films.remove(film);
    }

    public void deleteFilms(List<String> films) {
        for (String film : films) {
            if (this.films.containsKey(film)) {
                this.films.remove(film);
            }
        }
    }

    public void deleteAllActors(String film) {
        addFilm(film);
    }

    public void addActor(String film, String actor) {
        films.get(film).add(actor);
    }

    public void addActors(String film, List<String> actors) {
        films.get(film).addAll(actors);
    }

    public void deleteActor(String film, String actor) {
        for (int i = 0; i < films.get(film).size(); i++) {
            if (films.get(film).get(i).contains(actor)) {
                films.get(film).remove(i);
            }
        }
    }

    public void deleteActors(String film, List<String> actors) {
        for (int i = 0; i < films.get(film).size(); i++) {
            for (String actor : actors) {
                if (films.get(film).get(i).equals(actor)) {
                    films.get(film).remove(i);
                }
            }
        }
    }

    public void clear() {
        films.clear();
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> item : films.entrySet()) {
            System.out.println(item.getKey() + ":");
            if (item.getValue() != null) {
                for (int i = 0; i < item.getValue().size(); i++) {
                    System.out.println("\t" + item.getValue().get(i));
                }
            }
            System.out.println();
        }
    }
}
