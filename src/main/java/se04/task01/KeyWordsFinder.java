package se04.task01;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Прочитайте файл, содержащий код на языке Java. Определите, какие ключевые слова языка Java это код содержит.
 * Выведите эти слова и их количество в другой файл. Используйте только байтовые потоки ввода-вывода.
 */
public class KeyWordsFinder {
    private String file;
    private Map<String, Integer> keysMap;

    public KeyWordsFinder() {
        this("src/main/java/se03/task02/QuizzApp.java");
    }

    public KeyWordsFinder(String fileDestination) {
        readFile(fileDestination);
        findKeyWords();
        writeFoundKeysToFile();
    }

    private void readFile(String fileDestination) {
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(fileDestination))) {
            try (ByteArrayOutputStream bais  = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int b;
                while ((b = reader.read(buffer)) != -1) {
                    bais.write(buffer, 0, b);
                }
                file = bais.toString("UTF-8");
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
        try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("src/out/se04_task01_output.txt"))) {
            for (Map.Entry item : keysMap.entrySet()) {
                bos.write((item.getKey() + ": " + item.getValue() + System.lineSeparator()).getBytes());
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
