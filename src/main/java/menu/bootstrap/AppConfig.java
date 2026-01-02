package menu.bootstrap;

import menu.application.port.inbound.MenuUseCase;
import menu.application.service.MenuService;
import menu.interfaces.adapter.inbound.Controller;
import menu.interfaces.adapter.outbound.InputView;
import menu.interfaces.adapter.outbound.OutputView;

public class AppConfig {
    private final MenuUseCase useCase = new MenuService();
    public Controller controller() {
        return new Controller(useCase, new InputView(), new OutputView());
    }
}
