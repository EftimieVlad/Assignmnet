# Assignmnet

New information RSS feeds, rome library

Estimated elapsed time 7-8 hours

Workflow

This app has a schaduled function that reads rss feeds from  http://feeds.nos.nl/nosjournaal?format=xml ( it is hardcoded ).
Everytime a new feed (title and date are unique) is found is added to the database.
Imformation is provided at the endpoint /feed/all (get request) and it return all the information in raw format ( images are bytes )


Improvments

Give more options to the client ( select just some feeds by hour/title)
Unit/acceptance tests for images, rss link feed and format
Better information caching ( in memory at this time )


Used IDE:
Intellij IDEA

Sql

create schema assignment
create table assignment.rss
(
  id             int          not null
    primary key,
  created_date   datetime     null,
  description    longtext     null,
  image          longblob     null,
  published_date datetime     null,
  title          varchar(255) null
);


