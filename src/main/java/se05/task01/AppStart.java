package se05.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static se05.task01.SystemExplorer.*;

public class AppStart {
    public static void main(String[] args) {
       executeUserDialogue();
    }

    private static void executeUserDialogue() {
        getCurrentDir();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line;
            while (true) {
                if ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    String command = words[0].toLowerCase();
                    StringBuilder fileSB;

                    if (words.length == 1) {
                        switch (command) {
                            case "dir":  dir(); break;
                            case "cd..": cd(".."); break;
                            case "help":
                            case "?":    showHelp(); break;
                            case "exit":
                            case "quit": System.exit(0); break;
                            default:     System.err.println("--> Incorrect command! Try \"help\" or \"?\" to watch the full list of supporterd commands."); break;
                        }
                    } else if (words.length > 1) {
                        fileSB = new StringBuilder();
                        for (int i = 1; i < words.length; i++) {
                            if (i < words.length - 1) {
                                fileSB.append(words[i]);
                                fileSB.append(" ");
                            } else {
                                fileSB.append(words[i]);
                            }
                        }
                        String file = fileSB.toString();
                        String text;

                        switch (command) {
                            case "cd":      cd(file); break;
                            case "mkdir":   mkDir(file); break;
                            case "create":  create(file); break;
                            case "delete":  delete(file); break;
                            case "open":    open(file); break;
                            case "setdir":  setCurrentDir(words[1]); break;
                            case "rewrite": text = typeText(reader); rewrite(file, text); break;
                            case "addto":   text = typeText(reader); addTo(file, text); break;
                            default:        System.err.println("--> Incorrect command! Try \"help\" or \"?\" to watch the full list of supporterd commands."); break;
                        }
                    } else {
                        System.err.print("Input error! Please try again: ");
                    }
                } else {
                    System.err.print("Input error! Please try again: ");
                }
            }
        } catch (IOException e) {
            System.err.print("Input error! Please try again: ");
        }
    }

    private static String typeText(BufferedReader reader) {
        String text;
        StringBuilder textSB = new StringBuilder();
        System.out.println("--> Please enter your text here (type \"end\" on a new line to stop):");
        try {
            while (true) {
                if ("end".equals(text = reader.readLine())) break;
                else  {
                    textSB.append(text);
                    textSB.append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.err.println("--> Input error!");
        }
        return textSB.toString();
    }

    private static void showHelp() {
        System.out.println("=================================================================================================================================");
        System.out.println("                                              LIST OF ALL SUPPORTED COMMANDS");
        System.out.println("=================================================================================================================================");
        System.out.println("dir                          - shows list of all files and folders of current directory;");
        System.out.println("cd [folder_name]             - goes to folder [folder_name] from current directory, use command \"..\" or \"cd..\" to go up one level;");
        System.out.println("mkdir [folder_name]          - creates a folder [folder_name] in current directory;");
        System.out.println("create [file_name.extension] - creates a [file_name.extension] file in current directory;");
        System.out.println("delete [name]                - deletes a [file_name.extension] or a [folder_name] file/folder from current directory;");
        System.out.println("setdir [directory]           - sets current directory to [directory];");
        System.out.println("open [file_name.extension]   - opens file [file_name.extension] from current directory;");
        System.out.println("rewrite [file_name]          - rewrites a [file_name] file with the text you enter;");
        System.out.println("addto [file_name]            - adds to a [file_name] file text you enter;");
        System.out.println("help or ?                    - show list of all supported commands;");
        System.out.println("exit or quit                 - exit the program.");
        System.out.println("=================================================================================================================================");
        getCurrentDir();
    }
}
