grandma
=======

To run, create a file src/main/resources/grandma.properties which contains
number.from=number to send SMS from
google.apikey=for directions api
twilio.sid=for sms service
twilio.token=for sms service

run using 
mvn clean insall
mvn exec:java -Dexec.mainClass=grandma.App -DphoneNumber=whatever -Dfrom="" -Dto=""
