package se01.task06;

/**     Representation of a text in a Notebook.
 * <p>
 *      Contains text of a text in a notebook.
 *     </p>
 */

class Note {
    private String text;

    Note(String text) {
        this.text = text;
    }

    String getText() {
        return text;
    }

    void setText(String text) {
        this.text = text;
    }
}
