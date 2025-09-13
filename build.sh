#!/bin/bash
set -e

IMAGE_NAME="test-build"
TAG="latest"

echo "=== Building Docker image: $IMAGE_NAME:$TAG ========mayankfsdfasdfaf"
# docker build -t $IMAGE_NAME:$TAG .

echo "=== Running container to verify build ==="
# docker run --rm $IMAGE_NAME:$TAG

echo "ENV VAR $Name"
echo "test"
