package menu.application.port.inbound;

import menu.domain.Coach;

import java.util.List;

public interface MenuUseCase {
    ResponseRow process(List<Coach> coachList);
}
