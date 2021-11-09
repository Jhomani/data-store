libsPath='./libs/*';

eval "javac -cp \".:$libsPath\" -d ./build ./src/main/handler/*.java";
# eval 'cd ./build';
# eval "java --class-path \".:.$libsPath\" handler.Main";

# wget https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.26/mysql-connector-java-8.0.26.jar -O ./mysql-connector-java  # for download;
# eval "javac -d ./build ./src/main/handler/*.java";
# eval 'cd ./build';
# eval "java  handler.Main";