pipeline {		
	agent { 
		node { label 'Build Pipeline'}
	}
	stages {
		stage('Test') {
			sh './gradlew testDebugUnitTest'
		}

		stage('Build') {
			sh './gradlew assembleDebug'
		}
	}
}
