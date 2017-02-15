# limesurvey-rc
A Java 8 client for using the Limesurvey Remote Control

# Introduction
This library uses Java 8 and streams, it provides predefined methods for using Remote Control from your Limesurvey instance.

# Building
This project use Maven and can be build doing :
```
mvn clean install
```
Then you can add the jar to your project.

# Maven

This project is on Maven Central, you just have to add the dependency to your pom.xml
```
<dependency>
    <groupId>com.github.falydoor</groupId>
    <artifactId>limesurvey-rc</artifactId>
    <version>0.1</version>
</dependency>
```

# Usage
Simply create a new LimesurveyRC object by passing the correct credentials
```java
LimesurveyRC limesurveyRC = new LimesurveyRC("remoteControlUrl", "user", "password");
```
And then you can start calling methods directly on your LimesurveyRC object.

Example:
```java
// Display the title of all your active surveys
limesurveyRC.getActiveSurveys().forEach(survey -> System.out.println(survey.getTitle()));
```

### Change the default key timeout
By default, the key timeout is set to 2 hours (7200 seconds).
If the value of "iSessionExpirationTime" in your config-defaults.php is different, you have to set it using this method.
