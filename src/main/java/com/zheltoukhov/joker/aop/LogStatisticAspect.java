package com.zheltoukhov.joker.aop;

import com.zheltoukhov.joker.entity.User;
import com.zheltoukhov.joker.helpers.MessageConstants;
import com.zheltoukhov.joker.helpers.StatRecordType;
import com.zheltoukhov.joker.service.StatisticService;
import com.zheltoukhov.joker.telegram.commands.StartCommand;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by Maksim on 18.11.2017.
 */
@Component
@Aspect
public class LogStatisticAspect {
    private static Logger LOG = LoggerFactory.getLogger(LogStatisticAspect.class);
    private static final String LOG_PATTERN =
                    "----- %s -----\n" +
                    "--------------    User: %s [%s]\n" +
                    "--------------    Text: %s\n\n";

    @Autowired
    private StatisticService statisticService;

    @Pointcut("@annotation(com.zheltoukhov.joker.aop.annotations.Loggable)")
    public void loggableMethod(){}

    @Pointcut("execution(* com.zheltoukhov.joker.telegram.commands.AnecdoteCommand.execute(..))")
    public void voiceCommands(){}

    @Pointcut("execution(* com.zheltoukhov.joker.telegram.actions.RepeatButtonAction.execute(..))")
    public void voiceAction(){}

    @Pointcut("execution(* com.zheltoukhov.joker.telegram.commands.StartCommand.execute(..))")
    public void start(){}

    @Pointcut("execution(boolean com.zheltoukhov.joker.telegram.filters.*..execute(..))")
    public void filters(){}

    @Pointcut("within(@com.zheltoukhov.* *)")
    public void app(){};

    @Before("loggableMethod()")
    public void logMethod(JoinPoint jp) {
        LOG.debug("aspect ");
    }

    @After("voiceCommands() || voiceAction() || start()")
    public void commandExecution(JoinPoint jp) {
        User user = (User) jp.getArgs()[0];
        String text = null;
        String type = jp.getTarget().getClass().equals(StartCommand.class) ?
                StatRecordType.START : StatRecordType.VOICE;
        if (jp.getArgs()[1] instanceof Message)
            text = ((Message) jp.getArgs()[1]).getText();
        else
            text = ((CallbackQuery) jp.getArgs()[1]).getData();
        LOG.info(
                String.format(LOG_PATTERN, type, user.getName(), user.getChatId(), text)
        );

        statisticService.addRecord(user, text, type);
    }

    @AfterReturning(value = "filters()", returning = "returnValue")
    public void filterRejection(JoinPoint jp, Boolean returnValue) {
        if (!returnValue) {
            User user = (User) jp.getArgs()[0];
            String text = "Rejected by "+jp.getTarget().getClass().getSimpleName();
            LOG.info(
                    String.format(
                            LOG_PATTERN,
                            StatRecordType.FILTER,
                            user.getName(),
                            user.getChatId(),
                            text
                    )
            );

            statisticService.addRecord(user, text, StatRecordType.FILTER);
        }
    }

    @AfterThrowing(pointcut = "app()", throwing = "ex")
    public void measureMethodExecutionTime(Exception ex) {
        LOG.error(ex.getMessage(), ex);
    }
}
