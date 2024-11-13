# Usa a imagem oficial do PostgreSQL
FROM postgres:latest

# Define variáveis de ambiente para o PostgreSQL e pgAdmin
ARG POSTGRES_PASSWORD=senha123
ARG PGADMIN_EMAIL=email@email.com
ARG PGADMIN_PASSWORD=senha123
ARG USER_HOME
ARG USER_NAME

# Define variáveis de ambiente para o sistema
ENV HOME=${USER_HOME}
ENV USER=${USER_NAME}
ENV POSTGRES_PASSWORD=${POSTGRES_PASSWORD}
ENV PGADMIN_DEFAULT_EMAIL=${PGADMIN_EMAIL}
ENV PGADMIN_DEFAULT_PASSWORD=${PGADMIN_PASSWORD}

# Cria o diretório do PostgreSQL com base na variável HOME
RUN mkdir -p $HOME/PostgreSQL

# Executa o container PostgreSQL com a senha e volume dinâmico
RUN docker run --name teste-postgres --network=postgres-network -e "POSTGRES_PASSWORD=${POSTGRES_PASSWORD}" -p 5432:5432 -v $HOME/PostgreSQL:/var/lib/postgresql/data -d postgresi

# Executa o container do pgAdmin com os dados de login dinâmicos
RUN docker run --name teste-pgadmin --network=postgres-network  -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=${PGADMIN_EMAIL}" -e "PGADMIN_DEFAULT_PASSWORD=${PGADMIN_PASSWORD}" -d dpage/pgadmin4

# Expõe as portas necessárias
EXPOSE 5432 15432

