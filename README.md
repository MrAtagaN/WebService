Установка необходимых программ для сборки

1 скачать npm: https://nodejs.org/en/download/
2 скачать angular cli: npm install -g @angular/cli
3 в директории Frontend выполнить комманду: npm install


Сборка проекта

1 в корневой директории (WebService) выполнить комманду: mvn clean install
2 результатом сборки будет Backend-1.0-SNAPSHOT-spring-boot.jar в директории:
  WebService\Backend\target\Backend-1.0-SNAPSHOT-spring-boot.jar


Запуск

1 запустить программу  java -jar Backend-1.0-SNAPSHOT-spring-boot.jar
2 сервис будет доступен по адресу http://localhost:8080/   (Возможно нужно будет очистить кэш браузера)

