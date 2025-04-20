
pipeline {
    agent any

    tools {
        maven 'Maven_3'
        jdk 'JDK_17'
    }

    environment {
        IMAGE_NAME = 'thanhcom/my-app'
        IMAGE_TAG = 'latest'
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials' // ID của Docker credentials trong Jenkins
        SSH_KEY = credentials('ssh-remote') // Thay 'your-ssh-key-id' bằng ID của khóa SSH
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

        stage('Copy WAR to Server') {
            steps {
                sshagent(['ssh-remote']) {
                    sh '''
                        echo "Copying WAR file to remote server..."
                        scp -o StrictHostKeyChecking=no target/*.war thanhcom@server.thanhtrang.online:/home/thanhcom/
                    '''
                }
            }
        }

        stage('Delete old image') {
            steps {
                sshagent(['ssh-remote']) {
                    sh '''
                        echo "Delete war file to remote server..."
                        ssh -o StrictHostKeyChecking=no thanhcom@server.thanhtrang.online docker exec Tomcat.11 rm -rf /usr/local/tomcat/webapps/kmhn.war
                    '''
                }
            }
        }

        stage('Delete old file') {
            steps {
                sshagent(['ssh-remote']) {
                    sh '''
                        echo "Delete file to remote server..."
                        ssh -o StrictHostKeyChecking=no thanhcom@server.thanhtrang.online docker exec Tomcat.11 rm -rf /usr/local/tomcat/webapps/kmhn
                    '''
                }
            }
        }

        stage('Copy WAR vào container Tomcat') {
            steps {
                sshagent(['ssh-remote']) {
                    sh '''
                echo "Copy WAR vào container Tomcat..."
                ssh -o StrictHostKeyChecking=no thanhcom@server.thanhtrang.online "docker cp /home/thanhcom/*.war Tomcat.11:/usr/local/tomcat/webapps/"
            '''
        }
    }
}

        stage('Thực thi lệnh SSH restart Tomcat container') {
                        steps {
                            sshagent(['ssh-remote']) {
                                sh 'ssh -o StrictHostKeyChecking=no -l thanhcom server.thanhtrang.online docker restart Tomcat.11'
                        }
                    }
                }
        }
}