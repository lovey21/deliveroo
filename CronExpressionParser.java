package deliverooCronExpressionParser;

import deliverooCronExpressionParser.CronExpressionFieldType;

import java.util.*;
public class CronExpressionParser implements CronParser {

    private FieldParser minutes;
    private FieldParser hours;
    private FieldParser dayOfMonth;
    private FieldParser month;
    private FieldParser dayOfWeek;

    @Override
    public Map<String, String> parse(String cronExpression) throws InvalidCronExpressionException {

            String[] cronMembers = cronExpression.split("\\s+");
            if (cronMembers.length != 6) {
                throw new InvalidCronExpressionException("Expected [minute] [hour] [day of month] [day of week] [command] but got :" + cronExpression);
            }
            minutes = new FieldParser(cronMembers[0], CronExpressionFieldType.MINUTE);
            hours = new FieldParser(cronMembers[1], CronExpressionFieldType.HOUR);
            dayOfMonth = new FieldParser(cronMembers[2], CronExpressionFieldType.DAY_OF_MONTH);
            month = new FieldParser(cronMembers[3], CronExpressionFieldType.MONTH);
            dayOfWeek = new FieldParser(cronMembers[4], CronExpressionFieldType.DAY_OF_WEEK);
            String command = cronMembers[5];
            Map<String, String> output = new HashMap<>();
            output.put("minute", minutes.toString());
            output.put("hour", hours.toString());
            output.put("day of month", dayOfMonth.toString());
            output.put("month", month.toString());
            output.put("day of week", dayOfWeek.toString());
            output.put("command", command);
            return output;
        }
}
