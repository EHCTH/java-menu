package menu.domain;

import menu.domain.policy.OrderPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Coach {
    private final String name;
    private final List<String> hateMenu = new ArrayList<>();
    private final List<String> orderMenu = new ArrayList<>();

    public Coach(String name) {
        validateNameLength(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getOrderMenu() {
        return orderMenu;
    }

    public void setHateMenu(List<String> hateMenu) {
        this.hateMenu.clear();
        this.hateMenu.addAll(hateMenu);
    }

    public void clearOrderMenu() {
        this.orderMenu.clear();
    }
    public void order(Menu menu, OrderPolicy orderPolicy) {
        String orderMenu = getOrderMenu(menu, orderPolicy);
        this.orderMenu.add(orderMenu);
    }

    private String getOrderMenu(Menu menu, OrderPolicy orderPolicy) {
        Predicate<String> containsHateMenu = this::containsHateMenu;
        return Stream.generate(() -> orderPolicy.order(menu.getDishes()))
                .filter(containsHateMenu.negate())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 메뉴가 존재하지 않습니다"));
    }

    public boolean containsHateMenu(String menu) {
        return hateMenu.contains(menu);
    }
    private void validateNameLength(String name) {
        if (name.length() < 2 || name.length() > 4) {
            throw new IllegalArgumentException("[ERROR] 이름 길이는 2이상 4이하입니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coach)) return false;
        Coach coach = (Coach) o;
        return Objects.equals(name, coach.name) && Objects.equals(hateMenu, coach.hateMenu) && Objects.equals(orderMenu, coach.orderMenu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hateMenu, orderMenu);
    }
}
