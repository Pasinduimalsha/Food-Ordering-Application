
def APP_COMPOSE_IMAGE = "springboot-food-ordering-application:v0.0.1"
def DB_COMPOSE_IMAGE  = "mysql:8.0"                                

pipeline {
    agent any

    environment {
        REGISTRY_URL        = "docker.io"
        DOCKER_CREDENTIALS  = "pasindu12345"    
        DOCKER_IMAGE_APP    = ""               
        DOCKER_IMAGE_DB     = ""   
    }

    stages {
        stage('Compose Build') {
            steps {
                echo "Building services with docker-compose..."
                sh 'docker-compose build --parallel'
            }
        }

        stage('Integration Test (compose up)') {
            steps {
                echo "Starting services for integration test..."
                sh 'docker-compose up -d'
                // basic wait / health checks — adjust as needed
                sh '''
                    echo "Waiting for app to start..."
                    sleep 20
                '''
            }
            post {
                always {
                    echo "Stopping compose test environment..."
                    sh 'docker-compose down'
                }
            }
        }

        stage('Tag & Push Images') {
            steps {
                script {
                    if (!env.DOCKER_IMAGE_APP) {
                        error "DOCKER_IMAGE_APP not set. Configure target registry image (e.g. myuser/food-ordering-application)."
                    }

                    withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS,
                                                      usernameVariable: 'DOCKER_USER',
                                                      passwordVariable: 'DOCKER_PASS')]) {
                        // Login
                        sh "echo \$DOCKER_PASS | docker login ${env.REGISTRY_URL} --username \$DOCKER_USER --password-stdin"

                        // Tag & push app image built by docker-compose
                        sh """
                            echo "Tagging app image ${APP_COMPOSE_IMAGE} -> ${DOCKER_IMAGE_APP}:${env.BUILD_NUMBER}"
                            docker tag ${APP_COMPOSE_IMAGE} ${DOCKER_IMAGE_APP}:${env.BUILD_NUMBER}
                            docker tag ${APP_COMPOSE_IMAGE} ${DOCKER_IMAGE_APP}:latest

                            docker push ${DOCKER_IMAGE_APP}:${env.BUILD_NUMBER}
                            docker push ${DOCKER_IMAGE_APP}:latest
                        """

                        // Optional: tag & push DB image if configured
                        if (env.DOCKER_IMAGE_DB?.trim()) {
                            sh """
                                echo "Preparing DB image ${DB_COMPOSE_IMAGE} -> ${DOCKER_IMAGE_DB}:${env.BUILD_NUMBER}"
                                docker pull ${DB_COMPOSE_IMAGE} || true
                                docker tag ${DB_COMPOSE_IMAGE} ${DOCKER_IMAGE_DB}:${env.BUILD_NUMBER}
                                docker tag ${DB_COMPOSE_IMAGE} ${DOCKER_IMAGE_DB}:latest

                                docker push ${DOCKER_IMAGE_DB}:${env.BUILD_NUMBER}
                                docker push ${DOCKER_IMAGE_DB}:latest
                            """
                        } else {
                            echo "DOCKER_IMAGE_DB not provided — skipping DB push."
                        }

                        // Logout
                        sh 'docker logout ${REGISTRY_URL}'
                    }
                }
            }
        }

        stage('Verify Pull (optional)') {
            steps {
                script {
                    echo "Verifying pushed app image by pulling it..."
                    sh "docker rmi -f ${env.DOCKER_IMAGE_APP}:latest || true"
                    sh "docker pull ${env.DOCKER_IMAGE_APP}:latest"
                }
            }
        }
    }

    post {
        always {
            echo "Cleanup local temp images"
            sh "docker rmi -f ${DOCKER_IMAGE_APP}:${env.BUILD_NUMBER} || true"
        }
    }
}