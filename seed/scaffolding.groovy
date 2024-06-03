#!/usr/bin/env groovy
// PSEUDO CODE ONLY
def List envs     = ["dev", "staging", "prod"]
def List projects = ["A", "B", "C"]

// Create seed job directory structure
folder('POC') {
    displayName('POC')
    description('Upper most parent directory for the Mono Concept POC')
}

for (env in envs) {
    for (project in projects) {
        folder("POC/${env}/${project}")
            displayName("POC-${env}-${project}")
            description("POC for environment: ${env} and Project: ${project}")
    }
}
