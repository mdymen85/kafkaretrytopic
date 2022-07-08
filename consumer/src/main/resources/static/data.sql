create table if not exists control_key
(
    id    bigint auto_increment
    primary key,
    kafka_key varchar(50) not null,
    count int         not null,
    constraint control_key_kafka_key_uindex
    unique (kafka_key)
    );

create table if not exists event_consumer
(
    id   bigint auto_increment
    primary key,
    uuid varchar(40) not null,
    second varchar(40) not null,
    json text        not null
    );
