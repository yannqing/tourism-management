pipeline {
    agent any
    environment {
        GIT_URL = 'git@github.com:yannqing/tourism-management.git'
        APP_NAME = 'tourismmanagement'
        APP_PROFILE = 'prod'
        APP_IMAGE = 'tourismmanagement:latest'
        APP_PORT = '8092:8080'
    }

    stages {
            stage('查看路径') {
                steps {
                    script {
                        // 替换 dev.yml 文件
                        sh 'pwd'
                    }
                }
            }
        stage('替换配置文件') {
            steps {
                script {
                    // 替换 dev.yml 文件
                    sh 'cp /yannqing/tourismmanagement/application-dev.yml /var/lib/docker/volumes/jenkins_home/_data/workspace/tourism-management-backend/src/main/resources/application-dev.yml'
                }
            }
        }
        stage('拉取代码') {
            steps {
                git branch: 'master', url: GIT_URL
            }
        }
        stage('编译构建') {
            steps {
                sh "mvn clean package"
            }
        }
        stage('移除镜像和容器') {
            steps {
                script {
                    // 检查容器是否存在
                    def containerExists = sh(script: 'docker ps -a --format "{{.Names}}" | grep -q "^${APP_NAME}"', returnStatus: true) == 0
                    // 检查镜像是否存在
                    def imageExists = sh(script: 'docker images --format "{{.Repository}}:{{.Tag}}" | grep -q "^${APP_IMAGE}$"', returnStatus: true) == 0

                    // 如果容器存在，则停止和移除
                    if (containerExists) {
                        echo "容器存在"
                        def isRunning = sh(script: 'docker ps --format "{{.Names}}" | grep -q "^${APP_NAME}"', returnStatus: true) == 0
                        if (isRunning) {
                            sh "docker stop ${APP_NAME}"
                        }
                        echo "删除容器"
                        sh "docker rm ${APP_NAME}"
                    } else {
                        echo "容器不存在"
                    }

                    // 如果镜像存在，则移除
                    if (imageExists) {
                        echo "删除镜像"
                        sh "docker rmi ${APP_IMAGE}"
                    } else {
                        echo "镜像不存在"
                    }
                }
            }
        }
        stage('构建镜像，创建并运行容器') {
            steps {
                // 基于 Dockerfile 进行构建
                sh "docker build -f Dockerfile.dockerfile -t ${APP_IMAGE} ."
                sh "docker run -it --name ${APP_NAME} --network mynetwork -v /yannqing/${APP_NAME}:/yannqing/${APP_NAME} -p ${APP_PORT} -d ${APP_IMAGE}"
            }
        }
    }
}