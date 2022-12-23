package deliverooCronExpressionParser;

public class InvalidCronExpressionException extends Exception{

    public InvalidCronExpressionException (String str)
    {
        // calling the constructor of parent Exception
        super(str);
    }
}
