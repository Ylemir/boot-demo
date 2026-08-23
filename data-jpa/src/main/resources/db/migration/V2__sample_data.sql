-- 用户样例数据
INSERT INTO t_user (id, name, gender, birthday, roles, online, create_time, creator, update_time, updater) VALUES
('u-00001', '张三', 'MALE', '1990-01-15', '["ADMIN","USER"]', true, '2026-01-01 10:00:00', 'system', '2026-01-01 10:00:00', 'system'),
('u-00002', '李四', 'FEMALE', '1995-05-20', '["USER"]', false, '2026-01-02 10:00:00', 'system', '2026-01-02 10:00:00', 'system'),
('u-00003', '王五', 'MALE', '1988-12-08', '["ADMIN"]', true, '2026-01-03 10:00:00', 'system', '2026-01-03 10:00:00', 'system'),
('u-00004', '赵六', 'FEMALE', '2000-07-30', '["USER","MANAGER"]', false, '2026-01-04 10:00:00', 'system', '2026-01-04 10:00:00', 'system'),
('u-00005', '孙七', 'MALE', '1992-03-25', '["USER"]', true, '2026-01-05 10:00:00', 'system', '2026-01-05 10:00:00', 'system'),
('u-00006', '周八', 'UNKNOWN', '1998-11-11', '["USER"]', false, '2026-01-06 10:00:00', 'system', '2026-01-06 10:00:00', 'system');

-- 网站样例数据
INSERT INTO t_website (id, version, name, url, description, is_deleted, create_time, creator, update_time, updater) VALUES
('w-00001', 'v1', 'Spring Official', 'https://spring.io', 'Spring Framework 官方网站', false, '2026-01-01 10:00:00', 'system', '2026-01-01 10:00:00', 'system'),
('w-00002', 'v1', 'GitHub', 'https://github.com', '全球最大的代码托管平台', false, '2026-01-02 10:00:00', 'system', '2026-01-02 10:00:00', 'system'),
('w-00003', 'v1', 'Stack Overflow', 'https://stackoverflow.com', '全球最大的开发者问答社区', false, '2026-01-03 10:00:00', 'system', '2026-01-03 10:00:00', 'system'),
('w-00004', 'v1', 'Baeldung', 'https://www.baeldung.com', 'Java 与 Spring 技术博客', false, '2026-01-04 10:00:00', 'system', '2026-01-04 10:00:00', 'system'),
('w-00005', 'v1', '已下线站点', 'https://offline.example.com', '这是一个已被软删除的示例站点', true, '2026-01-05 10:00:00', 'system', '2026-01-05 10:00:00', 'system');
