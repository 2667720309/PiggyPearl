## 前端运行

``` bash
# 进入pigWeb目录后才可运行
cd pigWeb

# 安装依赖项
npm i

# 启动前端调试
npm run dev

# 编译前端项目
npm run build
```

### Git命令
``` bash
# 避免每次输入密码
git config --global credential.helper store

# 初始化git依赖文件
git init

# 从网络拉取项目
git clone <url>

# 推送变更项
git add <files>
git commit -m 'tips'
git push

# 拉取变更
git pull

# 获取分支
git fetch

# 切换分支
git checkout <branch>

# 合并分支
git checkout <targetBranch>
git merge --no-ff <mergedBranch>
git commit -m 'tips'
git push

# 放弃本地修改
git reset --hard
```

### Forever命令
``` bash
# 启动node项目(file一般为app.js)
forever start <file>

# 监听当前文件夹下所有改动并重启服务
forever start -w <file>

# 显示所有运行的服务
forever list

# 停止所有运行的服务
forever stopall

# 停止其中一个服务
forever stop <file>
# 或者
forever stop [id]

# 重启服务
forever restartall
# 或者
forever restart <file>
#
forever restart [id]
```

### Linux命令行
``` bash
# 新建文件夹
mkdir <name>

# 新建文件
touch <name.x>

# 删除文件
rm -rf <name>

# 进入路径
cd <path>

# 查看当前位置下文件
ls
# 查看当前位置的绝对目录
pwd

# 登陆
su root

# 新增账户
sudo adduser <name>

# 修改密码
sudo passwd <name>

# 上传文件
scp -p <flies> <username>@<website>:<path>

# 跳过公钥检查
yum install xxx.rpm --nogpgcheck

# 清屏
clear

# 压缩
tar -zcvf <name>.tar.gz <sourceName>
# 解压
tar -zxvf <name>.tar.gz

# 修改文件权限
chmod -R 000 <path> (000读写执：二进制最高为7)


# docker相关
# 启动
service docker start
# 挂载vue项目(需npm run build先编译)
docker run -d -p <port>:80 -v /$PWD/dist:/usr/share/nginx/html nginx
# 查看挂载的项目
docker ps
# 停止运行
docker stop <id>
# 重新运行
docker restart <id>


# 防火墙管理
# 开放端口
firewall-cmd --zone=public --add-port=<port>/tcp --permanent
# 配置立即生效
firewall-cmd --reload
# 查看防火墙所有开放的端口
firewall-cmd --zone=public --list-ports
# 查看监听的端口
netstat -lnpt
# 检查端口被哪个进程占用
netstat -lnpt |grep 5672


# mysql数据库
# 登陆数据库
mysql -u root -p
# 收回用户权限
REVOKE ALL PRIVILEGES,GRANT OPTION FROM 'username'@'%';
# 给予用户权限
GRANT SELECT, INSERT, UPDATE, REFERENCES, DELETE, CREATE, DROP, ALTER, INDEX, CREATE VIEW, SHOW VIEW ON `java`.* TO 'username'@'%';
# 刷新
flush privileges;
```
