package com.example.UnnamedProject.db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import java.sql.Statement;

public class V0__setup extends BaseJavaMigration
{
    //TODO
    public void migrate(Context context) throws Exception
    {
        Statement statement=context.getConnection().createStatement();
        statement.executeUpdate
               ("create table hdd\n" +
                "(\n" +
                "    id              uuid    not null\n" +
                "        primary key,\n" +
                "    brand           varchar(255),\n" +
                "    drive_slot_type varchar(255),\n" +
                "    name            varchar(255),\n" +
                "    price           integer not null,\n" +
                "    status          varchar(255),\n" +
                "    volumegb        integer not null\n" +
                ");\n" +
                "\n" +
                "alter table hdd\n" +
                "    owner to axrdjhmstcveys;\n" +
                "\n" +
                "create table laptop_items\n" +
                "(\n" +
                "    id              uuid    not null\n" +
                "        primary key,\n" +
                "    hdd_id          uuid,\n" +
                "    laptop_id       uuid,\n" +
                "    price           integer not null,\n" +
                "    ram_id          uuid,\n" +
                "    warranty_months integer not null\n" +
                ");\n" +
                "\n" +
                "alter table laptop_items\n" +
                "    owner to axrdjhmstcveys;\n" +
                "\n" +
                "create table laptops\n" +
                "(\n" +
                "    id              uuid    not null\n" +
                "        primary key,\n" +
                "    brand           varchar(255),\n" +
                "    drive_slot_type integer,\n" +
                "    name            varchar(255),\n" +
                "    price           integer not null,\n" +
                "    ram_type        varchar(255),\n" +
                "    status          varchar(255)\n" +
                ");\n" +
                "\n" +
                "alter table laptops\n" +
                "    owner to axrdjhmstcveys;\n" +
                "\n" +
                "create table log\n" +
                "(\n" +
                "    id       uuid not null\n" +
                "        primary key,\n" +
                "    staff_id uuid,\n" +
                "    type     varchar(255)\n" +
                ");\n" +
                "\n" +
                "alter table log\n" +
                "    owner to axrdjhmstcveys;\n" +
                "\n" +
                "create table log_entry_action_ids\n" +
                "(\n" +
                "    log_entry_id uuid not null\n" +
                "        constraint fknorleschyolvj6asykubu64qc\n" +
                "            references log,\n" +
                "    action_ids   uuid\n" +
                ");\n" +
                "\n" +
                "alter table log_entry_action_ids\n" +
                "    owner to axrdjhmstcveys;\n" +
                "\n" +
                "create table orders\n" +
                "(\n" +
                "    id   uuid not null\n" +
                "        primary key,\n" +
                "    date bigint\n" +
                ");\n" +
                "\n" +
                "alter table orders\n" +
                "    owner to axrdjhmstcveys;\n" +
                "\n" +
                "create table order_item_ids\n" +
                "(\n" +
                "    order_id uuid not null\n" +
                "        constraint fk36ypmk9p30utttuk39rdeduqh\n" +
                "            references orders,\n" +
                "    item_ids uuid\n" +
                ");\n" +
                "\n" +
                "alter table order_item_ids\n" +
                "    owner to axrdjhmstcveys;\n" +
                "\n" +
                "create table ram\n" +
                "(\n" +
                "    id       uuid    not null\n" +
                "        primary key,\n" +
                "    brand    varchar(255),\n" +
                "    name     varchar(255),\n" +
                "    price    integer not null,\n" +
                "    ram_type varchar(255),\n" +
                "    status   varchar(255),\n" +
                "    volumegb integer not null\n" +
                ");\n" +
                "\n" +
                "alter table ram\n" +
                "    owner to axrdjhmstcveys;\n" +
                "\n" +
                "create table smartphone_chargers\n" +
                "(\n" +
                "    id                uuid    not null\n" +
                "        primary key,\n" +
                "    brand             varchar(255),\n" +
                "    charger_plug_type varchar(255),\n" +
                "    name              varchar(255),\n" +
                "    price             integer not null,\n" +
                "    status            varchar(255),\n" +
                "    voltage           integer not null\n" +
                ");\n" +
                "\n" +
                "alter table smartphone_chargers\n" +
                "    owner to axrdjhmstcveys;\n" +
                "\n" +
                "create table smartphone_items\n" +
                "(\n" +
                "    id              uuid    not null\n" +
                "        primary key,\n" +
                "    charger_id      uuid,\n" +
                "    price           integer not null,\n" +
                "    smartphone_id   uuid,\n" +
                "    warranty_months integer not null\n" +
                ");\n" +
                "\n" +
                "alter table smartphone_items\n" +
                "    owner to axrdjhmstcveys;\n" +
                "\n" +
                "create table smartphones\n" +
                "(\n" +
                "    id                uuid    not null\n" +
                "        primary key,\n" +
                "    brand             varchar(255),\n" +
                "    charger_plug_type varchar(255),\n" +
                "    charger_voltage   integer not null,\n" +
                "    name              varchar(255),\n" +
                "    price             integer not null,\n" +
                "    status            varchar(255)\n" +
                ");\n" +
                "\n" +
                "alter table smartphones\n" +
                "    owner to axrdjhmstcveys;\n" +
                "\n" +
                "create table ssd\n" +
                "(\n" +
                "    id              uuid    not null\n" +
                "        primary key,\n" +
                "    brand           varchar(255),\n" +
                "    drive_slot_type varchar(255),\n" +
                "    name            varchar(255),\n" +
                "    price           integer not null,\n" +
                "    status          varchar(255),\n" +
                "    volumegb        integer not null\n" +
                ");\n" +
                "\n" +
                "alter table ssd\n" +
                "    owner to axrdjhmstcveys;\n" +
                "\n");
    }
}
