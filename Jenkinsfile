pipeline {
    agent any

    environment {
        STUDENT    = 'Ulpan Saparbek'
        IMAGE_NAME = 'saparbek-ulpan-student-api'
        IMAGE_TAG  = 'v1.0-saparbek-ulpan'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                sh 'echo "Student: $STUDENT"'
                sh 'git log -1 --oneline'
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean bootJar -x test --no-daemon'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG .'
            }
        }

        stage('Verify') {
            steps {
                sh 'docker images $IMAGE_NAME'
                sh 'docker image inspect $IMAGE_NAME:$IMAGE_TAG --format "Image OK: {{.RepoTags}}"'
            }
        }
    }

    post {
        success { echo "Pipeline finished successfully for $STUDENT" }
        failure { echo "Pipeline FAILED for $STUDENT" }
    }
}
