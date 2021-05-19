
#!/bin/bash

print_blue(){
    printf "\e[1;34m$1\e[0m"
}

sh ./tests/ui_and_unit.sh
