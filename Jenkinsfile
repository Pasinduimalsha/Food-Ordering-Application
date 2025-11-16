def DOCKER_HUB_ID = "12345678"
def DOCKER_REPO = "pasindu12345/springboot-food-ordering-application"
def SSH_CREDENTIALS_ID = "ec2-ssh-key"
// def EC2_PUBLIC_IP = "REPLACE_WITH_YOUR_EC2_IP_OR_DNS"
// def EC2_USER = "ubuntu" // <<< Change to 'ec2-user' if using Amazon Linux

pipeline {
    agent any

    environment {
        // Dynamic tags for tracking the build
        IMAGE_TAG = "${DOCKER_REPO}:${env.BUILD_NUMBER}"
        IMAGE_LATEST = "${DOCKER_REPO}:latest"
        APP_NAME = "food-ordering-application"
    }

    stages {
        stage('Clean Environment & Prepare') {
            steps {
                echo 'Cleaning up local test environment artifacts...'
                sh '/opt/homebrew/bin/docker compose down --remove-orphans || true'
                sh "/opt/homebrew/bin/docker rmi -f ${IMAGE_TAG} || true"
            }
        }

        stage('Build Application Image') {
            steps {
                script {
                    echo "Building image: ${IMAGE_TAG}"
                    def app = docker.build(env.IMAGE_TAG, ".")
                    app.tag(env.IMAGE_LATEST)
                    env.IMAGE_OBJECT = app
                }
            }
        }

        stage('Run Integration Tests') {
            steps {
                echo 'Starting Docker Compose for local testing...'
                // Uses the freshly built image and pulls the external mysqldb image
                sh "docker compose up -d --build --force-recreate"

                echo "Waiting for services to stabilize (30 seconds)..."
                sleep 30
                // Placeholder for actual testing logic (e.g., cURL health checks)
                sh 'docker ps'
            }
            post {
                always {
                    echo 'Tearing down test environment.'
                    sh 'docker compose down --remove-orphans'
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                script {
                    echo "Pushing images to Docker Hub: ${DOCKER_REPO}"
                    // Authenticate and push both tags
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_HUB_ID) {
                        env.IMAGE_OBJECT.push()
                    }
                }
            }
        }

//         stage('Deploy to EC2') {
//             steps {
//                 script {
//                     echo "Deploying to EC2 instance at ${EC2_PUBLIC_IP}"
//                     // Use sshagent to securely manage the private key for SSH connections
//                     sshagent(credentials: [SSH_CREDENTIALS_ID]) {
//
//                         // 1. Create deployment directory on EC2
//                         sh """
//                             ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_PUBLIC_IP} 'mkdir -p /home/${EC2_USER}/${APP_NAME}'
//                         """
//
//                         // 2. Copy docker-compose.yaml from Jenkins workspace to EC2
//                         // NOTE: Adjust the source path if your docker-compose.yaml is NOT in the root of your Jenkins workspace
//                         sh """
//                             scp -o StrictHostKeyChecking=no ./docker-compose.yaml \
//                             ${EC2_USER}@${EC2_PUBLIC_IP}:/home/${EC2_USER}/${APP_NAME}/docker-compose.yaml
//                         """
//
//                         // 3. Execute remote deployment commands
//                         sh """
//                             ssh ${EC2_USER}@${EC2_PUBLIC_IP} '
//                                 cd /home/${EC2_USER}/${APP_NAME} &&
//                                 docker compose pull &&
//                                 docker compose up -d
//                             '
//                         """
//                     }
//                 }
//             }
//         }
    }

    post {
        always {
            // Clean up the local images regardless of pipeline success
            sh "docker rmi -f ${DOCKER_REPO}:${env.BUILD_NUMBER} || true"
            sh "docker rmi -f ${DOCKER_REPO}:latest || true"
        }
    }
}