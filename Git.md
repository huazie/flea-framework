
# Git 命令
|  命令                                |  描述                                    |
|--------------------------------------|-----------------------------------------|  
|git branch 分支名                      |  创建分支                               |
|git branch                            |  查看分支（名称前面加* 号的是当前的分支）  |
|git branch -a                         |  查看远程分支（加上-a参数可以查看远程分支）|
|git branch -d 分支名                   |  删除本地分支                           | 
|git branch -r -d origin/分支名         |  删除远程分支                           |
|git checkout 分支名                    |  切换分支                               |
|git checkout --track origin/分支名     |  如果远程新建了一个分支，本地没有该分支   |
|git push --set-upstream origin 分支名  |  如果本地新建了一个分支，但是在远程没有   |
|git pull origin 分支名                 |  将远程分支代码拉下来                    |
|git push origin 分支名                 |  推送当前分支到远端                      |
|git merge 指定分支名                   |  合并指定分支至当前分支                  |
|git status                             |  查看分支状态                           |
|git add .                              |  添加代码到暂存区                        |
|git commit -m "备注"                   |  添加备注，并提交到本地仓库中             |
|git config --system --list             |  查看系统config                         |
|git config --global --list             |  查看当前用户（global）配置              |
|git config --local --list              |  查看当前仓库配置信息                    |
|git tag                                |  查看tag列表                            |
|git tag -d tag名                       |  删除tag                                |
|git push origin :tag名                 |  提交删除tag                            |


## 合并A分支到B分支
1. git checkout B  
2. git pull origin B 【远端可能有更新，所以要更新下】
3. git merge A  【将A分支合并B分支】
4. git status

## 提交代码至远端仓库
1. git add .
2. git commit -m "test"
3. git push origin 分支名