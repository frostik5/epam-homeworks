package se04.task03;

import java.io.*;

/**
 * Дан файл, содержащий буквы текст на кириллице. Кодировка файла utf-8.
 * Прочитайте информацию из файла и перепишите ее в файл в кодировкой utf-16.
 */
public class CodingConverter {
    private String file;

    CodingConverter() {
        readFileFromResources();
        convertToUTF16();
    }

    private void readFileFromResources() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(getResource("Когда_расцветают_бомбы.txt"))) {
            while (br.ready()) {
                sb.append(br.readLine());
                sb.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = sb.toString();
    }

    public void printFile() {
        if (!file.isEmpty()) {
            System.out.println(file);
        } else {
            System.out.println("---File is empty!---");
        }
    }

    private static InputStreamReader getResource(String name) throws UnsupportedEncodingException {
        Class<CodingConverter> cls = CodingConverter.class;
        return new InputStreamReader(cls.getResourceAsStream(name), "UTF-8");
    }

    private void convertToUTF16() {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/out/se04_task03_UTF-16_file.txt"), "UTF-16"))) {
            writer.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("---File successfully converted to UTF-16!---");
    }

    public static void main(String[] args) {
        CodingConverter cc = new CodingConverter();
        //cc.printFile();
    }
}
