# Use the official Nginx image as the base image
FROM nginx:alpine

# Set the working directory inside the container
WORKDIR /usr/share/nginx/html

# Copy all frontend files into the container
COPY . .

# Expose port 80 for the container
EXPOSE 80

# Default command to start Nginx
CMD ["nginx", "-g", "daemon off;"]

