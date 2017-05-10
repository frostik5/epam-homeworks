package se02.task03;

import java.util.ArrayList;
import java.util.List;

/**
 * Разработайте иерархию канцелярских товаров. Создайте “набор новичка”, используя созданную иерархию.
 */
public class StarterKit {
    private List<Stationery> kitList;
    private RollerballPen pen1;
    private GelPen pen2;
    private Eraser eraser;
    private Stapler stapler;

    public StarterKit() {
        pen1 = new RollerballPen();
        eraser = new Eraser();
        pen2 = new GelPen();
        stapler = new Stapler();

        kitList = new ArrayList<>();
        kitList.add(pen1);
        kitList.add(eraser);
        kitList.add(pen2);
        kitList.add(stapler);
    }

    public List<Stationery> getKitList() {
        return kitList;
    }

    @Override
    public String toString() {
        String str = "========= StarterKit =========\n";
        if (pen1 != null)    str += pen1.toString() + "\n";
        if (pen2 != null)    str += pen2 + "\n";
        if (eraser != null)  str += eraser + "\n";
        if (stapler != null) str += stapler + "\n";

        return str;
    }

    public static void main(String[] args) {
        System.out.println(new StarterKit());
    }
}
