# Exemplo de Java DAO e SQLite

## COMPILAR

Pra compilar nosso projeto via linha de comando, vamos criar um arquivo contendo todos os nossos arquivos fontes:

**No Windows:**

dir /s /B *.java > sources.txt

**No Linux:**

find -name "*.java" > sources.txt

Depois basta complilarmos o projeto com:

javac @sources.txt

## EXECUTAR

Considerando que driver de conexão esteja na pasta libs e estamos na pasta raiz, execute o seguinte comando:

java -classpath .:libs/sqlite-jdbc-3.32.3.2.jar src.com.app.main.Main
