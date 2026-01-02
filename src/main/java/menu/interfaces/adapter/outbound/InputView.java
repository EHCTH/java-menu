package menu.interfaces.adapter.outbound;

import camp.nextstep.edu.missionutils.Console;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.Name;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String DELIMITER = ",";

    public List<Name> promptName() {
        System.out.printf("%n코치의 이름을 입력해 주세요. (, 로 구분)%n");
        String names = Console.readLine();
        return convertName(names);
    }

    public List<String> promptHateMenu(Name name) {
        System.out.printf("%n%s(이)가 못 먹는 메뉴를 입력해 주세요.%n", name.value());
        String menus = Console.readLine();
        return Arrays.stream(menus.split(DELIMITER))
                .collect(Collectors.toList());
    }

    private String readLine() {
        return Console.readLine();
    }

    private List<Name> convertName(String names) {
        return Arrays.stream(names.split(DELIMITER))
                .map(Name::new)
                .collect(Collectors.toList());
    }
}
