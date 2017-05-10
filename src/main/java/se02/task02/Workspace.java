package se02.task02;

public class Workspace {
    private Pen[] pens;
    private Pencil[] pencils;
    private Eraser[] erasers;
    private Notebook[] notebooks;
    private DotMatrixPaper[] matrixPapers;

    private Workspace(int pensNum, int pencilsNum, int erasersNum, int notebooksNum, int matrixPapersNum) {
        pens = new Pen[pensNum];
        pencils = new Pencil[pencilsNum];
        erasers = new Eraser[erasersNum];
        notebooks = new Notebook[notebooksNum];
        matrixPapers = new DotMatrixPaper[matrixPapersNum];

        for (int i = 0; i < pensNum; i++) {
            pens[i] = new Pen();
        }
        for (int i = 0; i < pencilsNum; i++) {
            pencils[i] = new Pencil();
        }
        for (int i = 0; i < erasersNum; i++) {
            erasers[i] = new Eraser();
        }
        for (int i = 0; i < matrixPapersNum; i++) {
            matrixPapers[i] = new DotMatrixPaper();
        }
    }

    public static Workspace getInstance(StationeryPack sp) {
        if (sp == null)   { return new Workspace(3, 2, 1, 1, 1); }

        switch (sp) {
            case MINIMUM: { return new Workspace(1, 1, 1, 1, 1); }
            case MEDIUM:  { return new Workspace(3, 2, 1, 1, 1); }
            case FULL:    { return new Workspace(5, 3, 2, 2, 1); }
            case EXTRA:   { return new Workspace(10, 5, 3, 3, 3); }
        }

        return null;
    }

    public int calcWorkspaceStationeryPrice() {
        int totalPrice = 0;

        if (pens[0] != null)        { totalPrice += pens[0].getPrice() * pens.length; }
        if (pencils[0] != null)     { totalPrice += pencils[0].getPrice() * pencils.length; }
        if (erasers[0] != null)     { totalPrice += erasers[0].getPrice() * pencils.length; }
        if (notebooks[0] != null)   { totalPrice += notebooks[0].getPrice() * notebooks.length; }
        if (matrixPapers[0] !=null) { totalPrice += matrixPapers[0].getPrice() * matrixPapers.length; }

        return totalPrice;
    }
}
