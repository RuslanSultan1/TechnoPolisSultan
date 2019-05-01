package Enums;

public enum LoginInfo {
    LOGIN("89290608614"),
    PASSWORD("testautomation987!");
    String value;
    LoginInfo(String value){
        this.value=value;
    }

    @Override
    public String toString() {
        return value;
    }
}
