
# Maven 命令

## [Versions Maven Plugin](http://www.mojohaus.org/versions-maven-plugin/index.html)

|  命令                               |  描述                                    |
|-------------------------------------|-----------------------------------------|  
|mvn versions:set -DnewVersion=版本号 |  设置当前项目的版本，并根据该更改将该更改传播到任何需要的子模块上。|
|mvn versions:commit                  |  删除 pom 的初始备份，从而接受版本的更改  | 
|mvn versions:revert                  |  从 ```pom.xml.versionsBackup``` 文件中恢复 ```pom.xml``` 文件|

## 其他
|  命令                               |  描述                                    |
|-------------------------------------|-----------------------------------------|  
|mvn help:evaluate -Dexpression=settings.localRepository |  查看mvn 本地仓库的位置。|
|mvn deploy -DaltDeploymentRepository=my-release-repo::default::http://mirrors.csdn.net.cn/repository/maven-public/ | 部署项目依赖到指定仓库  | 
