DROP DATABASE IF EXISTS `airport`;
CREATE DATABASE airport;
USE airport;

-- Table structure for table `airlines`
DROP TABLE IF EXISTS `airlines`;
CREATE TABLE `airlines` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Table structure for table `countries`
DROP TABLE IF EXISTS `countries`;
CREATE TABLE `countries` (
  `id` int NOT NULL ,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for table `flightfares`
DROP TABLE IF EXISTS `flightfares`;
CREATE TABLE `flightfares` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `details` text,
  `value` double(7,3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Table structure for table `trips`
DROP TABLE IF EXISTS `trips`;
CREATE TABLE `trips` (
  `id` int NOT NULL AUTO_INCREMENT,
  `trip_date` date DEFAULT NULL,
  `price_tripe` double DEFAULT NULL,
  `departure_airport` VARCHAR(255) NOT NULL,
  `arrival_airport` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Table structure for table `tripulationroles`
DROP TABLE IF EXISTS `tripulationroles`;
CREATE TABLE `tripulationroles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for table `statuses`
DROP TABLE IF EXISTS `statuses`;
CREATE TABLE `statuses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Table structure for table `documenttypes`
DROP TABLE IF EXISTS `documenttypes`;
CREATE TABLE `documenttypes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for table `manufacturers`
DROP TABLE IF EXISTS `manufacturers`;
CREATE TABLE `manufacturers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Table structure for table `cities`
DROP TABLE IF EXISTS `cities`;
CREATE TABLE `cities` (
  `id` int NOT NULL ,
  `name` varchar(255) DEFAULT NULL,
  `idcountry` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Fk_city` (`idcountry`),
  CONSTRAINT `Fk_city` FOREIGN KEY (`idcountry`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for table `airports`
DROP TABLE IF EXISTS `airports`;
CREATE TABLE `airports` (
  `id` int NOT NULL ,
  `name` varchar(255)  DEFAULT NULL,
  `idcity` varchar(255) DEFAULT NULL,  
  PRIMARY KEY (`id`),
  KEY `fk_id_city_airport` (`idcity`),
  CONSTRAINT `fk_id_city_airport` FOREIGN KEY (`idcity`) REFERENCES `cities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Table structure for table `customers`
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `id` int NOT NULL ,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `iddocument` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Fk_customers` (`iddocument`),
  CONSTRAINT `Fk_customers` FOREIGN KEY (`iddocument`) REFERENCES `documenttypes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




-- Table structure for table `employees`
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `id` int NOT NULL ,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `idrol` int DEFAULT NULL,
  `ingressdate` date DEFAULT NULL,
  `idairline` int DEFAULT NULL,
  `idairport` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_airline_employee` FOREIGN KEY (`idairline`) REFERENCES `airlines` (`id`),
  CONSTRAINT `fk_airport_employee` FOREIGN KEY (`idairport`) REFERENCES `airports` (`id`),
  CONSTRAINT `fk_rol_employee` FOREIGN KEY (`idrol`) REFERENCES `tripulationroles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Table structure for table `models`
DROP TABLE IF EXISTS `models`;
CREATE TABLE `models` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `manufacturerid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Fk_manufacturerid` (`manufacturerid`),
  CONSTRAINT `Fk_manufacturerid` FOREIGN KEY (`manufacturerid`) REFERENCES `manufacturers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for table `planes`
DROP TABLE IF EXISTS `planes`;
CREATE TABLE `planes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `plates` varchar(255) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `fabrication_date` date DEFAULT NULL,
  `id_status` int DEFAULT NULL,
  `id_model` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `planes` (`planes`),
  KEY `fk_id_status_plane` (`id_status`),
  KEY `fk_id_model_plane` (`id_model`),
  CONSTRAINT `fk_id_model_plane` FOREIGN KEY (`id_model`) REFERENCES `models` (`id`),
  CONSTRAINT `fk_id_status_plane` FOREIGN KEY (`id_status`) REFERENCES `statuses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Table structure for table `flight_connections`
DROP TABLE IF EXISTS `flight_connections`;
CREATE TABLE `flight_connections` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `connection_number` VARCHAR(255) DEFAULT NULL,
  `id_trip` INT DEFAULT NULL,
  `id_plane` INT DEFAULT NULL,
  `type_fright` VARCHAR(255) DEFAULT NULL,
  `Last_Scale` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_trip_connection` (`id_trip`),
  KEY `fk_plane_connection` (`id_plane`),

  CONSTRAINT `fk_plane_connection` FOREIGN KEY (`id_plane`) REFERENCES `planes` (`id`),
  CONSTRAINT `fk_trip_connection` FOREIGN KEY (`id_trip`) REFERENCES `trips` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




-- Table structure for table `gates`
DROP TABLE IF EXISTS `gates`;
CREATE TABLE `gates` (
  `id` int NOT NULL AUTO_INCREMENT,
  `gatenumber` varchar(255) DEFAULT NULL,
  `idairport` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_airport_gate` (`idairport`),
  CONSTRAINT `fk_airport_gate` FOREIGN KEY (`idairport`) REFERENCES `airports` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Table structure for table `revisions`
DROP TABLE IF EXISTS `revisions`;
CREATE TABLE `revisions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `revision_date` date DEFAULT NULL,
  `id_plane` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_plane_revision` (`id_plane`),
  CONSTRAINT `fk_id_plane_revision` FOREIGN KEY (`id_plane`) REFERENCES `planes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Table structure for table `revemployee`
DROP TABLE IF EXISTS `revemployee`;
CREATE TABLE `revemployee` (
  `idemployee` varchar(255) NOT NULL,
  `idrevision` int NOT NULL,
  PRIMARY KEY (`idemployee`,`idrevision`),
  KEY `fk_id_revision_revemployee` (`idrevision`),
  CONSTRAINT `fk_id_employee_revemployee` FOREIGN KEY (`idemployee`) REFERENCES `employees` (`id`),
  CONSTRAINT `fk_id_revision_revemployee` FOREIGN KEY (`idrevision`) REFERENCES `revisions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- Table structure for table `revision_details`
DROP TABLE IF EXISTS `revision_details`;
CREATE TABLE `revision_details` (
  `id` int NOT NULL ,
  `description` text,
  `id_employee` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_revision_detail` (`id_employee`),
  CONSTRAINT `fk_employee_revision_detail` FOREIGN KEY (`id_employee`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for table `tripbooking`
DROP TABLE IF EXISTS `tripbooking`;
CREATE TABLE `tripbooking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `idtrips` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_trips` (`idtrips`),
  CONSTRAINT `fk_trips` FOREIGN KEY (`idtrips`) REFERENCES `trips` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- Table structure for table `tripbookingdetails`
DROP TABLE IF EXISTS `tripbookingdetails`;
CREATE TABLE `tripbookingdetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idtripbooking` int DEFAULT NULL,
  `idcustomers` varchar(255) DEFAULT NULL,
  `idfares` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tripbooking` (`idtripbooking`),
  KEY `fk_conection_trip_booking_details` (`idcustomers`),
  KEY `fk_flightfares_trip_booking_details` (`idfares`),
  CONSTRAINT `fk_conection_trip_booking_details` FOREIGN KEY (`idcustomers`) REFERENCES `customers` (`id`),
  CONSTRAINT `fk_flightfares_trip_booking_details` FOREIGN KEY (`idfares`) REFERENCES `flightfares` (`id`),
  CONSTRAINT `fk_tripbooking` FOREIGN KEY (`idtripbooking`) REFERENCES `tripbooking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Table structure for table `tripcrews`
DROP TABLE IF EXISTS `tripcrews`;
CREATE TABLE `tripcrews` (
  `idemployees` varchar(255) NOT NULL,
  `idconection` int NOT NULL,
  PRIMARY KEY (`idconection`,`idemployees`),
  KEY `fk_employees` (`idemployees`),
  CONSTRAINT `fk_conection` FOREIGN KEY (`idconection`) REFERENCES `flight_connections` (`id`),
  CONSTRAINT `fk_employees` FOREIGN KEY (`idemployees`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





INSERT INTO `airlines` VALUES (1,'American Airlines'),(2,'Delta Air Lines'),(3,'United Airlines'),(4,'Southwest Airlines'),(255,'JetBlue Airways'),(6,'Alaska Airlines'),(7,'Spirit Airlines'),(8,'Frontier Airlines'),(9,'Hawaiian Airlines'),(10,'Allegiant Air');
INSERT INTO `countries` VALUES ('AR','Argentina'),('AU','Australia'),('BR','Brazil'),('CA','Canada'),('CN','China'),('DE','Germany'),('EG','Egypt'),('ES','Spain'),('FR','France'),('IN','India'),('IT','Italy'),('JP','Japan'),('KR','South Korea'),('MX','Mexico'),('NG','Nigeria'),('RU','Russia'),('TR','Turkey'),('UK','United Kingdom'),('US','United States'),('ZA','South Africa');
INSERT INTO `cities` VALUES ('ABV','Abuja','NG'),('ADE','Adelaide','AU'),('ADN','Adana','TR'),('ALX','Alexandria','EG'),('ANK','Ankara','TR'),('BCN','Barcelona','ES'),('BEI','Beijing','CN'),('BER','Berlin','DE'),('BIL','Bilbao','ES'),('BIR','Birmingham','UK'),('BLR','Bangalore','IN'),('BNE','Brisbane','AU'),('BSB','Brasilia','BR'),('BUE','Buenos Aires','AR'),('BUR','Bursa','TR'),('BUS','Busan','KR'),('CAI','Cairo','EG'),('CAL','Calgary','CA'),('CD','Chengdu','CN'),('CGN','Cologne','DE'),('CHE','Chennai','IN'),('CHI','Chicago','US'),('COR','Córdoba','AR'),('CPT','Cape Town','ZA'),('DAE','Daegu','KR'),('DEL','Delhi','IN'),('DUR','Durban','ZA'),('EDI','Edinburgh','UK'),('EKB','Yekaterinburg','RU'),('FOR','Fortaleza','BR'),('FRA','Frankfurt','DE'),('FUK','Fukuoka','JP'),('GDL','Guadalajara','MX'),('GIZ','Giza','EG'),('GLA','Glasgow','UK'),('GWA','Gwangju','KR'),('GZ','Guangzhou','CN'),('HAM','Hamburg','DE'),('HOU','Houston','US'),('HUR','Hurghada','EG'),('HYD','Hyderabad','IN'),('IBA','Ibadan','NG'),('INC','Incheon','KR'),('IST','Istanbul','TR'),('IZM','Izmir','TR'),('JNB','Johannesburg','ZA'),('KAN','Kano','NG'),('LAX','Los Angeles','US'),('LON','London','UK'),('LOS','Lagos','NG'),('LP','La Plata','AR'),('LYO','Lyon','FR'),('MAD','Madrid','ES'),('MAN','Manchester','UK'),('MAR','Marseille','FR'),('MDZ','Mendoza','AR'),('MEL','Melbourne','AU'),('MEX','Mexico City','MX'),('MIL','Milan','IT'),('MON','Montreal','CA'),('MOS','Moscow','RU'),('MTY','Monterrey','MX'),('MUC','Munich','DE'),('MUM','Mumbai','IN'),('NAG','Nagoya','JP'),('NAP','Naples','IT'),('NIC','Nice','FR'),('NOV','Novosibirsk','RU'),('NYC','New York','US'),('OSA','Osaka','JP'),('OTT','Ottawa','CA'),('PAL','Palermo','IT'),('PAR','Paris','FR'),('PE','Port Elizabeth','ZA'),('PER','Perth','AU'),('PH','Port Harcourt','NG'),('PHX','Phoenix','US'),('PRY','Pretoria','ZA'),('PUE','Puebla','MX'),('RIO','Rio de Janeiro','BR'),('ROM','Rome','IT'),('ROS','Rosario','AR'),('SAL','Salvador','BR'),('SAM','Samara','RU'),('SAO','Sao Paulo','BR'),('SAP','Sapporo','JP'),('SEO','Seoul','KR'),('SEV','Seville','ES'),('SHA','Shanghai','CN'),('SHR','Sharm El Sheikh','EG'),('SPB','Saint Petersburg','RU'),('SYD','Sydney','AU'),('SZX','Shenzhen','CN'),('TJU','Tijuana','MX'),('TOK','Tokyo','JP'),('TOR','Toronto','CA'),('TORI','Turin','IT'),('TOU','Toulouse','FR'),('VAL','Valencia','ES'),('VAN','Vancouver','CA');
INSERT INTO `airports` VALUES ('CYOW','Ottawa Macdonald-Cartier International Airport','OTT'),('CYUL','Montréal-Pierre Elliott Trudeau International Airport','MON'),('CYVR','Vancouver International Airport','VAN'),('CYYC','Calgary International Airport','CAL'),('CYYZ','Toronto Pearson International Airport','TOR'),('DNAA','Nnamdi Azikiwe International Airport','ABV'),('DNIB','Ibadan Airport','IBA'),('DNKN','Mallam Aminu Kano International Airport','KAN'),('DNMM','Murtala Muhammed International Airport','LOS'),('DNPO','Port Harcourt International Airport','PH'),('EDDF','Frankfurt Airport','FRA'),('EDDH','Hamburg Airport','HAM'),('EDDK','Cologne Bonn Airport','CGN'),('EDDM','Munich Airport','MUC'),('EDDT','Berlin Tegel Airport','BER'),('EGBB','Birmingham Airport','BIR'),('EGCC','Manchester Airport','MAN'),('EGLL','Heathrow Airport','LON'),('EGPF','Glasgow Airport','GLA'),('EGPH','Edinburgh Airport','EDI'),('FACT','Cape Town International Airport','CPT'),('FALE','King Shaka International Airport','DUR'),('FAOR','O.R. Tambo International Airport','JNB'),('FAPE','Port Elizabeth Airport','PE'),('FAWB','Wonderboom Airport','PRY'),('HEAX','Alexandria International Airport','ALX'),('HECA','Cairo International Airport','CAI'),('HEGN','Hurghada International Airport','HUR'),('HESH','Sharm El Sheikh International Airport','SHR'),('HESX','Sphinx International Airport','GIZ'),('KIAH','George Bush Intercontinental Airport','HOU'),('KJFK','John F. Kennedy International Airport','NYC'),('KLAX','Los Angeles International Airport','LAX'),('KORD','Hare International Airport','CHI'),('KPHX','Phoenix Sky Harbor International Airport','PHX'),('LEBB','Bilbao Airport','BIL'),('LEBL','Barcelona-El Prat Airport','BCN'),('LEMD','Adolfo Suárez Madrid_Barajas Airport','MAD'),('LEVC','Valencia Airport','VAL'),('LEZL','Seville Airport','SEV'),('LFBO','Toulouse-Blagnac Airport','TOU'),('LFLL','Lyon-Saint Exupéry Airport','LYO'),('LFML','Marseille Provence Airport','MAR'),('LFMN','Nice Côte d\'Azur Airport','NIC'),('LFPG','Charles de Gaulle Airport','PAR'),('LICJ','Palermo Airport','PAL'),('LIMC','Milan Malpensa Airport','MIL'),('LIMF','Turin Airport','TORI'),('LIRF','Leonardo da Vinci–Fiumicino Airport','ROM'),('LIRN','Naples International Airport','NAP'),('LTAC','Esenboğa International Airport','ANK'),('LTAF','Adana Şakirpaşa Airport','ADN'),('LTBJ','Adnan Menderes Airport','IZM'),('LTBR','Yenisehir Airport','BUR'),('LTFM','Istanbul Airport','IST'),('MMGL','Guadalajara International Airport','GDL'),('MMMX','Mexico City International Airport','MEX'),('MMMY','Monterrey International Airport','MTY'),('MMPB','Hermanos Serdán International Airport','PUE'),('MMTJ','Tijuana International Airport','TJU'),('RJBB','Kansai International Airport','OSA'),('RJCC','New Chitose Airport','SAP'),('RJFF','Fukuoka Airport','FUK'),('RJGG','Chubu Centrair International Airport','NAG'),('RJTT','Tokyo Haneda Airport','TOK'),('RKJJ','Gwangju Airport','GWA'),('RKPK','Gimhae International Airport','BUS'),('RKSI','Incheon International Airport','SEO'),('RKTN','Daegu International Airport','DAE'),('SAAR','Rosario – Islas Malvinas International Airport','ROS'),('SACO','Ingeniero Ambrosio L.V. Taravella International Airport','COR'),('SADL','La Plata Airport','LP'),('SAEZ','Ministro Pistarini International Airport','BUE'),('SAME','Governor Francisco Gabrielli International Airport','MDZ'),('SBBR','Brasília International Airport','BSB'),('SBFZ','Pinto Martins – Fortaleza International Airport','FOR'),('SBGL','Rio de Janeiro-Galeão International Airport','RIO'),('SBGR','São Paulo-Guarulhos International Airport','SAO'),('SBSV','Deputado Luís Eduardo Magalhães International Airport','SAL'),('ULLI','Pulkovo Airport','SPB'),('UNNT','Novosibirsk Tolmachevo Airport','NOV'),('USSS','Koltsovo Airport','EKB'),('UUEE','Sheremetyevo International Airport','MOS'),('UWWW','Kurumoch International Airport','SAM'),('VABB','Chhatrapati Shivaji Maharaj International Airport','MUM'),('VIDP','Indira Gandhi International Airport','DEL'),('VOBL','Kempegowda International Airport','BLR'),('VOHS','Rajiv Gandhi International Airport','HYD'),('VOMM','Chennai International Airport','CHE'),('YBBN','Brisbane Airport','BNE'),('YMML','Melbourne Airport','MEL'),('YPAD','Adelaide Airport','ADE'),('YPPH','Perth Airport','PER'),('YSSY','Sydney Kingsford Smith Airport','SYD'),('ZBAA','Beijing Capital International Airport','BEI'),('ZGGG','Guangzhou Baiyun International Airport','GZ'),('ZGSZ','Shenzhen Bao\'an International Airport','SZX'),('ZSPD','Shanghai Pudong International Airport','SHA'),('ZUUU','Chengdu Shuangliu International Airport','CD');

INSERT INTO airport.tripulationroles
(id, name)
VALUES(1, 'administrador');

INSERT INTO airport.documenttypes
(id, name)
VALUES(1, 'cedula');

INSERT INTO airport.customers
(id, name, email, password, age, iddocument)
VALUES('WQESD', 'CLIENTE', 'cliente', 'cliente', 2, 1);


INSERT INTO airport.employees
(id, name, email, password, idrol, ingressdate, idairline, idairport)
VALUES('ASASF', 'TEST', 'admin', 'admin', 1, '2022-02-02', 1, 'CYOW');

