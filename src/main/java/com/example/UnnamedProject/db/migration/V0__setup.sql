create table hdd
(
    id              uuid    not null
        primary key,
    brand           varchar(255),
    drive_slot_type varchar(255),
    name            varchar(255),
    price           integer not null,
    status          varchar(255),
    volumegb        integer not null
);

alter table hdd
    owner to axrdjhmstcveys;

create table laptop_items
(
    id              uuid    not null
        primary key,
    hdd_id          uuid,
    laptop_id       uuid,
    price           integer not null,
    ram_id          uuid,
    warranty_months integer not null
);

alter table laptop_items
    owner to axrdjhmstcveys;

create table laptops
(
    id              uuid    not null
        primary key,
    brand           varchar(255),
    drive_slot_type integer,
    name            varchar(255),
    price           integer not null,
    ram_type        varchar(255),
    status          varchar(255)
);

alter table laptops
    owner to axrdjhmstcveys;

create table log
(
    id       uuid not null
        primary key,
    staff_id uuid,
    type     varchar(255)
);

alter table log
    owner to axrdjhmstcveys;

create table log_entry_action_ids
(
    log_entry_id uuid not null
        constraint fknorleschyolvj6asykubu64qc
            references log,
    action_ids   uuid
);

alter table log_entry_action_ids
    owner to axrdjhmstcveys;

create table orders
(
    id   uuid not null
        primary key,
    date bigint
);

alter table orders
    owner to axrdjhmstcveys;

create table order_item_ids
(
    order_id uuid not null
        constraint fk36ypmk9p30utttuk39rdeduqh
            references orders,
    item_ids uuid
);

alter table order_item_ids
    owner to axrdjhmstcveys;

create table ram
(
    id       uuid    not null
        primary key,
    brand    varchar(255),
    name     varchar(255),
    price    integer not null,
    ram_type varchar(255),
    status   varchar(255),
    volumegb integer not null
);

alter table ram
    owner to axrdjhmstcveys;

create table smartphone_chargers
(
    id                uuid    not null
        primary key,
    brand             varchar(255),
    charger_plug_type varchar(255),
    name              varchar(255),
    price             integer not null,
    status            varchar(255),
    voltage           integer not null
);

alter table smartphone_chargers
    owner to axrdjhmstcveys;

create table smartphone_items
(
    id              uuid    not null
        primary key,
    charger_id      uuid,
    price           integer not null,
    smartphone_id   uuid,
    warranty_months integer not null
);

alter table smartphone_items
    owner to axrdjhmstcveys;

create table smartphones
(
    id                uuid    not null
        primary key,
    brand             varchar(255),
    charger_plug_type varchar(255),
    charger_voltage   integer not null,
    name              varchar(255),
    price             integer not null,
    status            varchar(255)
);

alter table smartphones
    owner to axrdjhmstcveys;

create table ssd
(
    id              uuid    not null
        primary key,
    brand           varchar(255),
    drive_slot_type varchar(255),
    name            varchar(255),
    price           integer not null,
    status          varchar(255),
    volumegb        integer not null
);

alter table ssd
    owner to axrdjhmstcveys;

