drop table airHumidity;
drop table co2_level;
drop table fertilizeData;
drop table lightData;
drop table temp;
drop table waterLevel;

CREATE TABLE airHumidity(
	count integer,
	airHumidity double precision
);
CREATE TABLE co2_level(
	count integer,
	co2_level double precision
);
CREATE TABLE fertilizeData(
	count integer,
	fertilizeCount integer,
	fertalizeDate varchar(30)
);
CREATE	TABLE lightData(
	count integer,
	red_level integer,
	blue_level integer
);
CREATE TABLE temp(
	count integer,
	temp double precision
);
CREATE TABLE waterLevel(
	count integer,
	waterLevel integer
);