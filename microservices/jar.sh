#!/usr/bin/env bash

set -e

projects=( microservice-github microservice-twitter microservice-social )

for project in "${projects[@]}" ;
do
    echo "Generating JAR for project $project"
    cd "$project"
    gradle clean bootJar
    cd ..
done
