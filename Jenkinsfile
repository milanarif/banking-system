pipeline {
    agent any
    tools{
        maven 'Maven 3.6.3'
    }
    stages {
        stage('Build'){
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test'){
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Image'){
            when {
                branch 'master'
            }
            steps {
                sh 'mvn package'
                sh 'docker build -t milanarif/banking-system .'
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        stage('Run Image') {
            when {
                branch 'master'
            }
            steps {
                sh 'docker run milanarif/banking-system'
            }
        }
        stage('Push Image') {
            when {
                branch 'master'
            }
            steps {
                sh 'docker login --username=${DOCKERHUB_USERNAME} --password=${DOCKERHUB_PASSWORD}'
                sh 'docker push milanarif/banking-system'
            }
        }
    }
}