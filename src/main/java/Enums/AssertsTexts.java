package Enums;

public enum AssertsTexts {
    MAIN_URL("https://ok.ru/"),
    INVITATION_TO_GROUP_TEXT("Сначала нужно создать группу, а потом можно приглашать в неё друзей."),
    VIDEO_PAGE_URL("https://ok.ru/video/top"),
    NOTHING_FOUND_TEXT("По вашему запросу ничего не найдено"),
    FRIENDS_NOT_FOUND_TEXT("Не найдено ни одного друга"),
    VIDEOS_FOUND_REGEX("Найдено \\d+ видео"),
    NOTIFICATIONS_PAGE_URL("https://ok.ru/notifications"),
    SUBSCRIBED("Подписаны"),
    UNSUBSCRIBED("Подписаться");
    String value;
    AssertsTexts(String value){
        this.value=value;
    }

    @Override
    public String toString() {
        return value;
    }
}
