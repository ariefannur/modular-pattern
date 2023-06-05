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
	            sh "echo param ${params.branch}"
	            git (
	                url: "https://github.com/ariefannur/modular-pattern/",
	                branch: "${params.branch}"
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
			steps {
				sh "./gradlew testDebugUnitTest"
			}
		}

		stage('Build') {
			steps {
				sh "./gradlew assembleDebug"
			}
		}
	}
}