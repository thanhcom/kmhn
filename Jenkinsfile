
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
                        echo "✅ Copying WAR file to remote server..."
                        # Xoá folder app cũ đã giải nén nếu có (rất quan trọng để Tomcat redeploy)
                        ssh -o StrictHostKeyChecking=no thanhcom@thanhcom1989.ddns.net '
                            rm -rf /home/thanhcom/tomcat/webapps/kmhn.war
                        '

                        # Copy file .war mới
                        scp -o StrictHostKeyChecking=no target/kmhn.war thanhcom@thanhcom1989.ddns.net:/home/thanhcom/tomcat/webapps/

                        echo "🚀 WAR file copied to Tomcat webapps successfully"
                    '''
                }
            }
        }

    }
}

        stage('SSH restart Tomcat container') {
                        steps {
                            sshagent(['ssh-remote']) {
                                sh 'ssh -o StrictHostKeyChecking=no -l thanhcom thanhcom1989.ddns.net docker restart Tomcat.11'
                        }
                    }
                }
        }
}
