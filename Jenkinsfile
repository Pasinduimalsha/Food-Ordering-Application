pipeline {
    agent none
    parameters {
        booleanParam(name: 'autoApprove', defaultValue: false, description: 'Automatically run apply after generating plan?')
    }
    environment {
        BUILD_SERVER = 'ubuntu@98.93.74.231'
        DEPLOY_SERVER = 'ubuntu@54.227.180.79'
         AWS_ACCESS_KEY_ID     = credentials('AWS_ACCESS_KEY_ID')
        AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_ACCESS_KEY')
        IMAGE_NAME = "pasindu12345/springboot-food-ordering-application:v0.0.1$BUILD_NUMBER"
    }

    stages {
        stage('checkout') {
            agent any
            steps {
                 script{
                        dir("terraform")
                        {
                            git "https://github.com/Pasinduimalsha/Food-Ordering-Application.git"
                        }
                    }
                }
        }
        stage('Plan') {
            agent any
            steps {
                withEnv(["PATH+LOCAL=/usr/local/bin:/opt/homebrew/bin"]){
                    sh 'pwd;cd terraform/ ; terraform init'
                    sh "pwd;cd terraform/ ; terraform plan -out tfplan"
                    sh 'pwd;cd terraform/ ; terraform show -no-color tfplan > tfplan.txt'
                }
            }
        }
        stage('Approval') {
            agent any
            when {
                not {
                    equals expected: true, actual: params.autoApprove
                }
            }
           steps {
               script {
                    def plan = readFile 'terraform/tfplan.txt'
                    input message: "Do you want to apply the plan?",
                    parameters: [text(name: 'Plan', description: 'Please review the plan', defaultValue: plan)]
               }
           }
        }
        stage('Apply') {
            agent any
            steps {
                sh "pwd;cd terraform/ ; terraform apply -input=false tfplan"
            }
        }
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