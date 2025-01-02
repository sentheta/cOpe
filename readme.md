# cOpe

cOpe stands for course opener.

To allow user directly invoke this program in the terminal, please add the script `cope` to `~/.local/bin/` directory. Remember to write your own pathname (e.g. `/home/myUser/cope/`) in the script `cope`.

# Functionalities

User can input an index, course code, or keyword. The program will launch the course page based on the link in `courses.json`.

`+` allow user to add a course entry.

`-` allow user to remove a course entry.

`/` allow user to swap two course entries.

`up_arrow` will do the previously selected action.

`ctrl+C` and empty string will terminate the program.