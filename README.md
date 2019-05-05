# Assignmnet

New information RSS feeds, rome library

Estimated elapsed time 7-8 hours

Workflow

This app has a schaduled function that reads rss feeds from  http://feeds.nos.nl/nosjournaal?format=xml ( it is hardcoded ). Changed to a dinamic Csv file system.
There is a get last records and it is based on the last rss feed query who has an enbedeed cache.
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


