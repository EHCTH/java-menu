package menu.intrefaces.adapter.outbound;

import menu.application.port.inbound.ResponseCoach;
import menu.application.port.inbound.ResponseRow;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public void displayHeader() {
        System.out.printf("점심 메뉴 추천을 시작합니다.%n");
    }
    public void display(ResponseRow responseRow) {
        System.out.printf("%n메뉴 추천 결과입니다.%n");
        System.out.printf("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]%n");
        System.out.printf("%s%n", convertCategory(responseRow.getCategories()));
        responseRow.getResponseCoaches()
                .forEach(this::displayMenu);

        System.out.printf("%n추천을 완료했습니다.%n");
    }

    private String convertCategory(List<String> categories) {
        return categories.stream()
                .collect(Collectors.joining(" | ", "[ 카테고리 | ", " ]"));
    }
    private void displayMenu(ResponseCoach responseCoach) {
        String output = String.join(" | ", responseCoach.getMenus());
        System.out.printf("[ %s | %s ]%n", responseCoach.getName(), output);
    }
}
