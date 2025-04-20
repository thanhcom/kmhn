
pipeline {
    agent any

    environment {
        SSH_KEY = credentials('ssh-remote') // Thay 'your-ssh-key-id' bằng ID của khóa SSH
    }

    stages {
        stage('Thực thi lệnh SSH') {
            steps {
                sshagent(['ssh-remote']) {
                sh 'ssh -o StrictHostKeyChecking=no -l thanhcom server.thanhtrang.online docker stop Tomcat.11'
                }   
            }
        }
    }
}