create table candidate_language
(
    candidate_id bigint not null,
    languages    varchar(255)
);
create table candidate_technology
(
    candidate_id  bigint not null,
    technology_id bigint not null
);
create table candidates
(
    id                  bigint       not null,
    creation_date       timestamp(6),
    first_name          varchar(255) not null,
    last_name           varchar(255) not null,
    middle_name         varchar(255),
    notice_period       integer      not null,
    salary_expectation  numeric(38, 2),
    tags                varchar(255),
    type                varchar(255),
    years_of_experience integer      not null,
    main_technology_id  bigint,
    user_id             bigint,
    country_id          bigint,
    company_id          bigint,
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
create table technologies
(
    id       bigint       not null,
    logo_url varchar(255) not null,
    name     varchar(255) not null,
    tags     varchar(255),
    primary key (id)
);
create table users
(
    id            bigint  not null,
    creation_date timestamp(6),
    email         varchar(255),
    enabled       boolean not null,
    password      varchar(255),
    user_role     varchar(255),
    primary key (id)
);
alter table if exists countries
    add constraint UK_5dhgnik9p8t72kaktdb8kd8dt unique (code);
alter table if exists users
    add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table if exists candidate_language
    add constraint FK2cgkvwccdjdxgjnx8l05356ip foreign key (candidate_id) references candidates;
alter table if exists candidate_technology
    add constraint FKciseho3w77faqofxdty4hbtag foreign key (technology_id) references technologies;
alter table if exists candidate_technology
    add constraint FKdksytmpf3sl19egemist8epuv foreign key (candidate_id) references candidates;
alter table if exists candidates
    add constraint FKe7hi7mfxgoaoekwnciaomptk0 foreign key (main_technology_id) references technologies;
alter table if exists candidates
    add constraint FKme4fkelukmx2s63tlcrft6hio foreign key (user_id) references users;
alter table if exists candidates
    add constraint FK54boc1c57wsvwsventskhsgde foreign key (country_id) references countries;
alter table if exists candidates
    add constraint FK6s9x8ij6anv9n1u4tcqgxtor5 foreign key (company_id) references companies;