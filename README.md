# Superhero API

## Requests

### /findAll
**get** Method no parameters required

### /hero/{id}
**get** Method. URL Parameter Id must be a valid Hero Id

### /delete
**post** Method. Parameter Id must be a valid Hero Id

### /create
**post** method with parameters:

- pseudonym: text (not empty and not null)
- publisher: text (not empty and not null) and (either DC, MARVEL case insensitive)
- skills: Comma Separated text
- alliesId: Comma Separated text, must be of existing heroes
- firstAppearence: Date as text format YYYY-MM-DD
- name: text (not empty and not null)