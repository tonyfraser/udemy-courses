# get animal noises


def speak(animal="dog"):
    noises = {"dog": "woof", "pig": "oink", "duck": "quack", "cat": "meow"}
    noise = noises.get(animal)
    if noise:
        return noise
    return "?"

return day of week

#get day of week
def return_day(dayNum):
    days = {1:'Sunday',
        2:'Monday',
        3:'Tuesday',
        4:'Wednesday',
        5:'Thursday',
        6:'Friday',
        7:'Saturday'}
    return days.get(dayNum)


#return the last elelment of a list
def last_element(element):
    return element.pop()
#but fails for empty array
def return_element(el):
    try:
        return el.pop()
    except IndexError as e:
        return None
#or
def last_element(l):
    if l:
        return l[-1]
    return None

#which number is bgger?
def number_compare(a,b):
    if a > b:
        return "First is greater"
    elif b > a:
        return "Second is greater"
    return "Numbers are equal"

#counting letters in a string
def single_letter_count(word, letter):
    return len([char for char in word.lower() if char == letter])
def single_letter_count(string,letter):
    return string.lower().count(letter.lower())

def multiple_letter_count(string):
    return {letter: string.count(letter) for letter in string}

#palindrome
def is_palindrome(test_string):
    if test_string.lower() == ''.join(reversed(test_string.lower())):
        return True
    else:
        return False
is_palindrome("cat")

def is_palindrome(test_string):
    return test_string.lower() == ''.join(reversed(test_string.lower()))
is_palindrome("cat")
