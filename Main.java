import java.util.*;
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Expected [minute] [hour] [day of month] [day of week] [command] but got :" + Arrays.toString(args));
            return;
        }
        try {

            CronParser parser = new CronExpressionParser();
           Map<String,String> out = parser.parse(args[0]);
           for (Map.Entry<String,String> entry : out.entrySet()){
               System.out.println(entry.getKey() + "     " + entry.getValue());
           }

        } catch (InvalidCronExpressionException invalidCronExpression) {
            System.err.println(invalidCronExpression.getMessage());
        }
    }
}
