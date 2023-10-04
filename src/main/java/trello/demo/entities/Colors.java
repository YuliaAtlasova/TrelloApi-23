package trello.demo.entities;

public enum Colors {
    BLUE("blue"),
    ORANGE("orange"),
    GREEN("green"),
    RED("red"),
    PURPLE("purple"),
    PINK("pink"),
    LIME("lime"),
    SKY("sky"),
    GREY("grey");

    public final String color;

    Colors(String color) {
        this.color = color;
    }
}
