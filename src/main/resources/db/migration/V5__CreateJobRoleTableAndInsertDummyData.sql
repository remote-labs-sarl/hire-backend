create table job_roles
(
    id            bigint       not null,
    creation_date timestamp(6),
    name          varchar(255) not null,
    primary key (id)
);

INSERT INTO job_roles(id, creation_date, name)
VALUES (1, now(), 'DEVELOPER'),
       (2, NOW(), 'ARCHITECT'),
       (3, now(), 'DEVOPS');