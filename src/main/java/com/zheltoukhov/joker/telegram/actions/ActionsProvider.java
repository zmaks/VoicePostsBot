package com.zheltoukhov.joker.telegram.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import static com.zheltoukhov.joker.telegram.actions.Actions.BUTTON_ACTION_SPLITTER;

/**
 * Created by Maksim on 11.11.2017.
 */
@Component
public class ActionsProvider {

    @Autowired
    private ApplicationContext applicationContext;

    public JokerAction getCommand(String text) {
        String[] parts = text.split(BUTTON_ACTION_SPLITTER);
        String actionName = parts[0];
        return (JokerAction) applicationContext.getBean(actionName);
    }

}
