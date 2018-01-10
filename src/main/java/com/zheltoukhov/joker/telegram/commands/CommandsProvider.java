package com.zheltoukhov.joker.telegram.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.zheltoukhov.joker.telegram.commands.Commands.PREFIX;

/**
 * Created by Maksim on 11.11.2017.
 */
@Component
public class CommandsProvider {
    @Autowired
    ApplicationContext applicationContext;


    public Optional<JokerCommand> getCommand(String text) {
        String[] parts = text.replace(PREFIX, "").split(" ");
        String commandName = parts[0];
        return Optional.ofNullable((JokerCommand)applicationContext.getBean(commandName));
    }

}
