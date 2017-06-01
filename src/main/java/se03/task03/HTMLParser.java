package se03.task03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Необходимо определить в тексте статьи (html-файл), ссылается ли автор на рисунки последовательно или нет,
 * а также выделить все предложения, в которых встречаются ссылки на рисунки.
 * Для разбора текста использовать регулярные выражения.
 */
public class HTMLParser {

    public static void main(String[] args) throws Exception {
        String file = readFile();
        //System.out.println(file);
        List<Integer> figureLinks = findAllFigureWords(file);
        checkIfFigureLinksAresSequential(figureLinks);
        findAllSentencesWithFigureWords(file);
    }

    private static List<Integer> findAllFigureWords(String file) {
        /**
         * It's got to be links only though, not Figures themselves. The problem is there are no difference in HTML tags for strings with links and under-figure strings.
         * Usual text should't be aligned to middle, unlike under-figure-text...
         * Or it could be possible to count links, if usual text blocks (<div>) would be sequential to under-figure-text blocks, which is not the case here again.
         */
        String regEx = "[Рр]ис[унк]*.?\\s*(\\d{1,2}),?\\s?и?\\s?(\\d{1,2})?";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(file);

        List<Integer> figureLinks = new ArrayList<>();
        while(matcher.find()) {
            if (matcher.group(1) != null) { figureLinks.add(Integer.parseInt(matcher.group(1))); }
            if (matcher.group(2) != null) { figureLinks.add(Integer.parseInt(matcher.group(2))); }
        }
        System.out.println(figureLinks);

        return figureLinks;
    }

    private static void checkIfFigureLinksAresSequential(List<Integer> figureLinks) {
        for (int i = 0; i < figureLinks.size() - 1; i++) {
            if (!figureLinks.get(i).equals(figureLinks.get(i + 1))) {
                System.out.println("The figure links are not sequential!");
                return;
            }
        }
        System.out.println("The figure links are sequential!");
    }

    private static void findAllSentencesWithFigureWords(String file) {  // Not working correctly at the moment
        String regEx = "[А-Я][^\\.?]*([Рр]ис[унк]*.?\\s*(\\d{1,2}),?\\s?и?\\s?(\\d{1,2})?)-?[а-д]?,?[а-д]?\\)?[^\\.?]*([Рр]?ис?[унк]*.?\\s*(\\d{1,2})?,?\\s?и?\\s?(\\d{1,2})?)[.?]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(file);

        List<String> stringsWithFigureWords = new ArrayList<>();
        while(matcher.find()) {
            if (matcher.group() != null) { stringsWithFigureWords.add(matcher.group()); }
        }

        for (String stringsWithFigureWord : stringsWithFigureWords) {
            System.out.println(stringsWithFigureWord);
        }
    }

    private static String readFile() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(getResource("Java.SE.03.Information handling_task_attachment.html"))) {
            while (br.ready()) {
                sb.append(br.readLine());
                sb.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static InputStreamReader getResource(String name) {
        Class<HTMLParser> cls = HTMLParser.class;
        try {
            return new InputStreamReader(cls.getResourceAsStream(name), "windows-1251");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new InputStreamReader(cls.getResourceAsStream(name));
    }

}
