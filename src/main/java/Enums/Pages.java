package Enums;

public enum Pages {
    MESSAGES("Сообщения"),
    DISCUSSIONS("Обсуждения"),
    NOTIFICATIONS("Оповещения"),
    FRIENDS("Друзья"),
    GUESTS("Гости"),
    EVENTS("События"),
    MUSIC("Музыка"),
    VIDEO("Видео");
    String value;
    Pages(String value){
        this.value=value;
    }

    @Override
    public String toString() {
        return value;
    }
}
