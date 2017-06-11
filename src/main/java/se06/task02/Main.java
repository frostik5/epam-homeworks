package se06.task02;

import java.util.Map;
import se03.task02.QuizzApp;

public class Main {
    public static void main(String[] args) {
        PropsReader pr = new PropsReader();
        Map<String, String> map = pr.getProperties("src/main/resources/se05/task02/some.properties");
        //Map<String, String> map = pr.getProperties("Quiz_en.properties", new QuizzApp());
        //Map<String, String> map = pr.getProperties("src/main/resources/se03/task02/Quiz_ru.properties");

        //System.out.println(pr.getValue("key3"));
        //System.out.println(pr.getValue("key10"));     //NoSuchKeyException
        //System.out.println(pr.getValue("key15"));

        print(map);
    }
    private static void print(Map<String, String> map) {
        for (Map.Entry item : map.entrySet()) {
            System.out.println("Key: " + item.getKey() + ",\t value: " + item.getValue());
        }
    }
}
