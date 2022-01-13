pipeline {

    agent any
    tools {
        jdk 'jdk-8'
    }

    stages {
        stage('compile') {
            steps {
                echo '\n\n==================='
                sh 'mvn compile -Dmaven.test.skip'
            }
        }

        stage('test') {
            steps {
                echo '\n\n==================='
                sh 'mvn test'
            }
        }

        stage('package') {
            steps {
                echo '\n\n==================='
                sh 'mvn clean package -Dmaven.test.skip'
            }
        }
    }
}