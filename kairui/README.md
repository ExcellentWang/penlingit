#ON THE ROAD

#打包命令
####构建出```生产```环境需要的war包
###_```mvn clean package  -Dmaven.test.skip=true -Pproduction```_ 
    
    
####构建出```测试```环境需要的war包
###_```mvn clean package  -Dmaven.test.skip=true -Ptest```_ 
    
    
####构建出```线上测试```环境需要的war包
###_```mvn clean package  -Dmaven.test.skip=true -PonlineTest```_ 
    
    
####构建出```本机```环境需要的war包
###_```mvn clean package  -Dmaven.test.skip=true -Pdevelopment ```_ 
