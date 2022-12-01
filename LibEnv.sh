#!/bin/bash

SETX LIBRARY_SERVER_PORT "8081"
SETX LIBRARY_DB_NAME "uni_library"
SETX LIBRARY_DB_PASSWORD "library123"
SETX LIBRARY_DB_URL "jdbc:postgresql://localhost:5432/uni_library"
SETX LIBRARY_DB_USER "libraryroot"

echo "All env variables are set!"