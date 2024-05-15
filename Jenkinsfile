pipeline {
    agent any

    environment {
        // Gradle 설치 경로 지정 (사전 정의된 Gradle 설치 사용)
        GRADLE_HOME = tool name: 'Gradle', type: 'hudson.plugins.gradle.GradleInstallation'
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
                // Gradle 빌드 명령 실행
                sh "${GRADLE_HOME}/bin/gradle clean build"
            }
        }

        stage('Archive') {
            steps {
                // 빌드된 .jar 파일을 Jenkins 아티팩트로 저장
                archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Build and execution succeeded!'
        }
        failure {
            echo 'Build or execution failed!'
        }
    }
}
