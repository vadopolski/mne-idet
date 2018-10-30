create table consultant (
  id varchar(255) not null,
  name varchar(50) not null,
  primary key (id)
);

create table client (
  id bigserial not null,
  name varchar(50) not null,
  id_vk varchar(50) not null,
  description varchar(512) not null,
  primary key (id)
);

create table question_photo (
  id bigserial not null,
  id_photo varchar(50) not null,
  client_id bigint not null references client (id),
  question_photo_order bigint not null,
  description text not null,
  primary key (id)
);

create table hint_type (
  id bigserial not null,
  question_photo_id bigint not null references question_photo (id),
  hint_type_order bigint not null,
  evaluation boolean not null,
  primary key (id)
);

create table hint (
  id bigserial not null,
  consultant_id varchar(255) not null references consultant (id),
  hint_type_id bigint not null references hint_type (id),
  description text not null,
  date timestamp without time zone not null,
  primary key (id)
);
