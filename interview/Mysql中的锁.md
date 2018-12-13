# Mysql InnoDB中的锁

* 共享锁（行锁）：Shared Locks

* 排它锁（行锁）：Exclusive Locks

* 意向锁共享锁（表锁）：Intention Shared Locks

* 意向锁排它锁（表锁）：Intention Exclusive Locks

* 自增锁：AUTO-INC Locks

  ​    **innodb_autoinc_lock_mode**  

  * innodb_autoinc_lock_mode  = 0

    传统方式，串行自增，连续，需要独占串行锁，**申请锁然后插入语句执行完才释放锁**，性能最低

    例如：1、2、3.

  * innodb_autoinc_lock_mode  = 1

    自增，连续，**申请锁后就释放锁**，性能好。系统默认。1、2、3、4、5.若4回滚，则跳过4.

  * 交错方式。多语句插入数据时，有可能自增的序列号和执行先后顺不一致，并且中间可能有断裂。**一次分配一批自增值**，然后下个语句就再进行分配一批自增值，阻塞很小，性能很高。例如：1、2、3、6、5 

### 行锁算法

* 记录锁 Record Locks
* 间隙锁 Gap Locks
* 临键锁 Next-key Locks