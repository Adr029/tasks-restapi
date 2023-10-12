CREATE TABLE IF NOT EXISTS tasks(
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_name varchar(50) NOT NULL,
    date_target varchar(20) NOT NULL,
    notes TINYTEXT NOT NULL,
    status varchar(15) NOT NULL
);

