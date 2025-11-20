pipeline {
    agent none
    environment {
        BUILD_SERVER = 'ubuntu@54.86.125.128'
//         DEPLOY_SERVER = 'ubuntu@'
        IMAGE_NAME = "pasindu12345/springboot-food-ordering-application:v0.0.1$BUILD_NUMBER"
    }


    stages {
        //  stage("Build Application in the remote") {
        //     agent { 
        //         agent any
        //         docker { 
        //             image 'maven:3.8.7-jdk-11'
        //             args '-v $HOME/.m2:/root/.m2' 
        //         } 
        //     }
        //     steps {
        //         echo "Compiling the Spring Boot application and generating the JAR file in the local 'target/' directory."
        //         sh "mvn clean package -DskipTests" 
        //     }
        // }

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