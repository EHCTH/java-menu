package menu.intrefaces.adapter.outbound;


import camp.nextstep.edu.missionutils.Console;
import menu.domain.Coach;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String DELIMITER = ",";

    public List<Coach> promptName() {
        System.out.printf("%n코치의 이름을 입력해 주세요. (, 로 구분)%n");
        String names = readLine();
        List<Coach> coaches = convertName(names);
        validate(coaches);
        return coaches;
    }

    public void promptHateMenu(Coach coach) {
        System.out.printf("%n%s(이)가 못 먹는 메뉴를 입력해 주세요.%n", coach.getName());
        String menus = readLine();

        List<String> hateMenu = Arrays.stream(menus.split(DELIMITER))
                .collect(Collectors.toList());

        coach.setHateMenu(hateMenu);
    }

    private String readLine() {
        return Console.readLine();
    }

    private List<Coach> convertName(String names) {
        return Arrays.stream(names.split(DELIMITER))
                .map(Coach::new)
                .collect(Collectors.toList());
    }

    private void validate(List<Coach> coaches) {
        if (new HashSet<>(coaches).size() != coaches.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름이 존재합니다");
        }
        if (coaches.size() < 2 || coaches.size() > 5 ) {
            throw new IllegalArgumentException("[ERROR] 코치는 최소 2이상 최대 5이하로 식사가 가능합니다");
        }
    }
}
