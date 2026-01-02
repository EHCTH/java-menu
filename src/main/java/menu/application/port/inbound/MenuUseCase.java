package menu.application.port.inbound;

import menu.domain.Name;

import java.util.List;

public interface MenuUseCase {
    ResponseRow process(List<Name> names, List<List<String>> hateMenus);
}
