package com.zheltoukhov.joker.telegram.filters;

import com.zheltoukhov.joker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Maksim on 19.11.2017.
 */
@Component
public class FilterProvider {

    @Autowired
    private List<AnyRequestFilter> filters;

    public boolean performFilers(User user, Consumer<PartialBotApiMethod> botCallback) {
        for(JokerFilter filter : filters) {
            if (!filter.execute(user, botCallback)) return false;
        }
        return true;
    }

}
