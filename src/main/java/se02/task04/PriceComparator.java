package se02.task04;

import se02.task03.Stationery;

import java.util.Comparator;

public class PriceComparator implements Comparator<Stationery> {
    @Override
    public int compare(Stationery o1, Stationery o2) {
        return o1.getPrice() - o2.getPrice();
    }
}
