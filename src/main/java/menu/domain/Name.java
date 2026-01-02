package menu.domain;

public class Name {
    private final String name;

    public Name(String name) {
        validateNameLength(name);
        this.name = name;
    }

    public String value() {
        return name;
    }

    private void validateNameLength(String name) {
        int length = name.length();
        if (length < 2 || length > 4) {
            throw new IllegalArgumentException("[ERROR] 이름 길이는 최소 2이상 4이하입니다");
        }
    }

}
