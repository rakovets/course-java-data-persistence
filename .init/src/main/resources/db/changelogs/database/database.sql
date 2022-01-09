--liquibase formatted sql

--changeset rakovets:database-1 dbms:postgresql
-- precondition
CREATE SCHEMA IF NOT EXISTS jdbc;
CREATE SCHEMA IF NOT EXISTS dao;
CREATE SCHEMA IF NOT EXISTS intro_jpa;
CREATE SCHEMA IF NOT EXISTS intro_hibernate;
--rollback DROP SCHEMA jdbc CASCADE;
--rollback DROP SCHEMA dao CASCADE;
--rollback DROP SCHEMA intro_jpa CASCADE;
--rollback DROP SCHEMA intro_hibernate CASCADE;

--changeset rakovets:database-1 dbms:mariadb
-- precondition
CREATE SCHEMA IF NOT EXISTS jdbc;
CREATE SCHEMA IF NOT EXISTS dao;
CREATE SCHEMA IF NOT EXISTS intro_jpa;
CREATE SCHEMA IF NOT EXISTS intro_hibernate;
--rollback DROP SCHEMA jdbc;
--rollback DROP SCHEMA dao;
--rollback DROP SCHEMA intro_jpa;
--rollback DROP SCHEMA intro_hibernate;

--changeset rakovets:database-2 dbms:mariadb
GRANT ALL PRIVILEGES ON jdbc.* TO 'mariadb'@'%';
GRANT ALL PRIVILEGES ON dao.* TO 'mariadb'@'%';
GRANT ALL PRIVILEGES ON intro_jpa.* TO 'mariadb'@'%';
GRANT ALL PRIVILEGES ON intro_hibernate.* TO 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON jdbc.* FROM 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON dao.* FROM 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON intro_jpa.* FROM 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON intro_hibernate.* FROM 'mariadb'@'%';
