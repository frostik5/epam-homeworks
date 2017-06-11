package se05.task02;

import java.util.Enumeration;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        PropsReader pr = new PropsReader();
        Properties properties = pr.getProperties("some.properties", new Main());
        //Properties properties = pr.getProperties("src/main/resources/se05/task02/some.properties");
        //Properties properties = pr.getProperties("Quiz_en.properties", new QuizzApp());
        //Properties properties = pr.getProperties("src/main/resources/se03/task02/Quiz_ru.properties");

        //System.out.println(pr.getValue("key3"));
        //System.out.println(pr.getValue("key10"));     //NoSuchKeyException
        //System.out.println(pr.getValue("key15"));

        print(properties);
    }
    private static void print(Properties properties) {
        System.out.println("KEYS: ");
        System.out.println(properties.keySet());
        System.out.println();

        System.out.println("VALUES: ");
        Enumeration em = properties.keys();
        while(em.hasMoreElements()){
            String str = (String)em.nextElement();
            System.out.println(str + ": " + properties.get(str));
        }
    }
}
