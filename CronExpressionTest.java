package deliverooCronExpressionParser;

import deliverooCronExpressionParser.CronExpressionFieldType;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CronExpressionTest {

        /**
         * Test valid range expressions (E.g. 1-5, 0-3, etc. )
         */
        @Test
        public void correct_range() throws InvalidCronExpressionException {
            FieldParser parser = new FieldParser("1-5", CronExpressionFieldType.DAY_OF_MONTH);
            Assert.assertEquals("1 2 3 4 5", parser.toString());
            parser = new FieldParser("1-1", CronExpressionFieldType.DAY_OF_MONTH);
            Assert.assertEquals("1", parser.toString());
            parser = new FieldParser("1-2", CronExpressionFieldType.DAY_OF_MONTH);
            Assert.assertEquals("1 2", parser.toString());
            parser = new FieldParser("1-15", CronExpressionFieldType.DAY_OF_MONTH);
            Assert.assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15", parser.toString());

        }

        /**
         * Test correct fixed value cron field ( e.g. 1,3,4 or 15,16 )
         */
        @Test
        public void correct_fixed_values() throws InvalidCronExpressionException {
            FieldParser fieldParser = new FieldParser("1", CronExpressionFieldType.DAY_OF_MONTH);
            Assert.assertEquals("1", fieldParser.toString());
            fieldParser = new FieldParser("1,2,3,4,5", CronExpressionFieldType.DAY_OF_MONTH);
            Assert.assertEquals("1 2 3 4 5", fieldParser.toString());
            fieldParser = new FieldParser("1,1,1", CronExpressionFieldType.DAY_OF_MONTH);
            Assert.assertEquals("1", fieldParser.toString());
            fieldParser = new FieldParser("1,2", CronExpressionFieldType.DAY_OF_MONTH);
            Assert.assertEquals("1 2", fieldParser.toString());
            fieldParser = new FieldParser("2,1,3,5,6,7,4", CronExpressionFieldType.DAY_OF_MONTH);
            Assert.assertEquals("1 2 3 4 5 6 7", fieldParser.toString());
        }

        /**
         * Test correct interval expressions ( e.g. * , *\/15 , *\/20 )
         */
        @Test
        public void correct_intervals() throws InvalidCronExpressionException {
            FieldParser f = new FieldParser("*/10", CronExpressionFieldType.DAY_OF_MONTH);
            Assert.assertEquals("1 11 21 31", f.toString());
            f = new FieldParser("*/20", CronExpressionFieldType.DAY_OF_MONTH);
            Assert.assertEquals("1 21", f.toString());
            f = new FieldParser("*/40", CronExpressionFieldType.DAY_OF_MONTH);
            Assert.assertEquals("1", f.toString());
            f = new FieldParser("*", CronExpressionFieldType.DAY_OF_WEEK);
            Assert.assertEquals("1 2 3 4 5 6 7", f.toString());

            f = new FieldParser("*", CronExpressionFieldType.MONTH);
            Assert.assertEquals("1 2 3 4 5 6 7 8 9 10 11 12", f.toString());
        }

        @Test
        public void incorrect_intervals()  {
            tryParse("*/0", CronExpressionFieldType.DAY_OF_MONTH, "interval is 0");
        }

        private void tryParse(String expression, CronExpressionFieldType fieldType, String msg) {
            try {
                new FieldParser(expression, fieldType);
                fail(expression + " should not be a valid " + fieldType);
            } catch (InvalidCronExpressionException e) {
                assertTrue(e.getMessage().contains(msg));
                assertTrue(e.getMessage().contains(fieldType.toString()));
            }
        }
}

