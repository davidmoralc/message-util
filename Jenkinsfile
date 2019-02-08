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
        stage('Deploy stage') {        
            parallel {
                stage('DeployPre') {
                    steps {
                        echo "Deploy in Pre ${params.username}"
                    }
                }
                stage('DeployPro') {
                    steps {
                        echo "Deploy in Pro ${params.username}"
                    }
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