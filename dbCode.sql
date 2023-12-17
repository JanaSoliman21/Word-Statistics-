create database osproject;

use osproject;

CREATE TABLE osproject.words (
  directory_name VARCHAR(255),
  file_name VARCHAR(255),
  file_path VARCHAR(255),
  words INT NULL,
  _is INT NULL,
  are INT NULL,
  you INT NULL,
  Longest VARCHAR(100) NULL,
  Shortest VARCHAR(100) NULL
);

select * from osproject.words;
