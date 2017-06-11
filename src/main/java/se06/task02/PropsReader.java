package se06.task02;

import se05.task02.NoSuchKeyException;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Создать “универсальный” класс, позволяющий получить значение из любого properties-файла.
 * Физическое чтение файла должно происходить только один раз. Результаты чтения храните в коллекции типа Map.
 *
 * Ответьте на вопрос: как ведет себя map-коллекция если в нее добавить элемент с ключом, который уже присутствует?
 * Ответ: если данный ключ содержит значение, которое совпадает со значением присутствующего ключа, ничего не меняется,
 * иначе значение перезаписывается.
 */
public class PropsReader {
    private Map<String, String> map;

    PropsReader() {
        map = new TreeMap<>();
    }

    public Map<String, String> getProperties(String propsPath) {
        return method(propsPath, null);
    }

    public Map<String, String> getProperties(String propsName, Object obj) {
        return method(propsName, obj);
    }

    public Map<String, String> method(String props, Object obj) {
        InputStream is;
        if (obj == null) {
            try {
                is = new FileInputStream(new File(props));
            } catch (FileNotFoundException e) {
                System.err.println("--> File not found!");
                return new TreeMap<>();
            }
        }
        else {
            is = obj.getClass().getResourceAsStream(props);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, "UTF-8"))) {
            String line;
            while (reader.ready()) {
                if ((line = reader.readLine()) != null) {
                    Pattern pattern = Pattern.compile("[\\s]*#");
                    Matcher matcher = pattern.matcher(line);
                    if (line.trim().length() == 0) continue;
                    if (matcher.find()) continue;

                    String[] words = line.trim().split("=");
                    String key = words[0].trim();
                    map.put(key, null);
                    String value;
                    if (words.length > 1) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 1; i < words.length; i++) {
                            sb.append(words[i]);
                        }
                        value = sb.toString().trim();
                        map.putIfAbsent(key, value);
                    }
                }
            }
            return map;

        } catch (FileNotFoundException e) {
            System.err.println("--> File not found!");
            return new TreeMap<>();
        } catch (IOException e) {
            System.err.println("--> Input error!");
            return new TreeMap<>();
        }
    }

    public String getValue(String key) {
        String value;
        if (!map.keySet().contains(key)) {
            throw new NoSuchKeyException(key);
        } else {
            value = map.get(key);
        }
        return value;
    }


}
