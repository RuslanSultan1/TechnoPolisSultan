package Enums;

public enum Friends {
    VLADISLAV_SENKO("Владислав Сенько"),
    RUSLAN_SULTAN("Руслан Султан");
    String value;
    Friends(String value){
        this.value=value;
    }

    @Override
    public String toString() {
        return value;
    }
}
