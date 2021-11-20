
# Maven 命令

## [Versions Maven Plugin](http://www.mojohaus.org/versions-maven-plugin/index.html)

|  命令                               |  描述                                    |
|-------------------------------------|-----------------------------------------|  
|mvn versions:set -DnewVersion=版本号 |  设置当前项目的版本，并根据该更改将该更改传播到任何需要的子模块上。|
|mvn versions:commit                  |  删除 pom 的初始备份，从而接受版本的更改  | 
