create table admins
(
    id            bigint       not null,
    creation_date timestamp(6),
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    middle_name   varchar(255),
    user_id       bigint,
    primary key (id)
);
create table business_skills
(
    id   bigint       not null,
    name varchar(255) not null,
    primary key (id)
);
create table candidate_business_skills
(
    id                bigint not null,
    business_skill_id bigint not null,
    candidate_id      bigint not null,
    primary key (id)
);
create table candidate_language
(
    candidate_id bigint not null,
    languages    varchar(255)
);
create table candidate_soft_skills
(
    id            bigint not null,
    candidate_id  bigint not null,
    soft_skill_id bigint not null,
    primary key (id)
);
create table candidate_tech_skills
(
    id                  bigint  not null,
    candidate_id        bigint  not null,
    main                boolean not null,
    tech_skill_id       bigint  not null,
    years_of_experience integer not null,
    primary key (id)
);
create table candidates
(
    id                    bigint       not null,
    creation_date         timestamp(6),
    first_name            varchar(255) not null,
    last_name             varchar(255) not null,
    middle_name           varchar(255) not null,
    tags                  varchar(255),
    years_of_experience   integer      not null,
    country_id            bigint,
    notice_period_id      bigint,
    salary_expectation_id bigint,
    user_id               bigint,
    primary key (id)
);
create table companies
(
    id            bigint       not null,
    creation_date timestamp(6),
    name          varchar(255) not null,
    primary key (id)
);
create table countries
(
    id              bigint       not null,
    active          boolean      not null,
    code            varchar(255) not null,
    creation_date   timestamp(6),
    currency_code   varchar(255),
    currency_name   varchar(255),
    currency_symbol varchar(255),
    deleted_at      timestamp(6),
    dial_code       varchar(255),
    has_postal_code boolean,
    name            varchar(255),
    primary key (id)
);
create table job_roles
(
    id            bigint not null,
    creation_date timestamp(6),
    name          varchar(255),
    primary key (id)
);
create table notice_periods
(
    id       bigint       not null,
    amount   integer      not null,
    interval varchar(255) not null,
    primary key (id)
);
create table recruiters
(
    id            bigint       not null,
    creation_date timestamp(6),
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    middle_name   varchar(255),
    company_id    bigint,
    user_id       bigint,
    primary key (id)
);
create table salary_expectations
(
    id       bigint         not null,
    amount   numeric(38, 2) not null,
    currency varchar(255)   not null,
    type     varchar(255)   not null,
    primary key (id)
);
create table soft_skills
(
    id   bigint       not null,
    name varchar(255) not null,
    primary key (id)
);
create table tech_skills
(
    id       bigint       not null,
    logo_url varchar(255) not null,
    name     varchar(255) not null,
    tags     varchar(255),
    primary key (id)
);
create table users
(
    id            bigint       not null,
    creation_date timestamp(6),
    email         varchar(255) not null,
    enabled       boolean      not null,
    password      varchar(255) not null,
    user_role     varchar(255),
    primary key (id)
);
alter table if exists candidate_business_skills add constraint UK9ft67gd5opuv8q3y4tdu4kiya unique (candidate_id, business_skill_id);
alter table if exists candidate_soft_skills add constraint UK4tl99ndw03e2gvp1e7p9bqv2e unique (candidate_id, soft_skill_id);
alter table if exists candidate_tech_skills add constraint UKa2870retjs7jn6hy7ifhny2ch unique (candidate_id, tech_skill_id);
alter table if exists countries add constraint UK_5dhgnik9p8t72kaktdb8kd8dt unique (code);
alter table if exists users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table if exists admins add constraint FKgc8dtql9mkq268detxiox7fpm foreign key (user_id) references users;
alter table if exists candidate_language add constraint FK2cgkvwccdjdxgjnx8l05356ip foreign key (candidate_id) references candidates;
alter table if exists candidates add constraint FK54boc1c57wsvwsventskhsgde foreign key (country_id) references countries;
alter table if exists candidates add constraint FKdxp817wfehnk52mghndiqph9r foreign key (notice_period_id) references notice_periods;
alter table if exists candidates add constraint FKcpyssyi5f5fj5l8m38nw1upuf foreign key (salary_expectation_id) references salary_expectations;
alter table if exists candidates add constraint FKme4fkelukmx2s63tlcrft6hio foreign key (user_id) references users;
alter table if exists recruiters add constraint FKbfxdaa7me3dlmmd3hqd2uqpc2 foreign key (company_id) references companies;
alter table if exists recruiters add constraint FK1edjvp9udx35rophqr7imremb foreign key (user_id) references users;