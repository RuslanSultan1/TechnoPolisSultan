package Enums;

public enum LoginInfo {
    LOGIN("89290608614"),
    PASSWORD("testautomation987!"),
    LOGIN_PAGE_URL("https://ok.ru/dk?st.cmd=anonymMain&st.layer.cmd=PopLayerClose");
    String value;
    LoginInfo(String value){
        this.value=value;
    }

    @Override
    public String toString() {
        return value;
    }
}
