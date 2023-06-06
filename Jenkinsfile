pipeline {		
	agent any

    environment {
        PATH="/opt/homebrew/opt/openjdk@11/bin:${env.PATH}"
        JAVA_HOME="/opt/homebrew/opt/openjdk@11/"
        ANDROID_SDK_ROOT="/Users/ariefmaffrudin/Library/Android/sdk"
    }
	stages {
	    stage('Checkout') {
	        steps {
	            git (
	                url: "https://github.com/ariefannur/modular-pattern/",
					credentialsId: "",
	                branch: "master"
	                )
	        }
	    }
	    stage('Tools test') {
	        steps {
	            sh '''
	                env | grep -e PATH -e JAVA_HOME
	                echo $JAVA_HOME
	                which java
	                java --version
	                echo token="abc" > ./local.properties
                '''
	        }
	    }
		stage('Test') {
			when {
				branch "feature/*"
			}
			steps {
				sh "./gradlew testDebugUnitTest"
			}
			post {
        		always {
            		junit 'build/reports/**/*.xml'
        		}
    		}
		}

		stage('Build') {
			when {
				branch "master"
			}
			steps {
				sh "./gradlew assembleDebug"
			}
		}
	}
}