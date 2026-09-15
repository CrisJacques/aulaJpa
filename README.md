# aulaJpa

Projeto de estudo para demonstrar o uso de JPA com Hibernate e MySQL. O repositório contém uma entidade `Pessoa` mapeada com anotações JPA e um exemplo simples de persistência e consulta no banco de dados.

## Pré-requisitos

Antes de executar a aplicação, você deve criar o banco de dados `aulajpa` no MySQL:

```sql
CREATE DATABASE aulajpa;
```

## Configuração do banco

1. Renomeie o arquivo `src/main/resources/META-INF/persistence.example.xml` para `src/main/resources/META-INF/persistence.xml`.
2. Ajuste o usuário e a senha de conexão (`javax.persistence.jdbc.user` e `javax.persistence.jdbc.password`) de acordo com a configuração da sua máquina.
3. Verifique também a URL do banco, caso suas credenciais ou porta sejam diferentes.

## Observações importantes

A configuração do Hibernate no arquivo `persistence.xml` está com `hibernate.hbm2ddl.auto` definido como `create`. Isso significa que, a cada inicialização da aplicação, o banco de dados será recriado novamente.

Para manter os dados entre execuções, basta alterar:

```xml
<property name="hibernate.hbm2ddl.auto" value="create" />
```

para:

```xml
<property name="hibernate.hbm2ddl.auto" value="update" />
```

## Objetivo do projeto

Este repositório serve como exemplo didático de:

- mapeamento de entidades com JPA;
- configuração de unidade de persistência;
- uso de `EntityManager` para persistir e consultar dados;
- integração com MySQL usando Hibernate.
