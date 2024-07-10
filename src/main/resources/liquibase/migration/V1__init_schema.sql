--liquibase formatted sql

--changeset ChiniakinD:1
--comment: create table country
create table if not exists country
(
    id        text primary key,
    name      text    not null,
    is_active boolean not null default true
);

--changeset ChiniakinD:2
--comment: create table industry
create table if not exists industry
(
    id        serial primary key,
    name      text    not null,
    is_active boolean not null default true
);

--changeset ChiniakinD:3
--comment: create table org_form
create table if not exists org_form
(
    id        serial primary key,
    name      text    not null,
    is_active boolean not null default true
);

--changeset ChiniakinD:4
--comment: create table contractor
create table if not exists contractor
(
    id             varchar(12) primary key,
    parent_id      varchar(12),
    name           text      not null,
    name_full      text,
    inn            text,
    ogrn           text,
    country        text,
    industry       int4,
    org_form       int4,
    create_date    timestamp not null default now(),
    modify_date    timestamp,
    create_user_id text,
    modify_user_id text,
    is_active      boolean   not null default true,
    foreign key (parent_id) references contractor (id),
    foreign key (country) references country (id),
    foreign key (industry) references industry (id),
    foreign key (org_form) references org_form (id)
)