package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Menu {
    일식("일식", "규동, 우동, 미소시루, 스시, 가츠동, 오니기리, 하이라이스, 라멘, 오코노미야끼"),
    한식("한식", "김밥, 김치찌개, 쌈밥, 된장찌개, 비빔밥, 칼국수, 불고기, 떡볶이, 제육볶음"),
    중식("중식", "깐풍기, 볶음면, 동파육, 짜장면, 짬뽕, 마파두부, 탕수육, 토마토 달걀볶음, 고추잡채"),
    아시안("아시안", "팟타이, 카오 팟, 나시고렝, 파인애플 볶음밥, 쌀국수, 똠얌꿍, 반미, 월남쌈, 분짜"),
    양식("양식", "라자냐, 그라탱, 뇨끼, 끼슈, 프렌치 토스트, 바게트, 스파게티, 피자, 파니니");
    private final Category category;
    private final List<String> dishes;
    private static final List<Menu> MENUS = Arrays.stream(values()).collect(Collectors.toList());

    Menu(String category, String data) {
        this.category = new Category(category);
        this.dishes = Arrays.stream(data.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public Category getCategory() {
        return category;
    }

    public List<String> getDishes() {
        return dishes;
    }

    public static List<Menu> recommendMenu() {
        Predicate<List<Menu>> isRetry = Menu::isRetry;
        return Stream.generate(Menu::pickupRandomMenu)
                .filter(isRetry.negate())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 메뉴 추천 불가 예기지 못한 에러 발생"));
    }

    private static List<Menu> pickupRandomMenu() {
        return Stream.generate(Menu::randomCategory)
                .limit(5)
                .map(Menu::pickupRandomMenu)
                .collect(Collectors.toList());
    }

    private static int randomCategory() {
        return Randoms.pickNumberInRange(1, 5);
    }

    private static Menu pickupRandomMenu(int pickNumberInRange) {
        return MENUS.get(pickNumberInRange - 1);
    }


    private static boolean isRetry(List<Menu> menus) {
        return groupingByMenuCount(menus).values()
                .stream()
                .anyMatch(x -> x > 2);
    }

    private static Map<Menu, Long> groupingByMenuCount(List<Menu> menus) {
        return menus.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )
        );
    }

    private boolean containsDish(String name) {
        return dishes.contains(name);
    }

    private long matchesCount(List<String> dishes) {
        return dishes.stream()
                .filter(this::containsDish)
                .count();

    }

    public static Map<Category, Long> groupingByCategoryAndMatchCount(List<String> dishes) {
        ToLongFunction<Menu> menuMatchCountAdd = menu -> menu.matchesCount(dishes);
        return Arrays.stream(Menu.values())
                .collect(Collectors.groupingBy(
                        Menu::getCategory,
                        Collectors.summingLong(menuMatchCountAdd))
                );
    }

    public static Map<Category, Long> groupingByCategoryAndMatchCountV2(List<String> dishes) {
        return Arrays.stream(values())
                .collect(Collectors.groupingBy(
                                Menu::getCategory,
                                Collectors.mapping(
                                        x -> x.matchesCount(dishes),
                                        Collectors.reducing(0L, Long::sum)
                                )
                        )
                );
    }
}
