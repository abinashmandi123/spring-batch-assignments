# spring-batch-assignments
Spring-batch-assignments

I have done unit testing for spring-batch-assignment2.I have done unit testing for processor class.This unit testing of processor class covers the reader,processor and the writer class.Initially i was doing testing for the batch job which was using JobRepositoryTestUtils class.This class by default was invoking sql datasource.I have implemented the spring batch application with mongodb.I did not found any resources how to test the batch job.


I have created docker image for spring-batch-assignment1.
error-Failed to initialize the reader the reason for this error is that inside the docker container the reader is not able to read the file.
solution-i copied the file from my local directory to the docker directory using docker cp command.so now the reader is able to access the file inside the container

SOAPExample and SOAPExampleCLient are the two directories which are part of assignment 9.Assignment 9 is to create a soap service with crud operations.
