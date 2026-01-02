package menu.application.port.inbound;

import java.util.List;

public class ResponseCoach {
    private final String name;
    private final List<String> menus;

    public ResponseCoach(String name, List<String> menus) {
        this.name = name;
        this.menus = menus;
    }

    public String getName() {
        return name;
    }

    public List<String> getMenus() {
        return menus;
    }
}
