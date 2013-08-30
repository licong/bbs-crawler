bbs-crawler
===========

BBS_Crawler用java编写，目前只适用于北邮人论坛和水木社区

Jsoup http://jsoup.org/
	用于抓取网页html，并筛选出需要的元素
	
JSON http://www.json.org/java/index.html
	JSON采用org.json
	
Javamail http://www.oracle.com/technetwork/java/javamail/index.html 
	javamail用于发送email
	
Xampp http://www.apachefriends.org/zh_cn/xampp.html
	集成了所需的Mysql、Apache、myphpadmin。windows下有图形界面。
	
JDBC http://dev.mysql.com/downloads/connector/j/
	用于连接mysql数据库
	

如何使用：
	1. 配置关键词
    关键词保存在keywords.conf文件中，是正则表达式
	   
	2. 配置邮件客户端
    邮箱地址，用户名，密码的配置需要修改MailPostman.java中带星号“******”的部分
    
  3. 配置收邮件人
    收邮件人的配置在MAIN_REAP_JOB.java中带星号“******”的部分
    
  4. 配置数据库
    数据库连接的配置在DBOperator.java文件中
    
  5. 导入数据库.sql文件
    把127_0_0_1.sql导入到mysql
