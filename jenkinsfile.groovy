def repository = 'serving-runtime-dummy'

def buildFunction={
    sh "sbt compile"
    sh "sbt test"
    sh "sbt docker"
}

def collectTestResults = {
    junit testResults: '**/target/test-reports/io.hydrosphere*.xml', allowEmptyResults: true
}

pipelineCommon(
        repository,
        false, //needSonarQualityGate,
        ["hydrosphere/serving-runtime-dummy"],
        collectTestResults,
        buildFunction,
        buildFunction,
        buildFunction,
        null,
        "",
        "",
        {},
        {}
)
