# Use alpine linux w/ java8
FROM openjdk:8-jdk-alpine

VOLUME /tmp

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

# The alpine linux being used has no fonts available.
# font-bitstream-type1 & ghostscript-fonts packages are installed.
# /usr/share/fonts/ - fonts installation directory
# /etc/ImageMagick-7/type.xml - font configuration file
RUN apk upgrade --update \
    && apk add --no-cache --virtual .build-deps imagemagick \
    font-bitstream-type1 ghostscript-fonts

ENTRYPOINT ["java","-cp","app:app/lib/*","nz.net.example.wordart.gen.Application"]