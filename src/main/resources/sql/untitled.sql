-- 这是学习Mybatis-Plus的脚本文件
Create database `mybatis_plus`;
use `mybatis_plus`;
CREATE TABLE `user`(
                       `id` bigint(20) NOT NULL COMMENT '主键ID',
                       `name` varchar(30) DEFAULT NULL COMMENT '姓名',
                       `age` int(11) DEFAULT NULL COMMENT '年龄',
                       `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                       PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO user(id, name, age, email) VALUES
                                           (1, 'Jone', 18, 'test1@baomidou.com'),
                                           (2, 'Jack', 20, 'test2@baomidou.com'),
                                           (3, 'Tom', 28, 'test3@baomidou.com'),
                                           (4, 'Sandy', 21, 'test4@baomidou.com'),
                                           (5, 'Billie', 24, 'test5@baomidou.com');
CREATE TABLE `t_user`(
                         `uid` bigint(20) NOT NULL COMMENT '主键ID',
                         `user_name` varchar(30) DEFAULT NULL COMMENT '姓名',
                         `age` int(11) DEFAULT NULL COMMENT '年龄',
                         `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                         `is_deleted` int(11) DEFAULT NULL DEFAULT 0 COMMENT '删除标志',
                         PRIMARY KEY (`uid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO t_user(uid, user_name, age, email) VALUES
                                                   (1, 'Jone', 18, 'test1@baomidou.com'),
                                                   (2, 'Jack', 20, 'test2@baomidou.com'),
                                                   (3, 'Tom', 28, 'test3@baomidou.com'),
                                                   (4, 'Sandy', 21, 'test4@baomidou.com'),
                                                   (5, 'Billie', 24, 'test5@baomidou.com');
