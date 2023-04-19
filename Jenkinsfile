pipeline {		
	agent { 
		node { label 'Build Pipeline'}
	}
	stages {
		stage('Test') {
			./gradlew testDebugUnitTest
		}

		stage('Build') {
			./gradlew assembleDebug
		}
	}
}
