# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table event (
  id                        bigint auto_increment not null,
  user_id                   bigint not null,
  event_name                varchar(255) not null,
  description               varchar(255),
  place                     varchar(255),
  done                      tinyint(1) default 0 not null,
  date                      varchar(255) not null,
  time                      varchar(255) not null,
  constraint pk_event primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  email                     varchar(255) not null,
  password                  varchar(255) not null,
  first_name                varchar(255) not null,
  last_name                 varchar(255) not null,
  birthday                  varchar(255) not null,
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table event;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

