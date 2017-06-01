package se03.task01;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Необходимо создать класс CrazyLogger, ведущий журнал лога, используя как накопитель объект типа StringBuilder.
 * Логгер должен содержать методы поиска определенной информации в таком логе [с возможностью вывода ее в поток ввода вывода].
 * Информацию логгер хранит в форматированном виде: dd-mm-YYYY : hh-mm – message;.
 */
public class CrazyLogger {
    private static SimpleDateFormat sdf;
    private static String newLine;
    private StringBuilder log;
    private List<String> messages;

    CrazyLogger(){
        log = new StringBuilder("");
        sdf = new SimpleDateFormat("dd-MM-YYYY : hh-mm - ");
        newLine = System.lineSeparator();
    }

    public void add(String message) {
        log.append(sdf.format(new Date()))
           .append(message)
           .append(";")
           .append(newLine);
    }

    public String print() {
        if (log.length() != 0) return log.toString();
        else return "---Log is empty---";
    }

    public List<String> find(String text) {
        messages = new ArrayList<>(1000);
        if (log.length() != 0) {
            String[] logMessages = log.toString().trim().split(newLine);
            for (String s : logMessages) {
                if (s.contains(text)) {
                    messages.add(s);
                }
            }
        } else {
            messages.add("---Log is empty---");
        }

        return messages;
    }

    public void find(String text, OutputStream os) throws IOException {
        if (log.length() != 0) {
            String[] logMessages = log.toString().trim().split(newLine);
            for (String s : logMessages) {
                if (s.contains(text)) {
                    os.write(s.getBytes());
                    os.write(newLine.getBytes());
                }
            }
        } else {
            os.write("---Log is empty---".getBytes());
            os.write(newLine.getBytes());
        }
        os.flush();
        os.close();
    }
}
