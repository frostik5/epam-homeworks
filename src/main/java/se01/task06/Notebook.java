package se01.task06;

import java.util.Arrays;

/**     Representation of a Notebook.
 * <p>
 *      Contains a certain amount of <code>'notes'</code>, created when passing
 *      as a value into a <code>'se01.task06.Notebook'</code> constructor or by default.
 *      When created by default, the number of <code>'notes'</code> equals 100.
 *     </p>
 */
class Notebook {
    private Note[] notes;
    private int numberOfNotes;

    Notebook(int numberOfNotes) {
        this.numberOfNotes = numberOfNotes;
        notes = new Note[numberOfNotes];
        for (int i = 0; i < notes.length; i++) {
            notes[i] = new Note("");
        }
    }

    /**
     * Adds a note into a first non-null cell of <code>'notes'</code> array.
     *
     * @param note note text.
     */
    void addNote(String note) {
        for (int i = 0; i < notes.length; i++) {
            if (notes[i].getText().isEmpty()) {
                notes[i].setText(note);
                System.out.println("Добавлена запись #" + (i+1) + ": " + note);
                break;
            }
        }
    }

    /**
     * Adds a note into <code>'notes'</code> array by number.
     *
     * @param noteNumber number of a note;
     * @param note note text.
     */
    void addNote(int noteNumber, String note) {
        if (noteNumber < 1 || noteNumber > numberOfNotes-1) {
            System.out.println("Неверно задан # записи! Допустимый диапазон (1 <= # <=" + numberOfNotes + ").");
        } else if (!notes[noteNumber-1].getText().isEmpty()) {
            System.out.println("Запись с данным номером уже существует!");
        } else {
            notes[noteNumber-1].setText(note);
            System.out.println("Добавлена запись #" + noteNumber + ": " + note);
        }
    }

    /**
     * Deletes a note from <code>'notes'</code> array by number.
     *
     * @param noteNumber number of a note.
     */
    void deleteNote(int noteNumber) {
        if (noteNumber < 1 || noteNumber > numberOfNotes-1) {
            System.out.println("Неверно задан # записи! Допустимый диапазон (1 <= # <=" + numberOfNotes + ").");
        } else if (notes[noteNumber-1].getText().isEmpty()) {
            System.out.println("Нельзя удалить #" + noteNumber + "! Записи с данным номером не существует!");
        } else {
            notes[noteNumber-1].setText("");
            System.out.println("Запись #" + noteNumber + " удалена!");
        }
    }

    /**
     * Edit a note in a <code>'notes'</code> array by number.
     *
     * @param noteNumber number of a note;
     * @param newText new note edition.
     */
    void editNote(int noteNumber, String newText) {
        if (noteNumber < 1 || noteNumber > numberOfNotes-1) {
            System.out.println("Неверно задан # записи! Допустимый диапазон (1 <= # <=" + numberOfNotes + ").");
        } else if (notes[noteNumber-1].getText().isEmpty()) {
            System.out.println("Нельзя редактировать #" + noteNumber + "! Записи с данным номером не существует!");
        } else {
            notes[noteNumber-1].setText(newText);
        }
    }

    /**
     * Console output of all existing notes in a <code>'notes'</code> array.
     */
    void showAllNotes() {
        if (Arrays.toString(notes).isEmpty())
            System.out.println("Записи в блокноте отсутствуют!");
        else {
            StringBuilder str = new StringBuilder(notes.length);
            str.append("\n******************** РАСПЕЧАТКА ЗАПИСЕЙ ***********************\n");
            for (int i = 0; i < notes.length; i++) {
                if (!notes[i].getText().isEmpty()) {
                    str.append("#");
                    str.append(i+1);
                    str.append(": \"");
                    str.append(notes[i].getText());
                    str.append("\"\n");
                }
            }
            str.append("***************************************************************\n\n");
            System.out.print(str);
        }
    }
}
