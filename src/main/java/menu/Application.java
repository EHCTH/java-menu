package menu;

import menu.bootstrap.AppConfig;
import menu.interfaces.adapter.inbound.Controller;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        Controller controller = appConfig.controller();
        controller.run();
    }
}
