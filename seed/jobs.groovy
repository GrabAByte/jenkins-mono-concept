#!/usr/bin/env groovy
// PSEUDO CODE ONLY
def List envs     = ["dev", "staging", "prod"]
def List projects = ["A", "B", "C"]
def String id     = 100000000 + random.nextInt(900000000)

// Add main upstream job that performs remote trigger to downstream jobs
multibranchPipelineJob("POC/Upstream-Check-For-Changes-Pipeline") {
    branchSources {
        git {
            id("${id}"i)
            remote('https://github.com/GrabAByte/jenkins-mono-concept.git')
            credentialsId('github-ci')
            includes('*')
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(5)
        }
    }
}

// Add all downstream jobs
for (env in envs) {
    for (project in projects) {
        multibranchPipelineJob("POC/${env}/${project}/Check-For-Changes-Pipeline") {
            branchSources {
                git {
                    id("${id}")
                    remote('https://github.com/GrabAByte/jenkins-mono-concept.git')
                    credentialsId('github-ci')
                    includes('*')
                }
            }
            orphanedItemStrategy {
                discardOldItems {
                    numToKeep(5)
                }
            }
        }
    }
}
