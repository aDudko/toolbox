### Build Docker image
```
docker build -t [image_name]:[version] .
```

### Create Docker image to Docker Hub
```
docker login
```
```
docker tag [image_name]:[version] anatolydudko/[image_name]:[version]
```
```
docker push anatolydudko/[image_name]:[version]
```