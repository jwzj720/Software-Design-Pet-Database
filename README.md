# Navigating the Database
The Pet Adoption Database strives to streamline the process of matching adopters and fosterers to pets who need a home.
The main menu has options to navigate to a list of profiles, creating a new profile, or viewing statistics.
The list of profiles allows specific profiles to be opened.
From a specific family or pet profile, the user can navigate to the match window, which displays potential adoption and fostering matches.
The profiles also contain foster and adopt buttons that allow a match to be created and updated in the database.

# Creating New Profiles
Editing and creating new profiles requires very specific inputs in order to successfully update and add profiles to the database.
When a new profile is created, all fields must be filled out before pressing save and exiting the window.
Each field requires either an integer, a string, a specific string from a list of options, or a list seperated by commas.
Incorrect inputs will produce errors, so it is necessary to format the inputs correctly and ensure that there are no extra spaces.

## Family Profile
- Family Name allows any string to be the name.
- Family Size and Ideal Age require an integer.
- Marital Status, Kids Status, Existing Pets, and Willing to Foster require either "true" or "false".
- Energy level preference requires "low", "medium", or "high".
- Breed preference requires 3 names of breeds, seperated by commas with no spaces.

## Pet Profile
- Pet Name, Color, breed, notes, and species allows any string to be the input.
- Age requires an integer.
- Sex requires either "M" for male or "F" for female.
- Energy Level requires "low", "medium", or "high".
- Has been fostered, good with kids, potty trained, allergenic, and good with pets all require either "true" or "false".
<<<<<<< HEAD
- Certifications and Health Conditions require any strings in a list seperated by commas.
=======
- Certifications and Health Conditions require any strings in a list seperated by commas.
>>>>>>> GUI
