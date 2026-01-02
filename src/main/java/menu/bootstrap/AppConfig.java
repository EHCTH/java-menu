package menu.bootstrap;

import menu.application.port.inbound.MenuUseCase;
import menu.application.service.MenuService;
import menu.domain.policy.CategoryPolicy;
import menu.domain.policy.OrderPolicy;
import menu.infrastrucutre.RandomCategoryPolicy;
import menu.infrastrucutre.RandomOrderPolicy;
import menu.intrefaces.adapter.inbound.Controller;
import menu.intrefaces.adapter.outbound.InputView;
import menu.intrefaces.adapter.outbound.OutputView;

public class AppConfig {
    private final OrderPolicy orderPolicy = new RandomOrderPolicy();
    private final CategoryPolicy categoryPolicy = new RandomCategoryPolicy();
    private final MenuUseCase menuUseCase = new MenuService(orderPolicy, categoryPolicy);
    public Controller controller() {
        return new Controller(menuUseCase, new InputView(), new OutputView());
    }
}
