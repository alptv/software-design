FROM    bellsoft/liberica-openjdk-alpine:17.0.6 as builder

WORKDIR /product
COPY    ./gradle ./gradle
COPY    ./src ./src
COPY    ./build.gradle ./build.gradle
COPY    ./settings.gradle ./settings.gradle
COPY    ./gradlew ./gradlew
RUN     ./gradlew bootJar
RUN     cp ./build/libs/product.jar .

FROM    bellsoft/liberica-openjre-alpine:17.0.6
WORKDIR /product
COPY    --from=builder /product/product.jar .
CMD     java -jar product.jar