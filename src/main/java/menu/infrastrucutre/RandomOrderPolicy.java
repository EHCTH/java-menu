package menu.infrastrucutre;

import camp.nextstep.edu.missionutils.Randoms;
import menu.domain.policy.OrderPolicy;

import java.util.List;

public class RandomOrderPolicy implements OrderPolicy {

    @Override
    public String order(List<String> dishes) {
        return Randoms.shuffle(dishes).get(0);
    }

}
