use flight_manager;

insert into `company`(`name`,`headquarterCountry`,`headquarterCity`,`uuid`) values('IBM','Olanda','Amsterdam','95685245');
insert into `company`(`name`,`headquarterCountry`,`headquarterCity`,`uuid`) values('Alaska Airlines','Germania','Munchen','12589699');
insert into `company`(`name`,`headquarterCountry`,`headquarterCity`,`uuid`) values('Bechtel','Romania','Bucuresti','59685245');

insert into `user`(`email`,`firstName`,`lastName`,`password`,`phoneNumber`,`type`,`userName`,`uuid`,`COMPANY_id`) 
values('anne@doe.com','Anne','Doe','anne','0725852954','PILOT','anne','10251201',1);
insert into `user`(`email`,`firstName`,`lastName`,`password`,`phoneNumber`,`type`,`userName`,`uuid`,`COMPANY_id`) 
values('john@smith.com','John','Smith','john','0732695145','ADMINISTRATOR','john','22825812',2);
insert into `user`(`email`,`firstName`,`lastName`,`password`,`phoneNumber`,`type`,`userName`,`uuid`,`COMPANY_id`) 
values('paul@keller.com','Paul','Keller','paul','0724759632','FLIGHT_MANAGER','paul','30256981',2);
insert into `user`(`email`,`firstName`,`lastName`,`password`,`phoneNumber`,`type`,`userName`,`uuid`,`COMPANY_id`) 
values('kate@grey.com','Kate','Grey','kate','0729558663','COMPANY_MANAGER','kate','96584236',3);
insert into `user`(`email`,`firstName`,`lastName`,`password`,`phoneNumber`,`type`,`userName`,`uuid`,`COMPANY_id`) 
values('marry@parker.com','Marry','Parker','marry','0725963214','STEWARD','marry','10010010',3);


insert into `plane`(`numberOfPlaces`,`type`,`uuid`,`COMPANY_id`) values(100,'AIRBUS','12641645',1);
insert into `plane`(`numberOfPlaces`,`type`,`uuid`,`COMPANY_id`) values(300,'BOEING','84613846',2);
insert into `plane`(`numberOfPlaces`,`type`,`uuid`,`COMPANY_id`) values(200,'AIRBUS','87542103',2);
insert into `plane`(`numberOfPlaces`,`type`,`uuid`,`COMPANY_id`) values(350,'BEECHCRAFT','10203020',3);
insert into `plane`(`numberOfPlaces`,`type`,`uuid`,`COMPANY_id`) values(250,'BOEING','30020102',3);
insert into `plane`(`numberOfPlaces`,`type`,`uuid`,`COMPANY_id`) values(200,'AIRBUS','15963578',3);

insert into `itinerary`(`startTime`,`endTime`,`periodicity`,`uuid`,`nextDay`) values('10:00:00','11:00:00','DAILY','52525252',0);
insert into `itinerary`(`startTime`,`endTime`,`periodicity`,`uuid`,`nextDay`) values('11:00:00','19:00:00','ONCE','11447788',0);
insert into `itinerary`(`startTime`,`endTime`,`periodicity`,`uuid`,`nextDay`) values('12:00:00','02:00:00','WEEKLY','99668855',2);

insert into `airport`(`code`,`adress`,`city`,`country`,`name`,`uuid`) values('A11','Houghton Street','London','Britain','Airport1','55663320');
insert into `airport`(`code`,`adress`,`city`,`country`,`name`,`uuid`) values('A12','rue Arsene Houssaye','Paris','France','Airport2','99668855');
insert into `airport`(`code`,`adress`,`city`,`country`,`name`,`uuid`) values('A13','Lavender Gardens','London','Britain','Airport3','15475248');
insert into `airport`(`code`,`adress`,`city`,`country`,`name`,`uuid`) values('A14','52nd Street','New-York','USA','Airport4','36963696');



insert into `company_airport`(`companyId`,`airportId`) values(1,1);
insert into `company_airport`(`companyId`,`airportId`) values(1,2);
insert into `company_airport`(`companyId`,`airportId`) values(2,2);
insert into `company_airport`(`companyId`,`airportId`) values(2,3);
insert into `company_airport`(`companyId`,`airportId`) values(3,4);
insert into `company_airport`(`companyId`,`airportId`) values(3,3);

insert into `flightTemplate`(`uuid`,`arrivalId`,`departureId`,`itineraryId`) values('14789632',1,2,1);
insert into `flightTemplate`(`uuid`,`arrivalId`,`departureId`,`itineraryId`) values('36985214',3,4,2);
insert into `flightTemplate`(`uuid`,`arrivalId`,`departureId`,`itineraryId`) values('58989898',1,3,3);
insert into `flightTemplate`(`uuid`,`arrivalId`,`departureId`,`itineraryId`) values('88774477',2,4,1);

insert into `flight`(`date`,`uuid`,`planeId`,`templateId`) values('2016-09-06 00:00:00','33669988',1,1);
insert into `flight`(`date`,`uuid`,`planeId`,`templateId`) values('2016-09-05 00:00:00','14785296',2,2);
insert into `flight`(`date`,`uuid`,`planeId`,`templateId`) values('2016-10-03 00:00:00','14785963',3,3);
insert into `flight`(`date`,`uuid`,`planeId`,`templateId`) values('2016-10-11 00:00:00','12121252',4,4);
insert into `flight`(`date`,`uuid`,`planeId`,`templateId`) values('2016-11-07 00:00:00','36369656',5,1);
insert into `flight`(`date`,`uuid`,`planeId`,`templateId`) values('2016-11-10 00:00:00','14785965',6,2);

insert into `flight_user`(`flightId`,`userId`) values(1,2);
insert into `flight_user`(`flightId`,`userId`) values(1,5);
insert into `flight_user`(`flightId`,`userId`) values(2,1);
insert into `flight_user`(`flightId`,`userId`) values(2,5);
insert into `flight_user`(`flightId`,`userId`) values(3,1);
insert into `flight_user`(`flightId`,`userId`) values(4,1);
insert into `flight_user`(`flightId`,`userId`) values(4,5);

select * from user;
select * from company;