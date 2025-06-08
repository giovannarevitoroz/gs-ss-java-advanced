# Swift Safety - Sistema de Alertas Climáticos

## Descritivo

O Swift Safety é uma solução tecnológica desenvolvida como parte do desafio proposto pela FIAP, no contexto de eventos climáticos extremos. O sistema é composto por um aplicativo móvel desenvolvido em React Native, integrado a um backend desenvolvido em Java com Spring Boot, que se comunica com sensores IoT para detectar e divulgar ocorrências de desastres ambientais, como enchentes e queimadas.

A aplicação atua como uma rede social de alertas climáticos, permitindo que usuários e órgãos públicos compartilhem informações, imagens e comentários em tempo real. Além disso, sensores IoT detectam automaticamente situações críticas e enviam alertas ao sistema, que os divulga no app e por meio de notificações push no celular dos usuários.

## Tecnologias utilizadas

- **Frontend Mobile**: React Native
- **Backend**: Java 17, Spring Boot 3.1.2
- **Banco de Dados**: Oracle SQL Developer
- **Deploy Backend**: Render
- **ORM**: JPA / Hibernate
- **Segurança**: Spring Security + JWT
- **Documentação da API**: SpringDoc OpenAPI (Swagger)

### Dependências Notáveis:
- spring-boot-starter-data-jpa
- spring-boot-starter-security
- jjwt (JWT)
- ojdbc8 (Oracle JDBC)
- springdoc-openapi
- Lombok

## Estrutura do banco de dados

A estrutura em Java da aplicação foi modelada com base nas necessidades da aplicação no banco de dados. Abaixo, as tabelas principais:

### Tabelas e Relacionamentos
- **USUARIO**: Representa os usuários cadastrados (público geral e funcionários).
- **BAIRRO**: Localização geográfica, usada em sensores e endereços.
- **TIPO_DESASTRE**: Define os tipos de eventos climáticos.
- **ENDERECO**: Endereço de uma ocorrência/postagem.
- **SENSOR**: Sensores instalados em regiões para detecção automática.
- **ALERTA**: Alerta gerado por sensores com base nos dados detectados.
- **POSTAGEM**: Compartilhamento de informações por usuários ou governos.
- **COMENTARIO**: Comentários em postagens.
- **CONFIRMA_POSTAGEM**: Confirmações para POST da postagem.

## Entidades Java

Cada tabela foi representada por uma entidade Java com anotações do JPA, Lombok e também de outras dependências para boas práticas e modularidade da construção do projeto:

```java
@Entity
@Table(name = "Usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email_usuario"),
        @UniqueConstraint(columnNames = "telefone_usuario")
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
}
```

Outras entidades seguem a mesma lógica: Sensor, Postagem, Comentario, Alerta, etc.

## Funcionalidades implementadas

### Backend (Java)
- CRUD completo para todas as entidades
- Segurança com autenticação JWT
- Validação de dados com Jakarta Bean Validation
- API RESTful documentada com Swagger/OpenAPI
- Integração com Oracle DB via JPA
- Deploy em nuvem com Render

### Mobile (React Native)
- Tela de login/cadastro
- Feed de postagens
- Visualização de alertas por região
- Detalhamento de desastres
- Notificações em tempo real
- Comentários e confirmações de eventos

## Integração com IOT ESP32 MQTT e Node-RED

O sistema prevê a integração com dispositivos de sensores IoT, que enviam dados climáticos automaticamente para o backend. Ao detectar valores críticos (ex: nível de água elevado), o sistema:
1. Gera um ALERTA com nível de risco.
2. Salva no banco e envia uma notificação ao aplicativo.
3. Exibe no feed em tempo real como se fosse uma postagem.

## Security

Utilizamos:
- Spring Security para proteção das rotas.
- JWT para autenticação e autorização.
- Perfis de acesso diferenciados: Usuário e Funcionário.

## Deploy

O backend Java foi publicado na nuvem usando o serviço Render, que permite:
- Deploy automático via GitHub
- Logs em tempo real
- Escalabilidade vertical automática

## Estrutura do Projeto em Camadas

O backend segue o padrão MVC + Service Layer + JWT Security, organizado em pacotes que promovem a separação de responsabilidades e facilitam a manutenção.

```
src/
├── model         → Entidades JPA (ex: Usuario, Postagem, Sensor)
├── repository    → Interfaces que estendem JpaRepository
├── service       → Regras de negócio e integração entre camadas
├── dto           → Objetos de transferência de dados (entrada/saída)
├── controller    → REST controllers com endpoints da API
├── security      → Configurações e classes de autenticação JWT
├── filter        → Filtros personalizados de autenticação (JWT)
├── exception     → Tratamento global de erros (Handler)
├── config        → Arquivos de configuração (CORS, Security)
```

## Explicação das Camadas

| Pacote      | Função |
|-------------|--------|
| model       | Define as entidades JPA, que refletem as tabelas do banco de dados. |
| repository  | Interfaces que acessam o banco de dados (estendem JpaRepository ou CrudRepository). |
| service     | Camada de regra de negócio da aplicação (processamentos e decisões). |
| dto         | Define objetos usados para entrada e saída de dados, evitando expor diretamente as entidades. |
| controller  | Responsável pelos endpoints REST. Comunica com o service e retorna as respostas da API. |
| security    | Contém a lógica de autenticação e geração/validação de tokens JWT. |
| filter      | Filtra e intercepta requisições para verificar o JWT antes de acessar os recursos. |
| exception   | Tratamento centralizado de erros via @ControllerAdvice. |
| config      | Configurações globais, como segurança, CORS e autenticação. |

## Instruções para Deploy e Execução

### Deploy no Render
1. Faça o push do projeto para o GitHub
2. Acesse https://render.com e crie um novo serviço
3. Configure:
   - Ambiente: Docker
   - Porta: 8080
   - Variáveis de ambiente: DB_URL, JWT_SECRET, DB_USERNAME, DB_PASSWORD
4. Conecte seu repositório e clique em Deploy

## Artefatos Requeridos na Entrega

### Repositórios com o código-fonte
- **Frontend Mobile (React Native)**: https://github.com/FIAP-MOBILE/global-solution-1-semestre-mottumotion
- **Backend Java (Spring Boot)**: https://github.com/giovannarevitoroz/gs-ss-java-advanced
- **Vídeo do Pitch**: [Pitch](https://www.youtube.com/watch?v=byoqks2usaM)
- **Link do vídeo de demonstração completa**: [Demonstração Completa
](https://www.youtube.com/watch?v=ACMUmdk7PzM)
## Links dos Deploys para Acesso e Testes

- **Backend na Render**: https://gs-java-advanced-deploy.onrender.com
- **Swagger (Documentação da API)**: https://gs-java-advanced-deploy.onrender.com/swagger-ui/index.html

## Instruções para Acesso e Teste

```
GET https://gs-java-advanced-deploy.onrender.com/api/usuarios
GET https://gs-java-advanced-deploy.onrender.com/api/sensores/tipo/{tipo}
POST https://gs-java-advanced-deploy.onrender.com/auth/login
```

E todas as outras rotas apresentadas através de:  
https://gs-java-advanced-deploy.onrender.com/swagger-ui/index.html


## Integrantes do Projeto

- Giovanna Revito Roz - RM 558981
- Kaian Gustavo de Oliveira Nascimento - RM 558986
- Lucas Kenji Kikuchi - RM 554424

## Conclusão

O projeto Swift Safety se propõe a ser uma ferramenta social e tecnológica para combate e prevenção de desastres naturais. Através da integração entre backend robusto em Java, frontend acessível em React Native e IoT, buscamos apoiar a população em momentos críticos, oferecendo informação confiável e em tempo real.
