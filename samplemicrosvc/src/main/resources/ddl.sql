create table audit_entry (id bigint not null, action varchar(255), createdat bigint, entityname varchar(255), userid varchar(255), primary key (id)) engine=InnoDB;
