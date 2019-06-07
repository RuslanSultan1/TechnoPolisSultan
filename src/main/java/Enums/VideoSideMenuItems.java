package Enums;

public enum VideoSideMenuItems {
    TOP_OF_THE_WEEK("Топ недели"),
    OK_LIVE("OK live"),
    POPULAR("Популярное"),
    NEW_ITEMS("Новинки"),
    LIVE_BROADCAST("Прямой эфир"),
    SPORT("Спорт"),
    MY_SUBSCRIPTIONS("Мои подписки"),
    CATALOG("Каталог"),
    MY_VIDEO("Моё видео"),
    CST("Эфир"),
    VIDEO("Видео"),
    LINK("Ссылка");
    String value;

    VideoSideMenuItems(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
