pipeline {
    agent none
    environment {
        BUILD_SERVER = 'ubuntu@54.86.125.128'
//         DEPLOY_SERVER = 'ubuntu@'
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
                            sh "scp -o StrictHostKeyChecking=no -r ./docker-script.sh ${BUILD_SERVER}:/home/ubuntu/docker-script.sh"
                            sh "ssh -o StrictHostKeyChecking=no ${BUILD_SERVER} 'bash ~/docker-script.sh'"
                            sh "ssh ${BUILD_SERVER} sudo docker build-t f{IMAGE_NAME} /home/ubuntu/"
                            sh "ssh ${BUILD_SERVER} sudo docker login -u ${USERNAME} p $PASSWORD"
                            sh "shh ${BUILD_SERVER} sudo docker push ${IMAGE_NAME}"
                        }
                    }
                }
            }
        }
//         stage("Run the docker image using docker-compose"){
//             agent any
//             steps{
//                 script{
//                     sshagent(['Jenkins-slave']){
//                         withCredentials([usernamePassword(credentialsId: '12345678', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]){
//                              echo "Pull the docker image"
//                              sh "scp -o StrictHostKeyChecking=no+r docker-files ${DEPLOY_SERVER}):/home/ubuntu"
//                              sh "ssh -o StrictHostKeyChecking=no ${DEPLOY_SERVER} 'bash ~/docker-script.sh'"
//                              sh "ssh ${BUILD_SERVER} sudo docker login -u ${USERNAME} p $PASSWORD"
//                              sh "ssh -o StrictHostKeyChecking=no ${DEPLOY_SERVER} 'bash ~/docker-compose-script.sh ${IMAGE_NAME}'"
//
//                         }
//
//                     }
//                 }
//             }
//
//         }
    }
}