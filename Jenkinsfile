pipeline {
    agent none
    environment {
        BUILD_SERVER = 'ubuntu@98.93.74.231'
        DEPLOY_SERVER = 'ubuntu@54.227.180.79'
        IMAGE_NAME = "pasindu12345/springboot-food-ordering-application:v0.0.1$BUILD_NUMBER"
    }

    stages {
        stage("Build the docker image and push to dockerhub"){
            agent any
            steps {
                script {
                    sshagent(['Jenkins-slave']){
                        withCredentials([usernamePassword(credentialsId: '12345678', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]){
                            echo "Packing the code and create a docker image"
                            sh "scp -o StrictHostKeyChecking=no -r ${WORKSPACE}/* ${BUILD_SERVER}:/home/ubuntu/"
                            sh "ssh -o StrictHostKeyChecking=no ${BUILD_SERVER} 'bash ~/docker-script.sh'"
                            sh "ssh -o StrictHostKeyChecking=no ${BUILD_SERVER} 'chmod +x mvn-script.sh'"
                            sh "ssh -o StrictHostKeyChecking=no ${BUILD_SERVER} 'bash ~/mvn-script.sh'"

                            echo "Compiling code and creating JAR file on the BUILD_SERVER"
                            sh "ssh -o StrictHostKeyChecking=no ${BUILD_SERVER} 'mvn clean package -DskipTests'"

                            sh "ssh ${BUILD_SERVER} sudo docker build -t ${IMAGE_NAME} /home/ubuntu/"
                            sh "ssh ${BUILD_SERVER} sudo docker login -u $USERNAME -p $PASSWORD"
                            sh "ssh ${BUILD_SERVER} sudo docker push ${IMAGE_NAME}"
                        }
                    }
                }
            }
        }
        stage("Run the docker image using docker-compose"){
            agent any
            steps{
                script{
                    sshagent(['Jenkins-slave']){
                        withCredentials([usernamePassword(credentialsId: '12345678', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]){
                             echo "Pull the docker image"
                             sh "scp -o StrictHostKeyChecking=no -r ${WORKSPACE}/* ${DEPLOY_SERVER}:/home/ubuntu/"
                             sh "ssh -o StrictHostKeyChecking=no ${DEPLOY_SERVER} 'bash ~/docker-script.sh'"
                             sh "ssh ${DEPLOY_SERVER} sudo docker login -u $USERNAME -p $PASSWORD"
                             sh "ssh -o StrictHostKeyChecking=no ${DEPLOY_SERVER} 'bash ~/docker-compose-script.sh ${IMAGE_NAME}'"
                        }
                    }
                }
            }

        }
    }
}