package menu.intrefaces.adapter.inbound;

import menu.application.port.inbound.MenuUseCase;
import menu.application.port.inbound.ResponseRow;
import menu.domain.Coach;
import menu.intrefaces.adapter.outbound.InputView;
import menu.intrefaces.adapter.outbound.OutputView;

import java.util.List;

public class Controller {
    private final MenuUseCase menuUseCase;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(MenuUseCase menuUseCase, InputView inputView, OutputView outputView) {
        this.menuUseCase = menuUseCase;
        this.inputView = inputView;
        this.outputView = outputView;
    }
    public void run() {
        outputView.displayHeader();
        List<Coach> coaches = inputView.promptName();
        coaches.forEach(inputView::promptHateMenu);
        ResponseRow process = menuUseCase.process(coaches);
        outputView.display(process);
    }
}
