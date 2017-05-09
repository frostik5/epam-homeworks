package se02.task01;

public class MainClass {
    public static void main(String[] args) {
        Pen rollerballPen = new Pen(PenType.ROLLERBALL, "black", "visible");
        Pen gelPen = new Pen(PenType.GEL, "metallic", "invisible", 0.5f);
        Pen marker = new Pen(PenType.MARKER, "yellow", "glowing", 3.0f, "yellow", 5, 30);

        System.out.println(marker);
    }
}
