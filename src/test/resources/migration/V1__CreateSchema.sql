create table candidate_language
(
    candidate_id bigint not null,
    languages    varchar(255)
);
create table candidate_technology
(
    candidate_id  bigint not null,
    technology_id bigint not null,
    primary key (candidate_id, technology_id)
);
create table candidates
(
    id                  bigserial    not null,
    email               varchar(255) not null,
    first_name          varchar(255) not null,
    last_name           varchar(255) not null,
    middle_name         varchar(255),
    notice_period       integer      not null,
    salary_expectation  numeric(38, 2),
    tags                varchar(255),
    type                varchar(255),
    years_of_experience integer      not null,
    country_id          bigint,
    main_technology_id  bigint,
    primary key (id)
);
create table companies
(
    id   bigserial    not null,
    name varchar(255) not null,
    primary key (id)
);
create table countries
(
    id              bigserial    not null,
    active          boolean      not null,
    code            varchar(255) not null,
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
    id       bigserial    not null,
    logo_url varchar(255) not null,
    name     varchar(255) not null,
    tags     varchar(255),
    primary key (id)
);
alter table if exists countries add constraint UK_5dhgnik9p8t72kaktdb8kd8dt unique (code);
alter table if exists candidate_language add constraint FK2cgkvwccdjdxgjnx8l05356ip foreign key (candidate_id) references candidates;
alter table if exists candidate_technology add constraint FKciseho3w77faqofxdty4hbtag foreign key (technology_id) references technologies;
alter table if exists candidate_technology add constraint FKdksytmpf3sl19egemist8epuv foreign key (candidate_id) references candidates;
alter table if exists candidates add constraint FK54boc1c57wsvwsventskhsgde foreign key (country_id) references countries;
alter table if exists candidates add constraint FKe7hi7mfxgoaoekwnciaomptk0 foreign key (main_technology_id) references technologies;