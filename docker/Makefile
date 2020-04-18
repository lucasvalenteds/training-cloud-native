IMAGE = "application"
TAG = "latest"
PORT = 8081

build:
	@docker build --tag $(IMAGE):$(TAG) .

run:
	@docker run --rm --detach --publish $(PORT):8080 $(IMAGE):$(TAG)
