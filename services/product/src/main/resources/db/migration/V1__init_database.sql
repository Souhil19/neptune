create table if not exists category (
    id integer not null primary key,
    description varchar(255) not null,
    name varchar(255) not null
);

create table if not exists product (
    id integer not null primary key,
    description varchar(255) not null,
    name varchar(255) not null,
    available_quantity double precision not null,
    price numeric(38, 2) not null,
    category_id integer
        constraint fk_category
            references category
            on delete restrict
            on update restrict
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;