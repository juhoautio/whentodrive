# whentodrive

This application is able to retrieve traffic fluency data from Digitraffic.

## Build
Build should work out of the box, just do `mvn compile` or `mvn test` to try it.

You need to provide your user information in `src/main/resources/conf.json`

– just run the tests and [Configuration] will generate `conf-template.json` for you (which you can then rename).

## TrafficWsClient
[TrafficWsClient] is a ws client that uses CXF generated classes
- See [pom.xml] for `cxf-codegen-plugin`
- See [TrafficWsClientTest] for usage example

## TrafficRsClient
[TrafficRsClient] is a ws.rs client that makes HTTP call.
- No dependency to CXF class generation
- See [TrafficRsClientTest] for usage example

[TrafficWsClient]: src/main/java/juhoautio/whentodrive/client/TrafficWsClient.java
[TrafficRsClient]: src/main/java/juhoautio/whentodrive/client/TrafficRsClient.java
[pom.xml]: pom.xml
[Configuration]: src/main/java/juhoautio/whentodrive/configuration/Configuration.java
[TrafficWsClientTest]: src/test/java/juhoautio/whentodrive/client/TrafficWsClientTest.java
[TrafficRsClientTest]: src/test/java/juhoautio/whentodrive/client/TrafficRsClientTest.java
