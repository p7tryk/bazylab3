#!/bin/bash
mysql_user='root' #zmien na uzytkownika
mysql_password='pwsz' #zmien na haslo tego uzytkownika


RED='\033[0;31m'
NC='\033[0m' # No Color 

function clean()
{
    sudo mysql -u $mysql_user -p$mysql_password < createtables.sql
    echo -e "${RED}baza stworzona${NC}\n"
    
    sudo mysql -u $mysql_user -p$mysql_password < filltables.sql
    echo -e "${RED}baza wypelniona${NC}\n"

    sudo mysql -u $mysql_user -p$mysql_password < createusers.sql
    echo -e "${RED}uprawnienia ustawione${NC}\n"

    sleep 1
}
clean
echo "db imported"


