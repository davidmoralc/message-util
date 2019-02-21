pipeline {
	agent { 
        docker { image 'cloudbees/java-build-tools' }
    }
    parameters {
        string(name: 'username', defaultValue: 'Jenkins', description: 'How should I greet the world?')
    }
    environment {
		OUTPUT_PATH = './outputs/'
	}
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    echo "OUTPUT_PATH = ${OUTPUT_PATH}"
                ''' 
            }
        }	
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            parallel {
                stage('Test in Linux') {
                    steps {
                        sh 'mvn test'
                    }
                }
                stage('Test in alpine docker') {
                    //agent { docker 'maven:3-alpine' } 
                    steps {
                        echo 'Hello, Maven'
                        sh 'mvn --version'
                    }
                }
                stage('Test in other docker java image') {
                    //agent { docker 'openjdk:8-jre' } 
                    steps {
                        echo 'Hello, JDK'
                        sh 'java -version'
                    }
                }
            }            
            post {
                failure {
                    echo "Test failed Loser!"
                }
                success {
                    echo "Test Ok you are the man!"
                }                                
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy master') {        
            when {
                branch 'master'
            }    
            steps {
                echo "No Deploy"
            }
        }           
        stage('Deploy prod') {                 
            when {
                branch 'prod'
            }            
            steps {
                echo "Deploy in Production!"
            }
        }                
        stage('Deploy dev') {                 
            when {
                branch 'dev'
            }            
            steps {
                echo "Deploy in Dev ${params.username}"
            }            
        }
        stage('Final stage') {
            steps {
                echo "It's Done!"
            }
        }   
    }
}