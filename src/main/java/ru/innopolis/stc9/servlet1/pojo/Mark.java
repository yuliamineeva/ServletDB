package ru.innopolis.stc9.servlet1.pojo;

/**
 * Возможные значения оценок
 */
public enum Mark {

    MARK_1("1"),
    MARK_2("2"),
    MARK_3("3"),
    MARK_4("4"),
    MARK_5("5");

//    MARK_1("Кол"),
//    MARK_2("Неуд"),
//    MARK_3("Удовл"),
//    MARK_4("Хорошо"),
//    MARK_5("Отлично");

    private String value;

    private Mark(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getIntValue() {
        return Integer.parseInt(getValue());
    }
}
