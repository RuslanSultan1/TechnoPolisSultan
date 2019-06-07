package Enums;

public enum Friends {
    IVAN_IVANOV("Иван Иванов"),
    VLADISLAV_SENKO("Владислав Сенько"),
    RUSLAN_SULTAN("Руслан Султан"),
    TECHNOPOLIS_BOT12("technopolisBot12 technopolisBot12");
    String value;
    Friends(String value){
        this.value=value;
    }

    @Override
    public String toString() {
        return value;
    }
}
