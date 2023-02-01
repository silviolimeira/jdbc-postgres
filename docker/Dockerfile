FROM postgres 
ENV POSTGRES_PASSWORD root
ENV POSTGRES_DB dslelivery
COPY ./database_dsdeliver.sql /docker-entrypoint-initdb.d/

# docker build -t my-postgres-db ./

# docker images -a

# docker run -d --name my-postgresdb-container -p 5432:5432 my-postgres-db

# docker exec -it my-postgresdb-container /bin/bash

