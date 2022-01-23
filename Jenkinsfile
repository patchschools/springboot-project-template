node {
    checkout scm
    sh './mvnw -B -DskipTests clean package'
    docker.build("technocis/demoapp").push()
}