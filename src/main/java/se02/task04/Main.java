package se02.task04;

import se02.task03.StarterKit;
import se02.task03.Stationery;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        NameComparator nc = new NameComparator();
        PriceComparator pc = new PriceComparator();
        NamePriceComparator npc = new NamePriceComparator(pc, nc);

        List<Stationery> sk1 = new StarterKit().getKitList();
        System.out.println("=========== Initial StarterKit ===========");
        sk1.forEach(System.out::println);
        System.out.println("\n======== Sorted by name StarterKit =======");
        sk1.sort(nc);
        sk1.forEach(System.out::println);

        List<Stationery> sk2 = new StarterKit().getKitList();
        System.out.println("\n======= Sorted by price StarterKit =======");
        sk2.sort(pc);
        sk2.forEach(System.out::println);

        List<Stationery> sk3 = new StarterKit().getKitList();
        System.out.println("\n== Sorted by price then name StarterKit ==");
        sk3.sort(pc);
        sk3.forEach(System.out::println);
    }
}
