# riverlife
Web application to search information for A Day in Hudson project.
* http://app.ldeo-grg.org:8080/riverlife/chartsAlongRiver.xhtml
* http://app.ldeo-grg.org:8080/riverlife/chartsOverTime.xhtml

The above two sites are embedded to the following site.
https://www.ldeo.columbia.edu/edu/k12/snapshotday/Data.html

### Installing
You must configure the data-access-config.xml with the database connection information in WebContent/WEB-INF/classes/data-access-config.xml.

This project is compiled using Apache Ant and the relevant commands can be found within the build.xml file. A .war file can be packaged using the Ant war command.

### Deploying
To deploy the app to a Tomcat server you can copy the .war package to the webapps directory and restart the Tomcat instance.