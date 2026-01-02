package menu.infrastrucutre;

import camp.nextstep.edu.missionutils.Randoms;
import menu.domain.Menu;
import menu.domain.policy.CategoryPolicy;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomCategoryPolicy implements CategoryPolicy {
    @Override
    public List<Menu> createWeekCategories() {
        Predicate<List<Menu>> isRetry = this::isRetry;
        return Stream.generate(this::createRandomMenus)
                .filter(isRetry.negate())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 카테고리 예외가 발생하였습니다"));
    }

    private List<Menu> createRandomMenus() {
        List<Menu> menus = Menu.getMenus();
        return Stream.generate(() -> getRandomCategory(menus))
                .limit(5)
                .collect(Collectors.toList());
    }

    private boolean isRetry(List<Menu> menus) {
        Map<Menu, Long> groupingByCategoryCount = groupingByCategoryCount(menus);
        return groupingByCategoryCount.values()
                .stream()
                .anyMatch(x -> x > 2);
    }

    private Map<Menu, Long> groupingByCategoryCount(List<Menu> menus) {
        return menus.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting())
                );
    }


    private Menu getRandomCategory(List<Menu> menus) {
        return menus.get(Randoms.pickNumberInRange(1, 5) - 1);
    }


}
