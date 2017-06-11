package se05.task02;

import java.io.*;
import java.util.Properties;

/**
 * Создать “универсальный” класс, позволяющий получить значение из любого properties-файла.
 * Физическое чтение файла должно происходить только один раз. Обработайте следующие исключительные ситуации:
 * нет файла *.properties, нет ключа в properties-файле.
 */
public class PropsReader {
    private Properties properties;

    PropsReader() {
        properties = new Properties();
    }

    public Properties getProperties(String propsPath) {
        return method(propsPath, null);
    }

    public Properties getProperties(String propsName, Object obj) {
        return method(propsName, obj);
    }

    public Properties method(String props, Object obj) {
        InputStream is;
        if (obj == null) {
            try {
                is = new FileInputStream(new File(props));
            } catch (FileNotFoundException e) {
                System.err.println("--> File not found!");
                return new Properties();
            }
        }
        else {
            is = obj.getClass().getResourceAsStream(props);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, "UTF-8"))) {
            properties.load(reader);
            return properties;
        } catch (FileNotFoundException e) {
            System.err.println("--> File not found!");
            return new Properties();
        } catch (IOException e) {
            System.err.println("--> Input error!");
            return new Properties();
        }
    }

    public String getValue(String key) {
        String value;
        if (!properties.keySet().contains(key)) {
            throw new NoSuchKeyException(key);
        } else {
            value = properties.getProperty(key);
        }
        return value;
    }


}
