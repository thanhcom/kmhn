pipeline {
    agent any

    tools {
        maven 'Maven_3'
        jdk 'JDK_17'
    }

    environment {
        IMAGE_NAME = 'thanhcom/my-app'
        IMAGE_TAG = 'latest'
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials'
        SSH_KEY = credentials('ssh-remote')
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/thanhcom/kmhn.git', branch: 'main'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }

        stage('Clean & Copy WAR to Server') {
            steps {
                sshagent(['ssh-remote']) {
                    sh '''
                        set -e
                        echo "🧹 Deleting old WAR and exploded folder on remote server..."
                        ssh -o StrictHostKeyChecking=no thanhcom@thanhcom1989.ddns.net "
                            sudo rm -f /home/thanhcom/tomcat/webapps/kmhn.war &&
                            sudo rm -rf /home/thanhcom/tomcat/webapps/kmhn
                        "
                        echo "✅ Old WAR and exploded folder deleted successfully."
                    '''
                }
            }
        }

        stage('Copy WAR to Server') {
            steps {
                sshagent(['ssh-remote']) {
                    sh '''
                        set -e
                        echo "🚚 Copying WAR file to remote server..."
                        scp -o StrictHostKeyChecking=no target/*.war thanhcom@thanhcom1989.ddns.net:/home/thanhcom/tomcat/webapps/
                        echo "✅ WAR file copied successfully."
                    '''
                }
            }
        }

        stage('SSH restart Tomcat container') {
            steps {
                sshagent(['ssh-remote']) {
                    sh '''
                        echo "♻️ Restarting Tomcat container..."
                        ssh -o StrictHostKeyChecking=no thanhcom@thanhcom1989.ddns.net "sudo docker restart tomcat11"
                        echo "✅ Tomcat container restarted successfully."
                    '''
                }
            }
        }
    }
}
