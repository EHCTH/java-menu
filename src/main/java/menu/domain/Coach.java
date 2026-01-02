package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Coach {
    private final Name name;
    private final List<String> hateMenu;
    private List<String> choiceMenu;

    public Coach(Name name, List<String> hateMenu) {
        this.name = name;
        this.hateMenu = hateMenu;
    }

    public Name getName() {
        return name;
    }

    public void initChoiceMenu() {
        this.choiceMenu = new ArrayList<>();
    }

    public List<String> getChoiceMenu() {
        return choiceMenu;
    }

    public void addChoiceMenu(String dish) {
        this.choiceMenu.add(dish);
    }

    public void updateMenu(Menu menu) {
        List<String> candidates = menu.getDishes();
        String dish = getRandomPickupMenu(candidates);
        addChoiceMenu(dish);
    }

    public void updateMenu(List<Menu> categories) {
        List<List<String>> dishes = extractDishes(categories);
        List<String> choiceMenu;
        do {
            choiceMenu = dishes.stream()
                    .map(this::randomMenu)
                    .collect(Collectors.toList());
        } while (isRetry(choiceMenu));
        this.choiceMenu = choiceMenu;
    }

    private boolean isRetry(List<String> choiceMenu) {
        boolean duplicate = new HashSet<>(choiceMenu).size() != choiceMenu.size();
        boolean containsHateMenu = isHateMenu(choiceMenu);
        return duplicate || containsHateMenu;
    }

    private String getRandomPickupMenu(List<String> candidates) {
        Predicate<String> isRetry = this::isRetry;
        return Stream.generate(() -> randomMenu(candidates))
                .filter(isRetry.negate())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 예상치 못한 에러 발생"));
    }


    private boolean isRetry(String menu) {
        boolean duplicate = this.choiceMenu.contains(menu);
        boolean containsHateMenu = isHateMenu(menu);
        return duplicate || containsHateMenu;
    }

    private String randomMenu(List<String> menus) {
        return Randoms.shuffle(menus).get(0);
    }

    private List<List<String>> extractDishes(List<Menu> categories) {
        return categories.stream().map(Menu::getDishes).collect(Collectors.toList());
    }

    public boolean isHateMenu(List<String> choiceMenu) {
        return choiceMenu.stream()
                .anyMatch(hateMenu::contains);
    }

    public boolean isHateMenu(String menu) {
        return hateMenu.contains(menu);
    }


}

