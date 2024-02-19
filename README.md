# Class API

This project provides an API for managing users, lessons, and authentication.

## Authentication

### Endpoints

- `/auth/login`: Endpoint for user authentication
- `/auth/register`: Endpoint for user registration

### Controllers

- `AuthenticationController`: Manages user authentication and registration

## Lesson

### Endpoints

- `/lesson/create`: Endpoint for creating a new lesson
- `/lesson/update/{id}`: Endpoint for updating an existing lesson
- `/lesson`: Endpoint for fetching lessons by teacher

### Controllers

- `LessonController`: Manages lesson creation, updating, and fetching

## User

### Endpoints

- `/user/approve/{id}`: Endpoint for approving a user
- `/user/pendent/list`: Endpoint for fetching pending approval users
- `/user/denied`: Endpoint for fetching denied users
- `/user/deny/{id}`: Endpoint for denying a user

### Controllers

- `UserController`: Manages user approval, denial, and fetching

## Database Models

- `PendingUserModel`: Model for pending user data

## Enums

- `UserRole`: Enum for user roles
- `UserStatus`: Enum for user status