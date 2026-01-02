package menu;

import menu.application.service.MenuService;
import menu.bootstrap.AppConfig;
import menu.infrastrucutre.RandomCategoryPolicy;
import menu.infrastrucutre.RandomOrderPolicy;
import menu.intrefaces.adapter.inbound.Controller;
import menu.intrefaces.adapter.outbound.InputView;
import menu.intrefaces.adapter.outbound.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
//        String hello = ",,,,,1,2,3,4,5,6,,,,,";
//
//        List<String> split = Arrays.stream(hello.split(",", -1))
//                .map(String::trim)
//                .collect(Collectors.toList());
//        System.out.println(split);


        AppConfig appConfig = new AppConfig();
        Controller controller = appConfig.controller();
        controller.run();
    }
}
