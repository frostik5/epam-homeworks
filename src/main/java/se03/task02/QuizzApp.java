package se03.task02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Разработать приложение, позволяющее по выбору пользователя использовать русский или английский язык, для отображения информации.
 * Приложение должно представлять собой перечень вопросов под номерами (на английском или русском соответственно).
 * Выбрав номер вопроса пользователь может узнать ответ на него.
 */
public class QuizzApp {
    private static Locale[] locales = {
            Locale.ENGLISH,
            Locale.forLanguageTag("ru")
    };


    public static void main(String[] args) throws Exception {
        Locale language = chooseLanguage();
        ResourceBundle rb = ResourceBundle.getBundle("se03/task02/Quiz", language);
        showQuestions(rb, language.getLanguage());
    }

    private static Locale chooseLanguage() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lang;
        System.out.print("Choose quiz language:\n1 - English;\n2 - Russian.\nYour choice: ");
        while (true) {
            lang = br.readLine();
            if (!lang.isEmpty()) {
                if (lang.equals("1")) {
                    return locales[0];
                } else if (lang.equals("2")) {
                    return locales[1];
                }
            }
            System.out.print("Wrong input. Please try again: ");
        }
    }

    private static void showQuestions(ResourceBundle rb, String language) throws Exception {
        for (int i = 1; i <= rb.keySet().size(); i++) {
            if (rb.containsKey("Q" + i)) {
                System.out.println("\n" + rb.getString("Q" + i));
            }
        }

        switch (language) {
            case "en": { System.out.print("\nThe answer to the question you want to see: "); break; }
            case "ru": { System.out.print("\nОтвет на какой вопрос Вас интересует: "); break; }
        }

        typeNumberOfQuestion(rb, language);
    }

    private static void typeNumberOfQuestion(ResourceBundle rb, String language) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while (true) {
            str = br.readLine();
            if (!str.isEmpty() && str.length() < 2) {
                if (str.charAt(0) >= '1' && str.charAt(0) <= '5') {
                    System.out.println(rb.getString("A" + str));
                    break;
                }
            }
            if (language.equals("en")) {
                System.out.print("Wrong input. Please try again: ");
            } else if (language.equals("ru")) {
                System.out.print("Неправильный ввод. Попробуйте ещё раз: ");
            }
        }
    }

}
