# deliveroo
Deliveroo CodeAssignment  
Test

The Main.java class executes the cronExpressionParser.parse() method which takes input the cron expression entered through java args 

Supported features:

Valid Cron Expressions must consist of five time fields (minute, hour, day of
month, month, and day of week) followed by a command.


Following special characters can be included along with the fixed value.
1) asterisk (*)
2) basic slashes, for some time interval x (e.g. `*/15)
3) basic commas (e.g. 1,2)
4) hyphens (e.g. 1-7)

Following Features are not supported 

1) words in place of 
    MINUTE,
    HOUR,
    DAY_OF_MONTH,
    MONTH,
    DAY_OF_WEEK;

2) question marks, @ , #

Implementation Details of Classes 


CronExpressionParser Implements Interface CronParser 
  which includes the business logic to take in the valid cron expression and parses it.
   
FieldParser
   implements the five fields parsing techniques based on different strategy like comma, slashes, asterik, hyphen

Enum
CronExpressionFieldType
   MINUTE(0, 59),
   HOUR(0, 23),
   DAY_OF_MONTH(1, 31),
   MONTH(1, 12),
   DAY_OF_WEEK(1, 7),

Exception

InvalidCronExpressionException


Way to Run

After cloning the project, just run the following command to get your cron string parsed. This is assuming you have javaEE installed:

java Main */15 0 1,15 * 1-5 /usr/bin/find

There is a Test class for proper testing of the valid and invalid cron expressions "CronExpressionTest" implementing junit


