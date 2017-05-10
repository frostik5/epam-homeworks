package se02.task03;

public abstract class Stationery {
    String name;
    int price;

    Stationery(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Name: " + name + "; price: " + price + " rub.";
    }
}


abstract class StationeryForWriting extends Stationery {
    StationeryForWriting(String name, int price) {
        super(name, price);
    }

    abstract void write(String text);
}


abstract class Pen extends StationeryForWriting {
    Pen(String name, int price) {
        super(name, price);
    }
}

class GelPen extends Pen {
    public GelPen () {
        super("Gel pen", 40);
    }

    public void write(String text) {
        System.out.println(name + ": " + text);
    }
}


class RollerballPen extends Pen {
    public RollerballPen () {
        super("Rollerball pen", 50);
    }

    public void write(String text) {
        System.out.println(name + ": " + text);
    }
}

class Pencil extends StationeryForWriting {
    String name = "Pencil";
    int price = 25;

    public Pencil () {
        super("Pencil", 25);
    }

    public void write(String text) {
        System.out.println(name + ": " + text);
    }
}

abstract class OfficeStationery extends Stationery {
    public OfficeStationery (String name, int price) {
        super(name, price);
    }
}

class Eraser extends OfficeStationery {
    public Eraser () {
        super("Eraser", 30);
    }
}

class Stapler extends OfficeStationery {
    String name = "Stapler";
    int price = 100;

    public Stapler () {
        super("Stapler", 100);
    }
}
