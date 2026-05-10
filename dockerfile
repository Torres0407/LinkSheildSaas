FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
# Step 2: Connecting to Git (The "Source")
# Go to GitHub.com and create a repository named linkshield-saas.
# In your browser, click "uploading an existing file".
# Drag and drop your pom.xml, LinkShieldUI.java, and Dockerfile into the box.
# Click Commit changes.
# Step 3: The "Ghost" Automation (Workflows)
# Now we set up the CI/CD pipeline.
# 