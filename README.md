How to run your application

set up the db with name "notes"
run the db script

add the following environment variables

SERVER_PORT=**;
SPRING_DATASOURCE_DRIVERCLASSNAME=org.postgresql.Driver;
SPRING_DATASOURCE_PASSWORD=**;
SPRING_DATASOURCE_URL=jdbc:postgresql://ip:port/notes;
SPRING_DATASOURCE_USERNAME=**;
SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect

APIS
save API
    path variables -> userId
    This is the owner of the note

update API
    path variables -> userId, noteId
    owner id and the note id

status change (make notes ARCHIVE and ACTIVE)
    path variables -> userId, noteId
    path parameter -> status
    owner id and the note id
    incoming status should be different from the existing status

Get Notes API
    path variables -> userId
    path parameter -> status

Delete Note API
    path variables -> userId, noteId

Why java/springboot?
    faster development
    dependency injection
    my goto language
alternative -> NodeJS

New features
    search note
    implement note category
    note edit history/version
    
    
    
