package menu.domain;

import java.util.List;

public class Kiosk {
    private final Coaches coaches;
    private final List<Menu> menus;

    public Kiosk(Coaches coaches, List<Menu> menus) {
        this.coaches = coaches;
        this.menus = menus;
    }

    public void order() {
        coaches.clearOrderMenu();
        menus.forEach(coaches::order);

    }
}
