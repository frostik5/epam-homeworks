package se05.task01;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;

/**
 * Разработать приложение, позволяющее просматривать файлы и каталоги файловой системы, а также создавать и удалять текстовые файлы.
 * Для работы с текстовыми файлами необходимо реализовать функциональность записи (дозаписи) в файл.
 * Требуется определить исключения для каждого слоя приложения и корректно их обработать.
 */
public class SystemExplorer {
    private static final String OS = System.getProperty("os.name");
    private static final File root = new File(OS.contains("windows") ? "C:/" : "/");
    private static final String sep = File.separator;
    private static File currentDir = new File(root.getAbsolutePath());

    public static void cd(String folder) {
        File tmp = new File(currentDir.getAbsolutePath()).getParentFile();
        if (folder.equals("..") && tmp != null) {
            currentDir = tmp;
        } else if (folder.equals("..") && tmp == null) {
            System.err.println("--> You are in a root folder!");
        } else {
            tmp = new File(currentDir + sep + folder);
            if (tmp.exists() && tmp.isDirectory()) {
                currentDir = tmp;
            } else {
                try {
                    throw new FileNotFoundException(tmp.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    System.err.println("--> Folder not found!");
                }
            }
        }
        getCurrentDir();
    }

    public static void dir() {
        List<File> files;
        files = new ArrayList<>(Arrays.asList(currentDir.listFiles()));

        if (files.size() == 0) {
            System.out.println("--> Folder is empty!");
            getCurrentDir();
            return;
        }

        int filesCounter = 0;
        int foldersCounter = 0;
        files.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println("Folders:");
        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println("\t" + f.getName());
                foldersCounter++;
            }
        }

        System.out.println("Files:");
        for (File f : files) {
            if (f.isFile()) {
                System.out.println("\t" + f.getName() + "\t" + f.length() + " bytes");
                filesCounter++;
            }
        }
        System.out.println("* Total items: " + files.size());
        System.out.println("* Folders: " + foldersCounter);
        System.out.println("* Files: " + filesCounter);
        getCurrentDir();
    }

    public static void mkDir(String name) {
        if (containsIllegalChars(name, true)) {
            getCurrentDir();
            return;
        }
        File dir = new File(currentDir.getAbsolutePath() + "/" + name);
        if (dir.exists()) {
            try {
                throw new FileAlreadyExistsException(dir.getAbsolutePath());
            } catch (FileAlreadyExistsException e) {
                System.err.println("--> Folder \"" + name + "\" already exists!");
            }
        } else {
            dir.mkdir();
            System.out.println("--> Folder \"" + name + "\" created!");
        }
        getCurrentDir();
    }

    public static void create(String name) {
        if (containsIllegalChars(name, false)) {
            getCurrentDir();
            return;
        }
        File file = new File(currentDir.getAbsolutePath() + sep + name);
        try {
            if (file.exists() && file.isFile()) {
                throw new FileAlreadyExistsException(file.getName());
            } else if (name.matches("[\\w]+(\\.[\\w]+)+")) {
                file.createNewFile();
                System.out.println("--> File \"" + name + "\" created!");
            } else {
                System.err.println("--> Wrong file name input!");
            }
        } catch (FileAlreadyExistsException e) {
            System.err.println("--> File \"" + name + "\" already exists!");
        } catch (IOException e) {
            System.err.println("--> Couldn't create file \"" + name + "\"! Probably you don't have administrator rights!");
        }
        getCurrentDir();
    }

    private static boolean containsIllegalChars(String name, boolean dir) {
        String[] illegalChars = {"\\", "/", ":", "*", "?", "\"", "<", ">", "|"};
        for (String ch : illegalChars) {
            if (name.contains(ch)) {
                if (dir)    System.err.println("--> Folder name should not contains the following symbols: \\ / : * ? \" < > |");
                else        System.err.println("--> File name should not contains the following symbols: \\ / : * ? \" < > |");
                return true;
            }
        }
        return false;
    }

    public static void delete(String name) {
        if (containsIllegalChars(name, false)) return;
        File file = new File(currentDir.getAbsolutePath() + sep + name);
        if (file.exists() && file.isFile()) {
            file.delete();
            System.out.println("--> File \"" + name + "\" deleted!");
        } else if (file.exists() && file.isDirectory()) {
            file.delete();
            System.out.println("--> Folder \"" + name + "\" deleted!");
        } else {
            try {
                throw new FileNotFoundException(file.getName());
            } catch (FileNotFoundException e) {
                System.err.println("--> File/folder \"" + name + "\" not found!");
            }
        }
        getCurrentDir();
    }

    public static void rewrite(String name, String text) {
        writeTo(name, text, true);
    }

    public static void addTo(String name, String text) {
        writeTo(name, text, false);
    }

    private static void writeTo(String name, String text, boolean rewrite) {
        File file = new File(currentDir.getAbsolutePath() + sep + name);
        if (file.exists() && file.isFile()) {
            if (rewrite) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(text);
                } catch (IOException e) {
                    System.err.println("--> Couldn't rewrite file \"" + name + "\"!");
                }
            } else {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                    writer.write(text);
                } catch (IOException e) {
                    System.err.println("--> Couldn't add text to file \"" + name + "\"!");
                }
            }
        }
        getCurrentDir();
    }

    public static void open(String name) {
        File file = new File(currentDir.getAbsolutePath() + sep + name);
        if (file.exists() && file.isFile()) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                while(reader.ready()) {
                    sb.append(reader.readLine());
                    sb.append(System.lineSeparator());
                }
                if (sb.toString().length() == 0) {
                    System.out.println("--> File \"" + name + "\" is empty!");
                } else {
                    System.out.println("=================================================================================================================================");
                    System.out.println("                                               " + name.toUpperCase() + " CONTENT");
                    System.out.println("=================================================================================================================================");
                    System.out.println(sb.toString());
                    System.out.println("=================================================================================================================================");
                }
            } catch (IOException e) {
                System.err.println("--> Couldn't open file \"" + name + "\"!");
            }
        }
        getCurrentDir();
    }

    public static void getCurrentDir() {
        System.out.print(currentDir + "> ");
    }

    public static void setCurrentDir(String currentDir) {
        File tmp = new File(currentDir);
        if (tmp.exists() && tmp.isDirectory()) {
            SystemExplorer.currentDir = tmp;
        } else {
            try {
                throw new FileNotFoundException(tmp.getAbsolutePath());
            } catch (FileNotFoundException e) {
                System.err.println("--> Directory not found!");
            }
        }
        getCurrentDir();
    }
}
