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

    // [Рр]ис[унк]*.?\s*(\d{1,2}),?\s?и?\s?(\d{1,2})
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

    // [А-Я][^.?!]*([Рр]?ис[унк]*.?\s*(\d{1,2}),?\s?и?\s?(\d{1,2})?)\)?[^.?!(]*\(?([Рр]ис[унк]*.?\s*(\d{1,2}),?\s?и?\s?(\d{1,2})?)?\)?[^(.?!]*\(?([Рр]ис[унк]*.?\s*(\d{1,2}),?\s?и?\s?(\d{1,2})?)?\)?[^.?!]*[.!?]
    private static void findAllSentencesWithFigureWords(String file) {
        /**
         * Seems to be working fine, except when meets "э.д.с." string
         */
        String regEx =
                "[А-Я][^.?!]*" +
                "([Рр]?ис[унк]*.?\\s*(\\d{1,2}),?\\s?и?\\s?(\\d{1,2})?)\\)?" +  //Required Figure link
                "[^.?!(]*\\(?" +
                "([Рр]ис[унк]*.?\\s*(\\d{1,2}),?\\s?и?\\s?(\\d{1,2})?)?\\)?" +  //Optional figure links
                "[^(.?!]*\\(?" +                                                //Repeats the code above. Was trying to use ()*, but this highlights Figure links better
                "([Рр]ис[унк]*.?\\s*(\\d{1,2}),?\\s?и?\\s?(\\d{1,2})?)?\\)?" +
                "[^.?!]*[.!?]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(file);

        List<String> stringsWithFigureWords = new ArrayList<>();
        while(matcher.find()) {
            if (matcher.group() != null) { stringsWithFigureWords.add(matcher.group()); }
        }

        stringsWithFigureWords = getRidOfHMTLTags(stringsWithFigureWords);
        System.out.println();
        for (String s : stringsWithFigureWords) {
            System.out.println(s);
        }
    }

    private static List<String> getRidOfHMTLTags(List<String> stringsWithFigureWords) {
        StringBuilder sb;
        for (int i = 0; i < stringsWithFigureWords.size(); i++) {
            if (stringsWithFigureWords.get(i).contains("&nbsp;")) {
                sb = new StringBuilder();
                String[] s = stringsWithFigureWords.get(i).split("&nbsp;");
                for (String value : s) {
                    sb.append(value);
                }
                stringsWithFigureWords.set(i, sb.toString());
            }
            if (stringsWithFigureWords.get(i).contains("<span>")) {
                sb = new StringBuilder();
                String[] s = stringsWithFigureWords.get(i).split("<span>");
                for (String value : s) {
                    sb.append(value);
                }
                stringsWithFigureWords.set(i, sb.toString());
            }

            if (stringsWithFigureWords.get(i).contains("<sub>")) {
                sb = new StringBuilder();
                String[] s = stringsWithFigureWords.get(i).split("<sub>");
                for (String value : s) {
                    sb.append(value);
                }
                stringsWithFigureWords.set(i, sb.toString());
            }
            if (stringsWithFigureWords.get(i).contains("</sub>")) {
                sb = new StringBuilder();
                String[] s = stringsWithFigureWords.get(i).split("</sub>");
                for (String value : s) {
                    sb.append(value);
                }
                stringsWithFigureWords.set(i, sb.toString());
            }
        }

        return stringsWithFigureWords;
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
