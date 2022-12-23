package deliverooCronExpressionParser;

import java.util.*;
public interface CronParser {

    public Map<String,String> parse(String cronExpression) throws InvalidCronExpressionException;
}

