#!/usr/bin/env groovy
def envs
def projects

def checkForChanges(path) {
  try {
    sh("git diff --quiet --exit-code HEAD~1..HEAD ${path}")
    return false
  }

  catch (err) {
    return true
  }
}

pipeline {
  agent any
  stages {
    stage('Create Lists') {
      steps {
        script {
          envs = ["dev", "staging", "prod"]
          projects = ["A", "B", "C"]
        }
      }
    }
    stage('Run Dynamic Stages') {
      steps {
        script {
          for(int e=0; e < envs.size(); e++) {
            for(int p=0; p < projects.size(); p++) {
              stage("${envs[e]}-${projects[p]}"){
                if ( checkForChanges("environments/${envs[e]}/${projects[p]}/Jenkinsfile") ) {
                  build job: "POC/${envs[e]}/${projects[p]}/${projects[p]}-pipeline", propagate: true, wait: true
                }
              }
            }
          }
        }
      }
    }
  }
}
