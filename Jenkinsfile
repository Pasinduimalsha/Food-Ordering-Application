// Define variables
def APP_NAME = "food_ordering_application"
def REGISTRY_URL = "docker.io" // Docker Hub
def CREDENTIALS_ID = "docker-hub-credentials" // From Step 2.3

pipeline {
    agent any // Runs on the local machine where Jenkins is running

    stages {
        stage('Build Application Image') {
            steps {
                script {
                    // Build the application image using the local Dockerfile
                    def appImage = docker.build("${APP_NAME}:${env.BUILD_NUMBER}", ".")
                    
                    // Tag the image for Docker Hub
                    appImage.tag("${APP_NAME}:latest")
                    
                    // Store the image object for the next stages
                    env.IMAGE_OBJECT = appImage 
                }
            }
        }
        
        stage('Pull Database & Run Test Environment') {
             // This stage uses docker-compose to pull the external DB image
             // and run both services for integration testing.
            steps {
                sh 'docker-compose pull' // Pulls the mysqldb image from Docker Hub
                sh 'docker-compose up -d' // Starts the application and DB services
                
                // --- Add actual testing logic here ---
                // e.g., wait for services, run cURL checks, run application tests
                echo "Wait for application to stabilize before testing..."
                sleep 20 // Wait 20 seconds
            }
            post {
                // Ensure containers are stopped and removed after testing
                always {
                    sh 'docker-compose down'
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                script {
                    // Load the image object
                    def appImage = env.IMAGE_OBJECT
                    
                    // Authenticate and Push (requires the stored CREDENTIALS_ID)
                    docker.withRegistry("https://${REGISTRY_URL}", CREDENTIALS_ID) {
                        appImage.push() // Pushes both tags: BUILD_NUMBER and latest
                    }
                }
            }
        }
        
        stage('Pull Image from Docker Hub (Verification)') {
            // Optional: Verify the push by deleting the local image and pulling the new one
            steps {
                sh "docker rmi -f ${APP_NAME}:latest || true" // Delete local copy
                sh "docker pull ${APP_NAME}:latest" // Pull from Docker Hub
                echo "Successfully verified image pull from Docker Hub."
            }
        }
    }
    
    post {
        always {
            // Clean up the local image created during the build stage
            sh "docker rmi -f ${APP_NAME}:${env.BUILD_NUMBER} || true"
        }
    }
}