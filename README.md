# riverlife
Web application to search information for A Day in Hudson project.
* http://app.ldeo-grg.org:8080/riverlife/chartsAlongRiver.xhtml
* http://app.ldeo-grg.org:8080/riverlife/chartsOverTime.xhtml
* http://li1104-28.members.linode.com:8080/riverlife/chartsAlongRiver.xhtml
* http://li1104-28.members.linode.com:8080/riverlife/chartsOverTime.xhtml

The above two sites are embedded to the following site.
https://www.ldeo.columbia.edu/edu/k12/snapshotday/Data.html

New site: https://lamont.columbia.edu/education-outreach/school-programs-day-in-life-hudson/data

### Installing
You must configure the data-access-config.xml with the database connection information in WebContent/WEB-INF/classes/data-access-config.xml.

This project is compiled using Apache Ant and the relevant commands can be found within the build.xml file. A .war file can be packaged using the Ant war command.

### Deploying
The application and database is deployed on a Linode server. Contact Margie Turrin for connection information. You may need to update the iptables configuration.

To deploy the app to a Tomcat server you can copy the .war package to the webapps directory and restart the Tomcat instance.

Example Commands:

* ```service grg-tomcat stop```
* ```cp ~/riverlife-v7-prod.war /opt/server/tomcat/webapps/riverlife.war```
* ```rm -rf /opt/server/tomcat/webapps/riverlife```
* ```service grg-tomcat start```