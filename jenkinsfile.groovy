pipeline {
    agent any

    environment {
        // Maven 설치 경로 지정
        MAVEN_HOME = tool name: 'Maven', type: 'hudson.tasks.Maven$MavenInstallation'
    }

    stages {
        stage('Checkout') {
            steps {
                // Git 저장소에서 소스 코드를 체크아웃
                git 'https://github.com/impati/soccer-server.git'
            }
        }

        stage('Build') {
            steps {
                // Maven 빌드 명령 실행
                sh "${MAVEN_HOME}/bin/mvn clean package"
            }
        }

        stage('Archive') {
            steps {
                // 빌드된 .jar 파일을 Jenkins 아티팩트로 저장
                archiveArtifacts artifacts: 'soccer-batch-0.0.1-SNAPSHOT.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
