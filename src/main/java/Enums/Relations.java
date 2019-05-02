package Enums;

public enum Relations {
    RELATIVE("родственник"),
    BEST_FRIEND("лучший друг"),
    COLLEAGUE("коллега"),
    CLASSMATE("одноклассник"),
    GROUPMATE("однокурсник"),
    WAR_BUDDY("сослуживец"),
    GAME_FRIEND("играем вместе");
    String value;

    Relations(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
