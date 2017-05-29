package se03.task01;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        CrazyLogger log = createMessages();
        //System.out.println(log.print());
        findMessages(log);
    }

    private static CrazyLogger createMessages() {
        CrazyLogger log = new CrazyLogger();
        for (int i = 1; i <= 1_000_000; i++) {
            log.add(String.valueOf(i));
        }
        return log;
    }

    private static void findMessages(CrazyLogger log) {
        /*
        List<String> messages = log.find(String.valueOf(1000));
        for (String s : messages) {
        System.out.println(s);
        }
        */

        try {
            OutputStream bos = new BufferedOutputStream(new FileOutputStream("src/main/java/se03/task01/messages.txt"));
            log.find(String.valueOf(10000), bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
