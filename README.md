### Deploy de uma biblioteca no nexus

Estudo de como criar um biblioteca com gradle + repositorio nexus e uma aplicação que consome essa biblioteca por meio do nexus

### Acesso ao nexus

```
docker-compose up
```

Access

```
http://localhost:8081/
```


A senha está no arquivo /nexus-data/admin.password 


Nesse exemplo foi permitido o acesso anonimo


### Build da biblioteca

```
cd mylib
./gradlew build
```

### Deploy da aplicação 

* Abre o nexus http://localhost:8081/
* Loga como administrador
* Vai na opção upload
* Seleciona o repositorio
* Nos campos é importante marcar a opção 'Generate a POM file with these coordinates'

### Como utilizar a biblioteca que está no nexus

* Vai em outra aplicação, nesse exemplo em appgrails
* Adicionar o repositoriono arquivo build.gradle, nesse caso como o nexus local e não tem https tem que permitir o funcionamento por http
```
maven {
        url "http://localhost:8081/repository/maven-releases/"
        allowInsecureProtocol = true
    }
```
* Adiciona a biblioteca também
```
implementation "com.fernando:mylib:1.0.0"
```


### Biblioteca com codigo fonte e java doc

No build.gralde da biblioteca adicona

```
java {
    //Generating Sources JAR
    //You can easily generate a sources JAR for your library:
    withSourcesJar()
    //You can also generate a Javadoc JAR for your library:
    withJavadocJar()
}
```
É interessante coloca a versão tb
```
version = '1.1.0'
```

NO momento do build vai gerar 3 arquivos, tem que fazer o upload do 3 no nexus.
Para adicionar o arquivo do source e javadoc vai na opção 'Add another assert' e no 
campo 'Classifier' informa source e javadoc respectivamente para cada um. No final será
feito o upload dos 3 arquivos, do binário, source e javadoc.


### Material de apoio de como construir a biblioteca com o gradle

https://docs.gradle.org/7.5.1/samples/sample_building_java_libraries.html#publish_a_build_scan



### COmando para criar .gitkeep recursivamente

find . -type d -empty -not -path "./.git/*" -exec touch {}/.gitkeep \;