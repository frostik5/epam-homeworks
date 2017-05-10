package se02.task04;

import se02.task03.Stationery;

import java.util.Comparator;

public class NamePriceComparator implements Comparator<Stationery> {
    private Comparator<Stationery> c1, c2;

    NamePriceComparator(Comparator<Stationery> c1, Comparator<Stationery> c2) {
        this.c1 = c1;
        this.c2 = c2;
    }


    @Override
    public int compare(Stationery o1, Stationery o2) {
        int res = c1.compare(o1, o2);
        if (res == 0) {
            return c2.compare(o1, o2);
        }
        return res;
    }
}
