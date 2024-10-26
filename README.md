# Community Board Application

This project is a community board application developed using Spring Boot and Thymeleaf. Users can create new communities, post content within them, and browse posts created by others.

**Note:** This project was developed using Microsoft Visual Studio Code. Compatibility issues may arise if working with IntelliJ IDEA.

## Changelog
- **2024.10.2**: Uploaded to GitHub.
- **Current Errors**: No known or detected errors at this time.

## Features
- Create communities
- View a list of communities
- Create posts
- View posts within specific communities
- Edit and delete posts

## Tech Stack
- **Backend**: Java, Spring Boot
- **Frontend**: Thymeleaf, Bootstrap
- **Database**: H2 (development and testing), MariaDB (production)

## Project Structure

The project is organized as follows:

- **Controller**: Manages HTTP requests for the application.
- **Entity**: Contains entity classes mapped to database tables.
- **Repository**: Handles interactions with the database.
- **Service**: Processes business logic for the application.
- **Templates**: Thymeleaf template files for the front end.

## How to Use

Upon accessing the application, users can create a new community by clicking the "Create Community" button. After creating a community, users can click on it to view a list of posts in that community. They can also add new posts by clicking the "Create Post" button. By clicking on a post title, users can view, edit, or delete the post's content.
