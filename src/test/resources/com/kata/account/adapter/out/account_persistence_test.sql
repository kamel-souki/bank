INSERT INTO custumer (custumer_id) VALUES (2);


INSERT INTO account (account_id, custumer_id, balance) VALUES (3, 2, 500);

INSERT INTO account (account_id, custumer_id, balance) VALUES (4, 2, 2000);



INSERT INTO activity (activity_id, account_id, transaction, amount, date_of_creation) VALUES (1, 3, 'DEPOSIT', 1000, '2017-01-13T17:09:42.411');

INSERT INTO activity (activity_id, account_id, transaction, amount, date_of_creation) VALUES (2, 3, 'WITHDRAWAL', 200, '2017-01-13T17:09:42.411');

INSERT INTO activity (activity_id, account_id, transaction, amount, date_of_creation) VALUES (3, 4, 'DEPOSIT', 500, '2017-01-13T17:09:42.411');

INSERT INTO activity (activity_id, account_id, transaction, amount, date_of_creation) VALUES (4, 4, 'WITHDRAWAL', 100, '2017-01-13T17:09:42.411');
