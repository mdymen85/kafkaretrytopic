create table if not exists control_key
(
    id    bigint auto_increment
    primary key,
    `key` varchar(50) not null,
    count int         not null,
    constraint control_key_key_uindex
    unique (`key`)
    );

create table if not exists event_consumer
(
    id   bigint auto_increment
    primary key,
    uuid varchar(40) not null,
    json text        not null,
    constraint event_consumer_uuid_uindex
    unique (uuid)
    );
