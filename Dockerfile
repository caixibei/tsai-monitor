FROM openjdk:8
MAINTAINER caixibei@139.com
ENV TZ=Asia/Shanghai
ENV LANG C.UTF-8
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN mkdir -p "/tsai-monitor"
WORKDIR /tsai-monitor
COPY ./target/tsai-monitor-1.1.0.jar /tsai-monitor
ENTRYPOINT ["java","-jar","/tsai-monitor/tsai-monitor-1.1.0.jar"]

