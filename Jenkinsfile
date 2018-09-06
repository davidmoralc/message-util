pipeline {
	agent { label 'ubuntu-16-git-java-maven' }
    def username = 'Jenkins'
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
            steps {
                sh 'mvn test'
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
        parallel {
            stage('DeployPre') {
                steps {
                    echo "Deploy in Pre ${username}"
                }
            }
            stage('DeployPro') {
                steps {
                    echo "Deploy en Pro ${username}"
                }
            }
        }
        stage('Final stage') {
            steps {
                echo "It's Done!"
            }
        }        
    }
}