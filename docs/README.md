# Demacia User Guide

![Product Image](https://github.com/Wrongian/ip/blob/master/docs/Ui.png)

Demacia is a chatbot that helps you **keep track of your tasks**. This application is mainly for users that want to obsessively do everything in a **CLI**. You can mark, add dates, add notes and delete tasks and more.

## Prerequirements To Run Application
- Java 17 installed

## How To Run The application
1. Download the latest release file(it is a **.jar** file)
2. Copy the .jar file into a preferably empty folder
3. Double click the .jar file to run the application
Alternatively, can change directory to the folder where the .jar file is and enter `java -jar demacia.jar` to run the application



## Features

### Adding a todo task
Adds a todo task to the task list. 

Format: `todo <name>`

Example: `todo read lord of the rings`

Output:
(assuming no tasks were in the list prior)
```
Got it. I have added this task:
[T][ ] read lord of the rings
Now you have 1 task in the list
```

### Adding a deadline task
Adds a deadline task to the task list. 

Format: `deadline <name> /by <date>`
- The dates are in the format `yyyy-MM-dd HH-mm`

Example: `deadline sleep /by 2025-02-20 04-00`

Output:
(assuming no tasks were in the list prior)
```
Got it. I have added this task:
[D][ ] sleep (by: 2025-02-20 04:00)
Now you have 1 task in the list
```

### Adding an event task
Adds an event task to the task list. 

Format: `event <name> /from <date> /to <date>`
- The dates are in the format `yyyy-MM-dd HH-mm`

Example: `event anime watch party /from 2025-02-21 09-00 /to 2025-02-21 16-00`

Output:
(assuming no tasks were in the list prior)
```
Got it. I have added this task:
[E][ ] anime watch party (from: 2025-02-21 09:00 to: 2025-02-21 16:00)
Now you have 1 task in the list
```

### Listing all tasks
Lists all the tasks in the task list.

Format: `list`

Example: `list`

Output:
(assuming there are some arbitrary tasks in the list)
```
1. [E][ ] anime watch party (from: 2025-02-21 09:00 to: 2025-02-21 16:00)
2. [T][ ] eat lunch
3. [D][ ] make speech on happiness (by: 2025-02-24 23:59)
```

### Deleting a task
Deletes a task in the task list.

Format: `delete <index>`
- The `index` is the position of the task in the list
- The index must be a positive integer 
- The index must be an index number shown in the task list

Example: `delete 2`

Output:
(assuming there is a todo called "eat lunch" at index 2)
```
I have removed the task
[T][ ] eat lunch
```

### Exiting the program
Exits the program.

Format: `bye`

Example: `bye`

Output:
The program will exit with no output.

### Marking a task
Marks a task in the task list as done.

Format: `mark <index>`
- The `index` is the position of the task in the list
- The index must be a positive integer 
- The index must be an index number shown in the task list

Example: `mark 3`

Output:
(assuming there is a todo called "Make a house out of bricks" at index 3)
```
Marked this task as done:
[T][X] Make a house out of bricks
```

### Unmarking a task
Unmarks a task in the task list as not done.

Format: `unmark <index>`
- The `index` is the position of the task in the list
- The index must be a positive integer 
- The index must be an index number shown in the task list

Example: `unmark 3`

Output:
(assuming there is a todo called "Make a house out of bricks" at index 3)
```
Marked this task as not done yet:
[T][ ] Make a house out of bricks
```

### Finding a task
Finds a task in the task list that contains a keyword.

Format: `find <keyword>`
- The search is case sensitive
- It only matches the name and not anything else

Example: `find brick`

Output:
(assuming there is are two abitrary tasks that contain this keyword)
```
Find results:
1. [T][ ] Make a house out of bricks
2. [T][ ] Get bricks
```

### Setting a note for a task
Sets a note for a task.

Format: `setnote <index> /note <note>`
- The `index` is the position of the task in the list
- The index must be a positive integer 
- The index must be an index number shown in the task list

Example: `setnote 3 /note I need to make bricks with clay first`

Output:
(assuming there is an unmarked todo at index 3 with the name 'Make a house out of bricks')
```
Successfully set the note
```

### Viewing a note for a task
Views a note for a task.

Format: `setnote <index> /note <note>`
- A note by default will be blank

Example: `getnote 3`

Output:
(assuming there is an unmarked todo at index 3 with the note 'I need to make bricks with clay first')
```
I need to make bricks with clay first
```

### Saving the task list
The task list data is saved to the hard disk automatically after every change.


## Transferring Save Files To Another Computer
In the folder where the .jar file is located, copy the /data file into another folder on the other computer where and place it in the same folder with the .jar file of the application

## Notable Points

### Reserved Characters 
The characters `,` and `:` are reserved so users will not be able to use them when entering commands.
