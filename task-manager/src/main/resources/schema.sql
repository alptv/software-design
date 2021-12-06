create table if not exists Users (
    id bigint not null auto_increment,
    primary key(id),
    login varchar(40) not null,
    unique (login),
    password varchar(40) not null
);

create table  if not exists TasksLists (
    id bigint not null auto_increment,
    primary key(id),
    name varchar(40) not null,
    userId bigint not null,
    foreign key (userId) references Users (id) on delete cascade
);

create table if not exists Tasks (
    id bigint not null auto_increment,
    primary key (id),
    name varchar(40) not null,
    description varchar(105) not null,
    taskListId bigint not null,
    foreign key (taskListId) references TasksLists(id) on delete cascade,
    done boolean not null
);
