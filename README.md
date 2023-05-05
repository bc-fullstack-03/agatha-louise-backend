# Sysmap-parrot

backend monolito que representa uma rede social. 

### Funções:
  - Criar um usuario
  - Criar um post
  - Interagir com post com comentários e likes
  - Ver lista de posts
  - Ver perfil
  - Seguir usuários e ser seguido
  - Ver todas as pessoas da rede

### Stack

- Java 17
- Spring MongoDB
- Lombok
- Docker
- Docker Compose
- Kafka

### Descrição dos arquivos de configuração do projeto

| Recurso                                           | Descrição                                                                                             |
|---------------------------------------------------| ----------------------------------------------------------------------------------------------------- |
| ./docker-compose                                  | Arquivo utilizado para definição do processo de construção da imagem do container para execução local |
| ./src/main/resource/ValidationMessages.properties | Arquivo de configurações das mensagens                                                                |
| ./pom.xml                                         | Arquivo de configuração da gestão de dependência deste projeto                                        |


### Collection

A Collection do postman com todos os endpoints está disponível na pasta:

```
doc/collection
```

[collection-parrot](doc/collection/colocarcollection)

### Endpoint de Monitoração

| Url                    | Descrição                                         |
|------------------------|---------------------------------------------------|
| /health                | Endpoint para verificar a saude da aplicação      |
| /swagger-ui/index.html | Endpoint para verificar a documentação do projeto |
