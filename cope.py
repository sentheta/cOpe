#!/usr/bin/env python3
import json
import webbrowser
# import os

# # clear screen
# def clearScreen():
#     if os.name=='nt': os.system('cls')
#     else: os.system('clear')

# get user input, with python terminal -like format
def getInput(arrow=False):
	try:
		if arrow: print(">>> ", end='')
		s = str(input())
		# print(f"The string received is {s.encode().hex()}")
		return s
	except KeyboardInterrupt:
		print("Exiting...")
		exit()

# load and write course.json file
def loadCourse():
	try:
		global courses
		with open("courses.json","r") as file:
			courses = json.load(file)
	except Exception as e: print(f"Error: {e}")

def writeCourse():
	try:
		global courses
		with open("courses.json","w") as file:
			json.dump(courses, file, indent=4)
	except Exception as e: print(f"Error: {e}")

# print out course list
def displayCourse():
	try:
		for i,course in enumerate(courses):
			print(f"{i:2}. {course["code"]:<12}{course["title"]}")
	except Exception as e: print(f"Error: {e}")

# modify the course list
def addCourse():
	try:
		global courses

		course = {}
		print("New course code: ", end='');
		course["code"] = getInput()
		print("New course title: ", end='');
		course["title"] = getInput()
		print("New course link: ", end='');
		course["link"] = getInput()

		courses.append(course)
		writeCourse()
		print("Course added");
	except Exception as e: print(f"Error: {e}")

def removeCourse():
	try:
		global courses

		print("Index to be removed: ", end='');
		id = int(getInput())

		courses.pop(id)
		writeCourse()
		print("Course removed");
	except Exception as e: print(f"Error: {e}")
		
# let user choose action
def chooseAction():
	try:
		displayCourse()
		s = getInput(True)

		# can do up-arrow to get previous line
		global prevS
		if s.encode().hex() == "1b5b41":
			s = prevS
		prevS = s

		# special command
		if s=="-":
			removeCourse()
			return
		if s=="+":
			addCourse()
			return
		if s=="":
			print("Exiting...")
			exit()

		# check index, course code, course title, in that order
		if s.isdigit():
			webbrowser.open_new_tab(courses[int(s)]["link"])
			return
		for course in courses:
			if(s.lower() == course["code"].lower()):
				webbrowser.open_new_tab(course["link"])
				return
		for course in courses:
			if s.lower() in course["title"].lower():
				webbrowser.open_new_tab(course["link"])
				return
		
		print(f"No matching course found")
	except Exception as e: print(f"Error: {e}")


if __name__=="__main__":
	loadCourse()

	headerArt =''' _   _  __  ___
/ \\ / \\ | \\ |__
|   | | |_/ |__
\\_/ \\_/ |   |__
'''
	print(headerArt)

	while True:
		# clearScreen()
		print("cOpe: Course OPEner | by vanwij")
		chooseAction()
		print()
