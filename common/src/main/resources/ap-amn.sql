
CREATE TABLE if not exists tsp_mutex
  (
    system_code VARCHAR(20) NOT NULL,
    sub_system_code  VARCHAR(20) NOT NULL,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY (system_code,sub_system_code,name)
);

CREATE TABLE if not exists tsp_named_service_master
  (
    system_code VARCHAR(20) NOT NULL,
    sub_system_code  VARCHAR(20) NOT NULL,
    service_name VARCHAR(20) NOT NULL,
    service_id VARCHAR(20) NOT NULL,
    PRIMARY KEY (system_code,sub_system_code,service_name)
);