package menu.application.service;

import menu.application.port.inbound.MenuUseCase;
import menu.application.port.inbound.ResponseCoach;
import menu.application.port.inbound.ResponseRow;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.Kiosk;
import menu.domain.Menu;
import menu.domain.policy.CategoryPolicy;
import menu.domain.policy.OrderPolicy;

import java.util.List;
import java.util.stream.Collectors;

public class MenuService implements MenuUseCase {
    private final OrderPolicy orderPolicy;
    private final CategoryPolicy categoryPolicy;

    public MenuService(OrderPolicy orderPolicy, CategoryPolicy categoryPolicy) {
        this.orderPolicy = orderPolicy;
        this.categoryPolicy = categoryPolicy;
    }

    @Override
    public ResponseRow process(List<Coach> coachList) {
        List<Menu> weekCategories = categoryPolicy.createWeekCategories();
        Coaches coaches = new Coaches(orderPolicy, coachList);
        Kiosk kiosk = new Kiosk(coaches, weekCategories);

        kiosk.order();

        List<ResponseCoach> responseCoaches = createResponseCoaches(coaches);
        List<String> responseCategory = createResponseCategory(weekCategories);
        return new ResponseRow(responseCategory, responseCoaches);
    }

    private List<String> createResponseCategory(List<Menu> menus) {
        return menus.stream()
                .map(Menu::getCategory)
                .collect(Collectors.toList());
    }

    private List<ResponseCoach> createResponseCoaches(Coaches coaches) {
        return coaches.getCoaches()
                .stream()
                .map(this::createResponseCoach)
                .collect(Collectors.toList());
    }

    private ResponseCoach createResponseCoach(Coach coach) {
        return new ResponseCoach(coach.getName(), coach.getOrderMenu());
    }


}
