CREATE DATABASE 'demo';
USE demo;

create table users (
	id  int(3) NOT NULL AUTO_INCREMENT,
	name varchar(120) NOT NULL,
	email varchar(220) NOT NULL,
	country varchar(120),
	PRIMARY KEY (id)
);

create table projects (
	id  int(3) NOT NULL AUTO_INCREMENT,
	name varchar(120) NOT NULL,
	PRIMARY KEY (id)
);

create table tasks (
	id  int(3) NOT NULL AUTO_INCREMENT,
    projectid int(3),
	name varchar(120) NOT NULL,
	title varchar(120) NOT NULL,
	classtask ENUM('Task', 'Bug') NOT NULL,
	status ENUM('New', 'InProgress', 'OnReview', 'Closed') NOT NULL,
    priority ENUM('Low', 'Medium', 'High') NOT NULL,
    userid int(3),
    description varchar(1000),
	PRIMARY KEY (id),
	FOREIGN KEY (projectid) REFERENCES projects(id),
	FOREIGN KEY (userid) REFERENCES users(id)
    );

