package Enums;

public enum AssertsTexts {
    MAIN_URL("https://ok.ru/"),
    INVITATION_TO_GROUP_TEXT("Сначала нужно создать группу, а потом можно приглашать в неё друзей.");
    String value;
    AssertsTexts(String value){
        this.value=value;
    }

    @Override
    public String toString() {
        return value;
    }
}
