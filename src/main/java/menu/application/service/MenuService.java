package menu.application.service;

import menu.application.port.inbound.MenuUseCase;
import menu.application.port.inbound.ResponseCoach;
import menu.application.port.inbound.ResponseRow;
import menu.domain.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuService implements MenuUseCase {

    @Override
    public ResponseRow process(List<Name> names, List<List<String>> hateMenus) {
        Coaches coaches = createCoaches(names, hateMenus);

        coaches.settingMenus();

        List<ResponseCoach> responseCoaches = createResponseCoahces(coaches);
        List<String> categories = getCategories(coaches);

        return new ResponseRow(categories, responseCoaches);
    }

    private List<ResponseCoach> createResponseCoahces(Coaches coaches) {
        return coaches.getCoaches()
                .stream()
                .map(this::createResponseCoach)
                .collect(Collectors.toList());
    }

    private Coaches createCoaches(List<Name> names, List<List<String>> hateMenus) {
        Iterator<Name> nameIterator = names.iterator();
        Iterator<List<String>> hateMenuIterator = hateMenus.iterator();

        return Stream.generate(() -> new Coach(nameIterator.next(), hateMenuIterator.next()))
                .limit(names.size())
                .collect(Collectors.collectingAndThen(Collectors.toList(), Coaches::new));
    }

    private List<String> getCategories(Coaches coaches) {
        return coaches.getMenus().stream()
                .map(Menu::getCategory)
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    private ResponseCoach createResponseCoach(Coach coach) {
        return new ResponseCoach(coach.getName().value(), coach.getChoiceMenu());
    }
}
