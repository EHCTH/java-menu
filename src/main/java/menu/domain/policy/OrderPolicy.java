package menu.domain.policy;

import menu.domain.Menu;

import java.util.List;

public interface OrderPolicy {
    String order(List<String> dishes);

}
