package deliverooCronExpressionParser;

public enum CronExpressionFieldType {

    MINUTE(0, 59),
    HOUR(0, 23),
    DAY_OF_MONTH(1, 31),
    MONTH(1, 12),
    DAY_OF_WEEK(1, 7);

    public final int maximum;
    public final int minimum;

    CronExpressionFieldType(int min, int max) {
        this.minimum = min;
        this.maximum = max;
    }
}
