package se02.task01;

public class Pen {
    private PenType type;
    private String penColor;
    private String inkColor;
    private String inkType;
    private float lineWidth;
    private int inkLevel;
    private int price;

    public Pen(PenType type, String inkColor, String inkType) {
        this.type = type;
        this.inkColor = inkColor;
        this.inkType = inkType;
    }

    public Pen(PenType type, String inkColor, String inkType, float lineWidth) {
        this(type, inkColor, inkType);
        if (lineWidth < 0.2f) {
            this.lineWidth = 0.2f;
        } else if (lineWidth > 3.0f) {
            this.lineWidth = 3.0f;
        } else {
            this.lineWidth = lineWidth;
        }
    }

    public Pen(PenType type, String inkColor, String inkType, float lineWidth, String penColor) {
        this(type, inkColor, inkType, lineWidth);
        this.penColor = penColor;
    }

    public Pen(PenType type, String inkColor, String inkType, float lineWidth, String penColor, int inkLevel) {
        this(type, inkColor, inkType, lineWidth, penColor);
        if (inkLevel < 0) {
            this.inkLevel = 0;
        } else if (inkLevel > 10) {
            this.inkLevel = 10;
        } else {
            this.inkLevel = inkLevel;
        }
    }

    public Pen(PenType type, String inkColor, String inkType, float lineWidth, String penColor, int inkLevel, int price) {
        this(type, inkColor, inkType, lineWidth, penColor, inkLevel);
        this.price = price;
    }

    public void writeText(String text) {
        if (inkLevel != 0) {
            System.out.println(text);
            inkLevel--;
        } else {
            System.out.println("---Out of ink---");
        }
    }

    public void fuelTheInk(int newInk) {
        if ((10 - (inkLevel + newInk)) >= 0) {
            inkLevel += newInk;
        } else {
            inkLevel = 10;
        }
    }

    public PenType getType() {
        return type;
    }

    public void setType(PenType type) {
        this.type = type;
    }

    public String getPenColor() {
        return penColor;
    }

    public void setPenColor(String penColor) {
        this.penColor = penColor;
    }

    public String getInkColor() {
        return inkColor;
    }

    public void setInkColor(String inkColor) {
        this.inkColor = inkColor;
    }

    public String getInkType() {
        return inkType;
    }

    public void setInkType(String inkType) {
        this.inkType = inkType;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public int getInkLevel() {
        return inkLevel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price >= 30) {
            this.price = price;
        } else {
            this.price = 30;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pen pen = (Pen) o;

        if (Float.compare(pen.lineWidth, lineWidth) != 0) return false;
        if (type != pen.type) return false;
        if (!inkColor.equals(pen.inkColor)) return false;
        return inkType.equals(pen.inkType);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + inkColor.hashCode();
        result = 31 * result + inkType.hashCode();
        result = 31 * result + (lineWidth != +0.0f ? Float.floatToIntBits(lineWidth) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pen: " +
                "type = " + type +
                ", penColor = '" + penColor + '\'' +
                ", inkColor = '" + inkColor + '\'' +
                ", inkType = '" + inkType + '\'' +
                ", lineWidth = " + lineWidth +
                ", inkLevel = " + inkLevel +
                ", price = " + price;
    }
}
