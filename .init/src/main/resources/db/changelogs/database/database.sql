--liquibase formatted sql

--changeset rakovets:database-1 dbms:postgresql
-- precondition
CREATE SCHEMA IF NOT EXISTS "01_jdbc";
CREATE SCHEMA IF NOT EXISTS "02_dao";
CREATE SCHEMA IF NOT EXISTS "03_dbcp";
CREATE SCHEMA IF NOT EXISTS "04_intro_jpa";
CREATE SCHEMA IF NOT EXISTS "05_intro_hibernate";
CREATE SCHEMA IF NOT EXISTS "06_mapping_basic";
CREATE SCHEMA IF NOT EXISTS "07_mapping_inheritance";
CREATE SCHEMA IF NOT EXISTS "08_mapping_relationship";
CREATE SCHEMA IF NOT EXISTS "09_query_jpql";
CREATE SCHEMA IF NOT EXISTS "10_query_criteria";
--rollback DROP SCHEMA "01_jdbc" CASCADE;
--rollback DROP SCHEMA "02_dao" CASCADE;
--rollback DROP SCHEMA "03_dbcp" CASCADE;
--rollback DROP SCHEMA "04_intro_jpa" CASCADE;
--rollback DROP SCHEMA "05_intro_hibernate" CASCADE;
--rollback DROP SCHEMA "06_mapping_basic" CASCADE;
--rollback DROP SCHEMA "07_mapping_inheritance" CASCADE;
--rollback DROP SCHEMA "08_mapping_relationship" CASCADE;
--rollback DROP SCHEMA "09_query_jpql" CASCADE;
--rollback DROP SCHEMA "10_query_criteria" CASCADE;

--changeset rakovets:database-1 dbms:mariadb
-- precondition
CREATE SCHEMA IF NOT EXISTS 01_jdbc;
CREATE SCHEMA IF NOT EXISTS 02_dao;
CREATE SCHEMA IF NOT EXISTS 03_dbcp;
CREATE SCHEMA IF NOT EXISTS 04_intro_jpa;
CREATE SCHEMA IF NOT EXISTS 05_intro_hibernate;
CREATE SCHEMA IF NOT EXISTS 06_mapping_basic;
CREATE SCHEMA IF NOT EXISTS 07_mapping_inheritance;
CREATE SCHEMA IF NOT EXISTS 08_mapping_relationship;
CREATE SCHEMA IF NOT EXISTS 09_query_jpql;
CREATE SCHEMA IF NOT EXISTS 10_query_criteria;
--rollback DROP SCHEMA 01_jdbc;
--rollback DROP SCHEMA 02_dao;
--rollback DROP SCHEMA 03_dbcp;
--rollback DROP SCHEMA 04_intro_jpa;
--rollback DROP SCHEMA 05_intro_hibernate;
--rollback DROP SCHEMA 06_mapping_basic;
--rollback DROP SCHEMA 07_mapping_inheritance;
--rollback DROP SCHEMA 08_mapping_relationship;
--rollback DROP SCHEMA 09_query_jpql;
--rollback DROP SCHEMA 10_query_criteria;

--changeset rakovets:database-2 dbms:mariadb
GRANT ALL PRIVILEGES ON 01_jdbc.* TO 'mariadb'@'%';
GRANT ALL PRIVILEGES ON 02_dao.* TO 'mariadb'@'%';
GRANT ALL PRIVILEGES ON 03_dbcp.* TO 'mariadb'@'%';
GRANT ALL PRIVILEGES ON 04_intro_jpa.* TO 'mariadb'@'%';
GRANT ALL PRIVILEGES ON 05_intro_hibernate.* TO 'mariadb'@'%';
GRANT ALL PRIVILEGES ON 06_mapping_basic.* TO 'mariadb'@'%';
GRANT ALL PRIVILEGES ON 07_mapping_inheritance.* TO 'mariadb'@'%';
GRANT ALL PRIVILEGES ON 08_mapping_relationship.* TO 'mariadb'@'%';
GRANT ALL PRIVILEGES ON 09_query_jpql.* TO 'mariadb'@'%';
GRANT ALL PRIVILEGES ON 10_query_criteria.* TO 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON 01_jdbc.* FROM 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON 02_dao.* FROM 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON 03_dbcp.* FROM 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON 04_intro_jpa.* FROM 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON 05_intro_hibernate.* FROM 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON 06_mapping_basic.* FROM 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON 07_mapping_inheritance.* FROM 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON 08_mapping_relationship.* FROM 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON 09_query_jpql.* FROM 'mariadb'@'%';
--rollback REVOKE ALL PRIVILEGES ON 10_query_criteria.* FROM 'mariadb'@'%';
