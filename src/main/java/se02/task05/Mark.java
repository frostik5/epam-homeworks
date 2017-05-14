package se02.task05;

public class Mark<T extends Number> implements Comparable {
    private T mark;

    public Mark(T mark) {
        this.mark = mark;
    }

    public Number getMarkType() {
        if (mark instanceof Integer) {
            return mark.intValue();
        } else {
            return mark.doubleValue();
        }
    }

    public Integer getIntegerMark() {
        return mark.intValue();
    }

    public Double getDoubleMark() {
        return mark.doubleValue();
    }

    @Override
    public int compareTo(Object o) {
        return (int) Math.round(this.getDoubleMark() - ((Mark) o).getDoubleMark());
    }
}
