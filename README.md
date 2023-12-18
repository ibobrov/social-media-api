# Social Media API Project Description

Develop a RESTful API for a social media platform that allows users to register, log in, create posts, communicate with each other, subscribe to other users, and receive their activity feed.

## Requirements:

1. Authentication and Authorization:
    - Users can register by providing a username, email, and password.
    - Users can log in by providing valid credentials.
    - The API should ensure the confidentiality of user data, including password hashing and the use of JWT.

2. Post Management:
    - Users can create new posts by specifying text, title, and attaching images.
    - Users can view posts from other users.
    - Users can update and delete their own posts.

3. User Interaction:
    - Users can send friend requests to other users. Once a request is sent, the requesting user remains a follower until they unsubscribe. If the receiving user accepts the request, both users become friends. If the request is declined, the requesting user still remains a follower.
    - Friends are also followers of each other.
    - If one friend removes the other from their friends, they also unsubscribe. The second user should still remain a follower.
    - Friends can send messages to each other (chat implementation is not required, users can request messages using a request).

4. Subscriptions and Activity Feed:
    - User's activity feed should display the latest posts from the users they are subscribed to.
    - The activity feed should support pagination and sorting by post creation time.

5. Error Handling:
    - The API should handle and return clear error messages for incorrect requests or internal server problems.
    - The API should validate entered data and return informative messages for incorrect formats.

6. API Documentation:
    - The API should be well-documented using tools like Swagger or OpenAPI.
    - The documentation should include descriptions of available endpoints, request and response formats, as well as authentication requirements.

7. Platform Functionality:
    - Users can like and comment on posts.
    - Users can search for other users by username or email.
    - Users can edit their profile information, such as a profile picture and bio.
    - Users can follow hashtags and see posts related to those hashtags in their activity feed.
    - Users can block other users to prevent interactions with them.
    - Users can report posts or other users for inappropriate content or behavior.
    - Users can receive notifications for new friend requests, messages, and mentions.
    - Users can share posts on other social media platforms.
    - Users can customize their privacy settings to control who can see their posts and profile information.

## Technologies and Tools:

- Programming Language: Kotlin
- Framework: Spring Boot, JUnit
- Database: PostgreSQL
- Authentication and Authorization: Spring Security
- API Documentation: Swagger
- Tools: Gradle, Docker, TestContainer

## Expected Deliverables:

- Developed RESTful API that meets the specified requirements.
- Well-structured and documented project code.
- Tests covering the main API functionalities.
- API documentation describing available endpoints and their usage.
