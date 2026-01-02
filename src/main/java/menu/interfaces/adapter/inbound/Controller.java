package menu.interfaces.adapter.inbound;

import menu.application.port.inbound.MenuUseCase;
import menu.application.port.inbound.ResponseRow;
import menu.domain.Name;
import menu.interfaces.adapter.outbound.InputView;
import menu.interfaces.adapter.outbound.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Controller {
    private final MenuUseCase useCase;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(MenuUseCase useCase, InputView inputView, OutputView outputView) {
        this.useCase = useCase;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.displayHeader();

        List<Name> names = promptNames();
        List<List<String>> hateMenu = promptHateMenus(names);

        ResponseRow responseRow = useCase.process(names, hateMenu);

        outputView.display(responseRow);
    }

    private List<Name> promptNames() {
        return retryUntilToSuccess(inputView::promptName);
    }

    private List<List<String>> promptHateMenus(List<Name> names) {
        return names.stream()
                .map(name -> retryUntilToSuccess(() -> inputView.promptHateMenu(name)))
                .collect(Collectors.toList());
    }

    public <T> T retryUntilToSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
