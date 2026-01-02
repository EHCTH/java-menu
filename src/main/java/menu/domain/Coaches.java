package menu.domain;

import menu.domain.policy.OrderPolicy;

import java.util.List;

public class Coaches {
    private final OrderPolicy orderPolicy;
    private final List<Coach> coaches;

    public Coaches(OrderPolicy orderPolicy, List<Coach> coaches) {
        this.orderPolicy = orderPolicy;
        this.coaches = coaches;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void clearOrderMenu() {
        coaches.forEach(Coach::clearOrderMenu);
    }

    public void order(Menu menu) {
        coaches.forEach(coach -> coach.order(menu, orderPolicy));
    }
}
