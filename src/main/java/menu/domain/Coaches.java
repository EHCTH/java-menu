package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class Coaches {
    private final List<Menu> menus;
    private final List<Coach> coaches;

    public Coaches(List<Coach> coaches) {
        validate(coaches);
        this.menus = Menu.recommendMenu();
        this.coaches = coaches;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void settingMenus() {
//        coaches.forEach(coach -> coach.updateMenu(menus));

        coaches.forEach(Coach::initChoiceMenu);
        menus.forEach(this::settingMenu);
    }

    private void settingMenu(Menu menu) {
        coaches.forEach(coach -> coach.updateMenu(menu));
    }


    private void validate(List<Coach> coaches) {
        validateDuplicate(coaches);
        validateCoachesSize(coaches);
    }

    private void validateDuplicate(List<Coach> coaches) {
        if (new HashSet<>(coaches).size() != coaches.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름이 존재합니다");
        }
    }

    private void validateCoachesSize(List<Coach> coaches) {
        int size = coaches.size();
        if (size < 2 || size > 5) {
            throw new IllegalArgumentException("[ERROR] 코치는 2명 이상 5명 이하 까지 같이 식사가 가능합니다)");
        }
    }
}

