package deliverooCronExpressionParser;

import deliverooCronExpressionParser.CronExpressionFieldType;

import java.util.*;

public class FieldParser {

    private static final String SLASH = "/";
    private static final String EMPTY_STRING = "";
    private static final String HASH_TAG = "#";
    private static final String QUESTION_MARK_STRING = "?";
    private static final String ASTERISK = "*";
    private static final char[] SPECIAL_CHARS_MINUS_STAR = new char[] {'-', ',' };// universally supported

    private final CronExpressionFieldType type;

    private final String fieldExpression;


    public FieldParser(String fieldExpression, CronExpressionFieldType type) throws InvalidCronExpressionException {
        this.type = type;
        this.fieldExpression = fieldExpression;

        parseFixedValues();
        parseRangeOfValues();
        parseIntervals();

        if (values.isEmpty()) {
            values.add(parseNumber(fieldExpression));
        }
    }

    private Set<Integer> values = new TreeSet<>();

    public Set<Integer> getValues() {
        return values;
    }

    public String toString() {
        return values.stream().map(Object::toString).collect(java.util.stream.Collectors.joining(" "));
    }


    private void parseIntervals() throws InvalidCronExpressionException {
        if (fieldExpression.startsWith(ASTERISK)) {
            int interval = 1;
            String[] intervals = fieldExpression.split("/");
            if (intervals.length > 2) {
                throw new InvalidCronExpressionException("Number " + fieldExpression + " for " + type + "has too many intervals");
            }
            if (intervals.length == 2) {
                interval = parseNumber(intervals[1]);
            }
            populateValues(type.minimum, type.maximum, interval);
        }
    }

    private void parseRangeOfValues() throws InvalidCronExpressionException {
        String[] range = fieldExpression.split("-");
        if (range.length == 2) {
            int start = parseNumber(range[0]);
            int end = parseNumber(range[1]);
            populateValues(start, end, 1);
        }
    }

    private void parseFixedValues() throws InvalidCronExpressionException {
        String[] fixedDates = fieldExpression.split(",");
        if (fixedDates.length > 1) {
            //fixed values
            for (String date : fixedDates) {
                int e = parseNumber(date);
                // pass value to populate() to validate min max ranges
                populateValues(e, e, 1);
            }
        }
    }

    private void populateValues(int start, int end, int increment) throws InvalidCronExpressionException {
        if (increment == 0) {
            throw new InvalidCronExpressionException("Number " + fieldExpression + " for " + type + " interval is 0");
        }
        if (end < start) {
            throw new InvalidCronExpressionException("Number " + fieldExpression + " for " + type + " ends before it starts");
        }
        if (start < type.minimum || end > type.maximum) {
            throw new InvalidCronExpressionException("Number " + fieldExpression + " for " + type + " is outside valid range (" + type.minimum + "-" + type.maximum + ")");
        }
        for (int i = start; i <= end; i += increment) {
            values.add(i);
        }
    }

    private Integer parseNumber(String no) throws InvalidCronExpressionException {
        try {
            return Integer.parseInt(no);
        } catch (NumberFormatException nfe) {
            throw new InvalidCronExpressionException("Invalid number '" + no + "' in field " + type + ": " + nfe.getMessage());
        }
    }
}
