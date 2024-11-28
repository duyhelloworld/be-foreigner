-- 127.0.0.1:3306
-- Database: be-foreigner

-- Create user 'be-foreigner' with all privileges;
CREATE USER 'be-foreigner' @'%' IDENTIFIED BY 'password';

-- Grant select privilege to all databases;
GRANT SELECT ON *.* TO 'be-foreigner' @'%' WITH GRANT OPTION;
-- Grant all privileges to all databases;
GRANT ALL PRIVILEGES ON *.* TO 'be-foreigner' @'%' WITH GRANT OPTION;

-- DROP DATABASE be-foreigner;
