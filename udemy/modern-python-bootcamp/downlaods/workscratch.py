#import re


#test for valid date formatting.
# import re
#
# def parse_date(input):
#     pattern = re.compile("^(?P<day>\d\d)[,/.](?P<month>\d\d)[,/.](?P<year>\d{4})$")
#     match = pattern.search(input)
#     if match:
#         return {
#             "d": match.group('day'),
#             "m": match.group('month'),
#             "y": match.group('year'),
#         }
#     return None
#
# print(parse_date("01/01/1999"))

# # search and replace regex.
# import re
# def censor(input):
#     pattern = re.compile(r'\bfrack\w*\b', re.IGNORECASE)
#     return pattern.sub("CENSORED", input)


# **********************************

# Solutions Part 1
# reverse_string
#
# def reverse_string(str):
#     s = ''
#     for i, char in enumerate(str[::-1]):
#         s += char
#     return s
#
#Reverse a sttring
# def reverse_string(s):
#     str = ""
#     for i in s:
#         str = i + str
#     return str
# print(reverse_string("hello world"))

#list_check
#
# '''
# # list_check(
#     [
#         [],[1],[2,3], (1,2)
#     ]
# ) # False
# list_check([1, True, [],[1],[2,3]]) # False
# list_check([[],[1],[2,3]]) # True
# '''
# def list_check(vals):
#         return all(type(v) == list for v in vals)
# print(list_check([1, True, [],[1],[2,3]]) )
# print(list_check([[],[1],[2,3]]))

# def list_check(vals):
#     return all(type(l) == list for l in vals)
#

# remove_every_other element in a list
# def remove_every_other(my_list):
#     return[val for i,val in enumerate(my_list) if i %2 == 0]
# print(remove_every_other([1,2,3,4,5]))

# sum_pairs
#accepts a list and a number and returns the first pair
#of numbres taht sum the number past into the function
# def sum_pairs(ints, s):
#     already_visited = set()
#     for i in ints:
#         difference = s - i
#         if difference in already_visited:
#             return [difference, i]
#         already_visited.add(i)
#     return []

# count the number of vowels in a sttring
# def vowel_count(string):
#     lower_case = string.lower()
#     return {letter: lower_case.count(letter) for letter in lower_case if letter in "aeiou"}
# print(vowel_count("please excuse my dear aunt sally"))

#my way, doesn't work.
# def titleize(my_string:str):
#     return my_string.title()
# print(titleize("tori rivera"))
# print(titleize('this is awesome') + " This Is Awesome") # "This Is Awesome"
# print(titleize('oNLy cAPITALIZe fIRSt') + "ONLy CAPITALIZe FIRSt") # "ONLy CAPITALIZe FIRSt"
# def titleize(string):
#     return ' '.join(s[0].upper() + s[1:] for s in string.split(' '))


# #find all integer factors from a number
# def find_factors(number):
#     i = 1
#     factors = []
#     while i <= number:
#         if number % i == 0: factors.append(i)
#         i += 1
#     return factors
# print(find_factors(6))

# #repeat a string N number of times
# def repeat(string, num):
#     if (num == 0):
#         return ''
#     i = 0
#     newStr = ''
#     while (i < num):
#         newStr += string
#         i += 1
#     return newStr
# print(repeat("tony", 2))
# print(repeat("tony", 3))

# truncate a string and add elipses
def truncate(string, n):
    if (n < 3):
        return "Truncation must be at least 3 characters."
    if (n > len(string) + 2):
        return string
    return string[:n - 3] + "..."

#
# def two_list_dictionary(keys, values):
#     collection = {}
#
#     for idx, val in enumerate(keys):
#         if idx < len(values):
#             collection[keys[idx]] = values[idx]
#         else:
#             collection[keys[idx]] = None
#
#     return collection


# range_in_list
#
# def range_in_list(lst, start=0, end=None):
#     end = end or lst[-1]
#     return sum(lst[start:end+1])
# same_frequency
#
# def same_frequency(num1,num2):
#     d1 = {letter:str(num1).count(letter) for letter in str(num1)}
#     d2 = {letter:str(num2).count(letter) for letter in str(num2)}
#
#     for key,val in d1.items():
#         if not key in d2.keys():
#             return False
#         elif d2[key] != d1[key]:
#             return False
#     return True
#
# nth
#
# def nth(arr, idx):
#     if idx >= 0:
#         return arr[idx]
#     return arr[idx + len(arr)]
# find_the_duplicate
#
# def find_the_duplicate(arr):
#     counter = {}
#     for val in arr:
#         if val in counter:
#             counter[val] += 1
#         else:
#             counter[val] = 1
#     for key in counter.keys():
#         if counter[key] > 1:
#             return int(key)


# Solutions Part 4
# sum_up_diagonals
# def sum_up_diagonals(arr):
#     total = 0
#
#     for i,val in enumerate(arr):
#         total += arr[i][i]
#         total += arr[i][-1-i]
#     return total
# min_max_key_in_dictionary
#
# def min_max_key_in_dictionary(d):
#     keys = d.keys()
#     return [min(keys), max(keys)]
# find_greater_numbers
#
# def find_greater_numbers(arr):
#     count = 0
#     i = 0
#     j = 1
#     while i < len(arr):
#         while j < len(arr):
#             if arr[j] > arr[i]:
#                 count += 1
#             j+=1
#         j = i+1
#         i+=1
#     return count;
# two_oldest
#
# def two_oldest_ages(ages):
#     return sorted(ages)[-2:]
# is_odd_string
#
# def is_odd_string(string):
#     total = sum((ord(c) - 96) for c in string.lower()) or 0
#     return total % 2 == 1
