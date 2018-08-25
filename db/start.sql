create table train
(
	id int auto_increment
		primary key,
	name varchar(100) null,
	status_id int null,
	constraint train_id_uindex
		unique (id),
	constraint train_status_id_fk
		foreign key (status_id) references status (id)
)
engine=InnoDB
;

create index train_status_id_fk
	on train (status_id)
;

create table user
(
	id int auto_increment
		primary key,
	firstName varchar(100) not null,
	lastName varchar(100) not null,
	login varchar(100) not null,
	password varchar(100) not null,
	birthDay varchar(10) null,
	sex varchar(10) null,
	constraint user_id_uindex
		unique (id)
)
engine=InnoDB
;

create table user_role
(
	user_id int null,
	role_id int null,
	constraint user_role_user_id_fk
		foreign key (user_id) references user (id),
	constraint user_role_role_id_fk
		foreign key (role_id) references role (id)
)
engine=InnoDB
;

create index user_role_role_id_fk
	on user_role (role_id)
;

create index user_role_user_id_fk
	on user_role (user_id)
;

create table station
(
	id int auto_increment
		primary key,
	name varchar(100) null,
	latitude double null,
	longitude double null,
	status_id int null,
	constraint station_id_uindex
		unique (id),
	constraint station_status_id_fk
		foreign key (status_id) references status (id)
)
engine=InnoDB
;

create index station_status_id_fk
	on station (status_id)
;

create table status
(
	id int auto_increment
		primary key,
	statusName varchar(100) null,
	constraint status_id_uindex
		unique (id)
)
engine=InnoDB
;


create table audit
(
	id int auto_increment
		primary key,
	user_id int null,
	operations varchar(100) null,
	date timestamp null,
	oldValue varchar(200) null,
	newValue varchar(200) null,
	constraint audit_id_uindex
		unique (id),
	constraint audit_user_id_fk
		foreign key (user_id) references user (id)
)
engine=InnoDB
;

create index audit_user_id_fk
	on audit (user_id)
;

create table role
(
	id int auto_increment
		primary key,
	type varchar(50) null,
	constraint role_id_uindex
		unique (id)
)
engine=InnoDB
;

create table schedule
(
	id int auto_increment
		primary key,
	date_departure timestamp null,
	date_arrival timestamp null,
	stationDeparture_id int null,
	stationArrival_id int null,
	train_id int null,
	constraint schedule_id_uindex
		unique (id),
	constraint schedule_station_id_fk
		foreign key (stationDeparture_id) references station (id),
	constraint schedule_station_id_fk_2
		foreign key (stationArrival_id) references station (id),
	constraint schedule_train_id_fk
		foreign key (train_id) references train (id)
)
engine=InnoDB
;

create index schedule_station_id_fk
	on schedule (stationDeparture_id)
;

create index schedule_station_id_fk_2
	on schedule (stationArrival_id)
;

create index schedule_train_id_fk
	on schedule (train_id)
;

create table seat
(
	id int auto_increment
		primary key,
	carriage int null,
	seat int null,
	train_id int null,
	constraint seat_id_uindex
		unique (id),
	constraint seat_train_id_fk
		foreign key (train_id) references train (id)
)
engine=InnoDB
;

create index seat_train_id_fk
	on seat (train_id)
;

create table ticket
(
	id int auto_increment
		primary key,
	schedule_id int not null,
	user_id int not null,
	seat_id int not null,
	price int null,
	constraint ticket_id_uindex
		unique (id),
	constraint ticket_schedule_id_fk
		foreign key (schedule_id) references schedule (id),
	constraint ticket_user_id_fk
		foreign key (user_id) references user (id),
	constraint ticket_seat_id_fk
		foreign key (seat_id) references seat (id)
)
engine=InnoDB
;

create index ticket_schedule_id_fk
	on ticket (schedule_id)
;

create index ticket_seat_id_fk
	on ticket (seat_id)
;

create index ticket_user_id_fk
	on ticket (user_id)
;



