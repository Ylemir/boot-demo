-- 用户表
CREATE TABLE IF NOT EXISTS t_user (
    id          VARCHAR(50)  PRIMARY KEY,
    name        VARCHAR(30)  NOT NULL,
    gender      VARCHAR(10)  DEFAULT 'UNKNOWN',
    birthday    DATE,
    roles       TEXT,
    online      BOOLEAN      DEFAULT FALSE,
    create_time TIMESTAMP    NOT NULL,
    creator     VARCHAR(30)  NOT NULL,
    update_time TIMESTAMP    NOT NULL,
    updater     VARCHAR(30)  NOT NULL,
    CONSTRAINT uk_user_name UNIQUE (name)
);

-- 网站表
CREATE TABLE IF NOT EXISTS t_website (
    id          VARCHAR(50)  NOT NULL,
    version     VARCHAR(10)  NOT NULL,
    name        VARCHAR(50),
    url         VARCHAR(500) NOT NULL,
    description VARCHAR(255),
    is_deleted  BOOLEAN      DEFAULT FALSE,
    create_time TIMESTAMP    NOT NULL,
    creator     VARCHAR(30)  NOT NULL,
    update_time TIMESTAMP    NOT NULL,
    updater     VARCHAR(30)  NOT NULL,
    PRIMARY KEY (id, version)
);
