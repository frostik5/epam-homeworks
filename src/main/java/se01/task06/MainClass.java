package se01.task06;

/**
 * @author Stanislav Radichenko
 **/
public class MainClass {
    public static void main(String[] args) {
        executeUserDialogue(new Notebook(100));
    }

    private static void executeUserDialogue(Notebook nb) {
        nb.addNote("Javadoc — генератор документации в HTML-формате из комментариев исходного кода");
        nb.addNote("на Java от Sun Microsystems. Javadoc — стандарт для документирования классов Java. ");
        nb.showAllNotes();
        nb.deleteNote(1);
        nb.deleteNote(-5);
        nb.addNote("Большинство сред разработки программного обеспечения автоматически генерируют HTML-документацию, используя Javadoc.");
        nb.addNote("Javadoc также предоставляет API для создания доклетов и тэглетов, которые позволяют программисту анализировать структуру Java-приложения.");
        nb.addNote("Комментарии документации применяют для:");
        nb.addNote(40,"- документирования классов,");
        nb.addNote(45,"- интерфейсов,");
        nb.addNote(46,"- полей (переменных),");
        nb.addNote(47,"- конструкторов,");
        nb.addNote(48,"- методов,");
        nb.addNote(70,"- пакетов.");
        nb.showAllNotes();
        nb.deleteNote(44);
        nb.deleteNote(40);
        nb.showAllNotes();
        nb.editNote(35, "new text");
        nb.editNote(2, "Let's try to put this text into #2");
        nb.showAllNotes();
    }
}
