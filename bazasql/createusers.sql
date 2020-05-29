use lab3;
drop user if exists 'admin'@'%';

CREATE USER 'admin'@'%' IDENTIFIED BY 'pwsz';

GRANT ALL PRIVILEGES ON lab3.* TO 'admin'@'%';
