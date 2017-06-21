package se02.task05;

public enum Subject {
    COMPUTER_NETWORKS(true),
    JAVA_BASICS(true),
    ENGLISH(false),
    INFORMATION_SECURITY(false),
    DATA_BASES(true);

    private boolean intMark;
    Subject(boolean intMark) {
        this.intMark = intMark;
    }

    public boolean isIntMark() {
        return intMark;
    }
}


