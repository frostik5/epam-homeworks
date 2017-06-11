package se05.task02;

public class NoSuchKeyException extends RuntimeException {
    public NoSuchKeyException(String key) {
        System.err.println("--> Key \"" + key + "\" not found!");
    }
}
