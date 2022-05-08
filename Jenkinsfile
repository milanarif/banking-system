pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean compile'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Build Image') {
      when {
        branch 'master'
      }
      post {
        success {
          archiveArtifacts 'target/*.jar'
        }

      }
      steps {
        sh 'mvn package'
        sh 'docker build -t milanarif/banking-system .'
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
  tools {
    maven 'maven'
  }
}