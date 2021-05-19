print_red(){
    printf "\e[1;31m$1\e[0m"
}
print_green(){
    printf "\e[1;32m$1\e[0m"
}
print_yellow(){
    printf "\e[1;33m$1\e[0m"
}
print_blue(){
    printf "\e[1;34m$1\e[0m"
}

print_blue "\n\nStarting..."

print_blue "\n\ncd into working directory...\n"

print_blue "\n\nrun unit tests...\n"
./gradlew test
print_green "\n\n unit tests complete"

print_blue "\n\nrun android tests...\n"
./gradlew connectedAndroidTest
print_green "\n\n android tests complete"

print_yellow "\n\nAll tests complete..."
