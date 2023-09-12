--
-- Table structure for table `account`
--

CREATE TABLE account (
  account_id bigint NOT NULL auto_increment,
  custumer_id bigint DEFAULT '0' ,
  balance int DEFAULT '0',
  PRIMARY KEY (account_id)
);

--
-- Table structure for table `custumer`
--

CREATE TABLE custumer (
  custumer_id bigint NOT NULL auto_increment,
  PRIMARY KEY (custumer_id)
);


--
-- Table structure for table `activity`
--

CREATE TABLE activity (
  activity_id bigint NOT NULL AUTO_INCREMENT,
  account_id bigint DEFAULT '0',
  transaction varchar(45) DEFAULT NULL,
  amount int DEFAULT NULL,
  date_of_creation datetime DEFAULT NULL,
  PRIMARY KEY (activity_id)
);
