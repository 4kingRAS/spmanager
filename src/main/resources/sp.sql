USE db_supply;

insert into user_group values(1, 0, 0, '管理员');
insert into user_group values(2, 0, 0, '员工');
insert into user_group values(3, 0, 1, '代理商1');

insert into user values(1, 'HNA', 1, 1, 0, NULL, '123', 'Mickey');

insert into role values(1, '拥有系统所有权限', 'ROLE_HNA');
insert into role values(2, '管理订单，操作货物', 'ROLE_EMPLOYEE');
insert into role values(3, '可以发起订单，查询货物信息', 'ROLE_AGENT');

insert into permission values(1, 1, 1);
insert into permission values(2, 2, 2);
insert into permission values(3, 3, 3);

insert into goods values(1,'最新款苹果笔记本电脑 MacBook Pro 2018','MacBook Pro 2018',15688.01,19999.99,17688.01,'电脑');
insert into goods values(2,' MacBook Air 2018','MacBook Air 2018',5688.01,7999.99,9688.01,'电脑');
insert into goods values(3,'最新华为手机，华为Mate 20 无华为不战狼！爱国就用华为手机！','Huawei Mate 20',1688.01,2999.99,4888.8,'手机');
insert into goods_type values(1, '电脑');
insert into goods_type values(2, '手机');
insert into goods_index values(1, 10, 1);
insert into goods_index values(2, 10, 2);
insert into goods_index values(3, 20, 3);
