# Sysmap-parrot

backend monolito que representa uma rede social. 


### Collection

A Collection do postman com todos os endpoints está disponível na pasta:
(OBS: a collection já está setada, tá bem legal, vale a pena baixar)

```
doc/collection
```

[parrot-collection](doc/collection/parrot-collection.json)

---

### docker-hub: docker pull agathalouise/parrot-app

---
### Rodando a aplicação com docker

abra o terminal na pasta do projeto e execute os comandos

```
mvn clean
```

```
mvn install
```

```
docker build images
```

```
docker compose up -d
```

A aplicação passará a responder na porta: 8082

Lembre-se de configurar o localstorage
```
aws configure --profile default //mykey, mykey, us-west-2, json
```

```
aws s3 mb s3://parrot-bucket --endpoint-url [http://localhost:4566](http://localhost:4566/)
```

---

### Funções:

  - Crud de usuario
  - Crud de Post
  - Interagir com post com comentários e likes
  - Seguir usuários e ser seguido
  - Ver todas as pessoas da rede

### Stack

- Java 17
- Spring MongoDB
- Lombok
- Docker
- Docker Compose
- LocalStack

### Descrição dos arquivos de configuração do projeto

| Recurso                                           | Descrição                                                                                             |
|---------------------------------------------------| ----------------------------------------------------------------------------------------------------- |
| ./docker-compose                                  | Arquivo utilizado para definição do processo de construção da imagem do container para execução local |
| ./src/main/resource/ValidationMessages.properties | Arquivo de configurações das mensagens                                                                |
| ./pom.xml                                         | Arquivo de configuração da gestão de dependência deste projeto                                        |


### Endpoint de Monitoração

| Url                    | Descrição                                         |
|------------------------|---------------------------------------------------|
| /actuator/health       | Endpoint para verificar a saude da aplicação      |
| /swagger-ui/index.html | Endpoint para verificar a documentação do projeto |
