USE db_supply;

insert into user_group values(1, 1, 0, '管理员');
insert into user_group values(2, 1, 0, '采购员工');
insert into user_group values(3, 1, 0, '审核员工');
insert into user_group values(4, 1, 1, '代理商1');

insert into user values(1, 'HNA', 1, 1, 0, NULL, '123', 'Mickey');
insert into user values(2, 'HNA', 2, 1, 0, NULL, '123', 'kitty');
insert into user values(3, 'HNA', 3, 1, 0, NULL, '123', 'john');
insert into user values(4, 'IBM', 4, 1, 0, NULL, '123', '马云');

insert into role values(1, '拥有系统所有权限', 'ROLE_HNA');
insert into role values(2, '负责采购货物', 'ROLE_BUYER');
insert into role values(3, '审核所有订单', 'ROLE_CHECKER');
insert into role values(4, '可以发起订单，查询货物信息', 'ROLE_AGENT');

insert into permission values(1, 1, 1);
insert into permission values(2, 2, 2);
insert into permission values(3, 3, 3);
insert into permission values(4, 4, 4);

insert into goods values(1,'最新款苹果笔记本电脑 MacBook Pro 2018','MacBook Pro 2018',15688.01,19999.99,17688.01,'电脑');
insert into goods values(2,' MacBook Air 2018','MacBook Air 2018',5688.01,7999.99,9688.01,'电脑');
insert into goods values(3,'最新华为手机，华为Mate 20 无华为不战狼！爱国就用华为手机！','Huawei Mate 20',1688.01,2999.99,4888.8,'手机');
insert into goods values(4,'CHANEL 香奈儿五号香水（经典）红色限量版*','Chanel N°5',1288.01,1599.99,1788.99,'化妆品');
insert into goods values(5,'Pixel3的背板采用金属与玻璃拼接的技术，其工艺难度可想而知。并且相较于前代，Pixel3上增加了无线充电功能。','Google Pixel 3',2988.01,3499.99,3788.99,'手机');
insert into goods_type values(1, '电脑');
insert into goods_type values(2, '手机');
insert into goods_type values(3, '化妆品');
insert into goods_index values(1, 10, 1);
insert into goods_index values(2, 10, 2);
insert into goods_index values(3, 20, 3);
insert into goods_index values(4, 0, 4);
