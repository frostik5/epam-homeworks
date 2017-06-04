package se04.task02;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Прочитайте файл, содержащий код на языке Java. Определите, какие ключевые слова языка Java это код содержит.
 * Выведите эти слова и их количество в другой файл. Используйте только символьные потоки ввода-вывода.
 */
public class KeyWordsFinder {
    private String file;
    private Map<String, Integer> keysMap;

    public KeyWordsFinder() {
        this("src/main/java/se01/task06/Notebook.java");
    }

    public KeyWordsFinder(String fileDestination) {
        readFile(fileDestination);
        findKeyWords();
        writeFoundKeysToFile();
    }

    private void readFile(String fileDestination) {
        try {
            try (BufferedReader bis = new BufferedReader(new FileReader(fileDestination))) {
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = bis.readLine()) != null) {
                    sb.append(line);
                }
                file = sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findKeyWords() {
        keysMap = new TreeMap<>();

        List<String> javaKeyWords = readKeysFromResources();

        int value;
        Pattern pattern;
        Matcher matcher;
        for (String key : javaKeyWords) {
            value = 0;
            pattern = Pattern.compile("[\\D]?" + key + "\\(?\\s+");
            matcher = pattern.matcher(file);
            while(matcher.find()) {
                keysMap.put(key, ++value);
            }
        }
    }

    private List<String> readKeysFromResources() {
        Path path;
        List<String> keyWords = new ArrayList<>();
        try {
            path = Paths.get(getResource("../Java_key_words.txt"));
            keyWords = Files.readAllLines(path);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return keyWords;
    }

    private URI getResource(String name) throws URISyntaxException {
        Class<KeyWordsFinder> cls = KeyWordsFinder.class;
        return cls.getResource(name).toURI();
    }

    private void writeFoundKeysToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/out/se04_task02_output.txt"))) {
            for (Map.Entry item : keysMap.entrySet()) {
                writer.write((item.getKey() + ": " + item.getValue() + System.lineSeparator()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getKeysMap() {
        return keysMap;
    }

    @Override
    public String toString() {
        if (file.isEmpty()) {
            System.out.println("{---File is empty!---}");
        }
        return keysMap.toString();
    }

    public static void main(String[] args) {
        KeyWordsFinder kwc = new KeyWordsFinder();
        System.out.println(kwc.toString());
    }
}
