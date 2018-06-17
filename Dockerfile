#######################################
# Platform builder
#
# This is a base image used by the
# threshold/gateway image.
#######################################

FROM maven:3.5-jdk-8

ARG PLATFORM_VERSION="1.3.1.Kamara-1"

COPY . /build/apiman
WORKDIR /build/apiman

ENV MAVEN_OPTS="-Xmx1536m"
RUN mvn clean install -Dmaven.test.skip=true
RUN unzip distro/tomcat8/target/apiman-distro-tomcat8-${PLATFORM_VERSION}-overlay.zip -d /build/output

RUN unzip /build/output/webapps/apiman-gateway.war -d /build/output/webapps/ROOT
RUN rm /build/output/webapps/*.war
