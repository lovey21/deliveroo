# deliveroo
DeliverooCodeAssignmentTest

Supported features:

Cron Expressions must consist of five "parts" followed by a command.
asterisk (*)
basic slashes, for some time interval x (e.g. `*/15)
basic commas (e.g. 1,2)
hyphens (e.g. 1-7)

Features not supported 

words in place of Month or Day of week
question marks, @ 

Implementation Details
classes

CronExpressionParser implements CronParser 
   implements the business logic to take in the valid cron expression and parses it.
   
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
