package Enums;

public enum LoginInfo {
    IVAN_LOGIN("89290608614"),
    IVAN_PASSWORD("testautomation987!"),
    BOT_LOGIN("technopolisBot122"),
    BOT_PASSWORD("technopolis16"),
    LOGIN_PAGE_URL("https://ok.ru/dk?st.cmd=anonymMain&st.layer.cmd=PopLayerClose");
    String value;

    LoginInfo(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
