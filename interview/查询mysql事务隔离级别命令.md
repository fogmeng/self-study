# 查询mysql事务隔离级别命令

1. 查看当前会话隔离级别

   select @@tx_isolation;

2. 查看系统当前隔离级别

   select @@global.tx_isolation; 

3. 设置当前会话隔离级别

    set session transaction isolatin level repeatable read;

4. 设置系统当前隔离级别

   set global transaction isolation level repeatable read;

5. 命令行，开始事务时

   set autocommit=off 或者 start transaction