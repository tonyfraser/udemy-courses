# numbers = (1, 2, 3, 4)
# result = map(
#     lambda x: x + x, numbers
# )
# # print(list(result))
# for range()
#
#
# fruits = ["apple", "banana", "cherry"]
# fruits.map (
#     lambda x : if x == "apple":
#         print("yum")
# )
# def is_all_strings(ls):
#     subl = [a for a in ls if isinstance(a, str)]
#     if len(ls) == len(subl):
#         return True
#     else:
#         return False
#
# print(is_all_strings(["tony", "fraser"]))

# #Lamda Function
# f = lambda x, y: x * y
# f(1, 2)


# fruits = ["apple", "banana", "cherry"]
# for x in fruits:
#   if x == "banana":
#     continue
#   print(x)

# for i in range(6):
#     print(i)

# def extremes(i):
#     min = min(i)
#     max = max(i)
#     return tup(min, max)
#

#a function that returns the max abolute value
# def max_magnitude(l):
#     return max(list(map(lambda x : abs(x), l)))
# print(max_magnitude([1, 2, -100]))
#also
# def max_magnitude(nums):
#     return max(abs(num) for num in nums)


# sum_even_values(4,2,1,10) # 16
# sum_even_values(1) # 0

# def sum_even_values(*args):
#     return sum([x for x in args if x %2 ==0])
# print(sum_even_values(0,1,2,3,4))

# sum_floats(1.5, 2.4, 'awesome', [], 1) # 3.9
# sum_floats(1,2,3,4,5) # 0
# def sum_floats(*args):
#     return sum(x for x in args if isinstance(x,float))
# print(sum_floats(1.5, 2.4, 'awesome', [], 1))
#
# '''
# triple_and_filter([1,2,3,4]) # [12]
# triple_and_filter([6,8,10,12]) # [24,36]
# '''
# my_list = [0,4,8,9]
def triple_and_filter(l):
    return [x*3 for x in l if x % 4 == 0]
#
# print(triple_and_filter(my_list))


# '''
# names = [{'first': 'Elie', 'last': 'Schoppik'}, {'first': 'Colt', 'last': 'Steele'}]
# extract_full_name(names) # ['Elie Schoppik', 'Colt Steele']
# '''
#
# def extract_full_name(l):
#     return list(map(lambda val: "{} {}".format(val['first'], val['last']), l))

# Write a function called divide, which accepts two parameters (you can call them num1 and num2). The function should return the result of num1 divided by num2. If you do not pass the correct amount of arguments to the function, it should return the string "Please provide two integers or floats". If you pass as the second argument a 0, Python will raise a ZeroDivisionError, so if this function is invoked with a 0 as the value of num2, return the string "Please do not divide by zero"

    # Examples

    # divide(4,2)  2
    # divide([],"1")  "Please provide two integers or floats"
    # divide(1,0)  "Please do not divide by zero"

# def divide(x,y):
#     try:
#         answer = x / y
#     except TypeError:
#         return "Please provide two integers or floats"
#     except ZeroDivisionError:
#         return "Please do not divide by zero"
#     return answer
#
# print(divide(4,2))
# print(divide('a', 2))
# print(divide(2, 0))

# comment class with username, text and likes.
# likes should default to zero.

# class Comment:
#     def __init__(self, u, t, **kwargs):
#         self.username = u
#         self.text = t
#         if "likes" in kwargs:
#             self.likes = kwargs.get("likes")
#         else:
#             self.likes = 0
#
# c = Comment("davey", "lol", likes=25)
# print(c.username)
# print(c.text)
# print(c.likes)
 #"  {c.likes}")

# class BankAccount:
#     def __init__(self, owner):
#         self.owner = owner
#         self.balance = 0
#     def deposit(self, amount):
#         self.balance = self.balance + amount
#         return self.balance
#     def withdraw(self, amount):
#         self.balance = self.balance - amount
#         return self.balance
# bk = BankAccount("tony")
# print(bk.deposit(5))
# print(bk.withdraw(4))

# class Chicken:
#     total_eggs = 0
#     def __init__(self, name, species):
#         self.species = species
#         self.name = name
#         self.eggs = 0
#     def lay_egg(self):
#         self.eggs += 1
#         Chicken.total_eggs += 1

# '''
# days = week()
# next(days) # 'Monday'
# next(days) # 'Tuesday'
# next(days) # 'Wednesday'
# next(days) # 'Thursday'
# next(days) # 'Friday'
# next(days) # 'Saturday'
# next(days) # 'Sunday'
# next(days) # StopIteration
# '''
# def week():
#     days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
#     for day in days:
#         yield day
# days = week()
# print(next(days))


# def yes_or_no():
#     a = "yes"
#     while True:
#         yield a
#         a = "no" if a == "yes" else "yes"
#
# yon = yes_or_no()
# print(next(yon))
# print(next(yon))
# print(next(yon))
# print(type(yon))

# def make_song(repeat_count):
#     while repeat_count >=0 :
#         yield "hello" + str(repeat_count)
#         repeat_count -=1
#
# ms = make_song(10)
# print(next(ms))
# print(next(ms))

#
# from random import shuffle
#
# class Card:
# 	def __init__(self, value, suit):
# 		self.value = value
# 		self.suite = suit
#
# 	def __repr__(self):
# 		# return "{} of {}".format(self.value, self.suit)
# 		return "{} of {}".format({self.value}, {self.suite})
#
#
# class Deck:
# 	def __init__(self):
# 		suits = ["Hearts", "Diamonds", "Clubs", "Spades"]
# 		values = ['A','2','3','4','5','6','7','8','9','10','J','Q','K']
# 		self.cards = [Card(value, suit) for suit in suits for value in values]
#
# 	def __repr__(self):
# 		return "Deck of {} cards".format({self.count()})
#
# 	def count(self):
# 		return len(self.cards)
#
# 	def _deal(self, num):
# 		count = self.count()
# 		actual = min([count,num])
# 		if count == 0:
# 			raise ValueError("All cards have been dealt")
# 		cards = self.cards[-actual:]
# 		self.cards = self.cards[:-actual]
# 		return cards
#
# 	def deal_card(self):
# 		return self._deal(1)[0]
#
# 	def deal_hand(self, hand_size):
# 		return self._deal(hand_size)
#
# 	def shuffle(self):
# 		if self.count() < 52:
# 			raise ValueError("Only full decks can be shuffled")
#
# 		shuffle(self.cards)
# 		return self
# tf ="tf"
# print(f"{tf} tony fraser")

# class Character:
#     def __init__(self, name, hp, level):
#         self.name = name
#         self.hp = hp
#         self.level = level
#
# class NPC(Character):
#     def __init__(self, name, hp, level):
#         super().__init__(name, hp, level)
#
#     def speak(self):
#         return "{0} says: 'I heard monsters running around last night!'".format(self)
#
# rockstar = NPC("tony", 6, 10)

#method resolution order / MRO
#print(rockstar.__mro__)
#print(help(rockstar)) # .__mro__ not defined. rockstar.mro() also not defined.

#MRO example
# class Mother:
#     def __init__(self):
#         self.eye_color = "brown"
#         self.hair_color = "dark brown"
#         self.hair_type = "curly"
# class Father:
#     def __init__(self):
#         self.eye_color = "blue"
#         self.hair_color = "blond"
#         self.hair_type = "straight"
# class Child(Mother, Father):
#     pass
#
# tony = Child()
# help(tony)

# class Train:
#     def __init__(self, num_cars):
#         self.num_cars = num_cars
#     def __repr__(self):
#         return str(self.num_cars) + " car train"
#     def __len__(self):
#         return self.num_cars
# t = Train(4)
# print(t)
# print(len(t))

# def get_multiples(num=1, count=10):
#     next_num = num
#     while count > 0:
#         yield next_num
#         count -= 1
#         next_num += num
#


#Generator expression versus building a list and adding all the values.
# import time
# # SUMMING 10,000,000 Digits With Generator Expression
# gen_start_time = time.time() # save start time
# print(sum(n for n in range(100000000)))
# gen_time = time.time() - gen_start_time # end time - start time
#
#
# # SUMMING 10,000,000 Digits With List Comprehension
# list_start_time = time.time()
# print(sum([n for n in range(100000000)]))
# list_time = time.time() - list_start_time
# print(f"sum(n for n in range(10000000)) took: {gen_time}")
# print(f"sum([n for n in range(10000000)]) took: {list_time}")


#write a decortor function that tracks method times, like a speed test.
# from time import time
# from functools import wraps
# def speed_test(fn):
#     @wraps(fn)
#     def wrapper(*args, **kwargs):
#         start_time = time()
#         result = fn(*args, **kwargs)
#         end_time = time()
#         print(fn.__name__ + ":time elapsed: " + str(end_time - start_time))
#         return result
#     return wrapper
#
# @speed_test
# def sum_sums_generator_function():
#     return sum(x for x in range(10000))
# @speed_test
# def sum_sums_list_generator():
#     return sum([x for x in range(1000)])
#
# print(sum_sums_generator_function())
# print(sum_sums_list_generator())

# a function that just prints inputs.
# from functools import wraps
# def show_args(fn):
#     @wraps(fn)
#     def wrapper(*args, **kwargs):
#         print("Here are the args:", args)
#         print("Here are the kwargs:", kwargs)
#         return fn(*args, **kwargs)
#     return wrapper
#
# @show_args
# def do_nothing(*args, **kwargs):
#     print([a for a in args])
#     print([b for b in kwargs])
#
# do_nothing(1, 2, 3, a="hi")

#Double function, return a function value twice.
# from functools import wraps
# def double_return(fn):
#     @wraps(fn)
#     def wrapper(*args, **kwargs):
#         val = fn(*args, **kwargs)
#         return [val, val]
#     return wrapper
#
# @double_return
# def give_back(val):
#     return(str(val) + " from function call")
# print(give_back(2))


#decorator for too many arguments into a Function
#uses tuples as inputs
# from functools import wraps
# def ensure_fewer_than_three_args(fn):
#     @wraps(fn)
#     def wrapper(*args, **kwargs):
#         if len(args) >=3:
#             return "Too many arguments!"
#         return fn(*args, **kwargs)
#     return wrapper
# @ensure_fewer_than_three_args
# def add(*mylist):
#     return sum(mylist)
# print(add(1,2,3,4))
# add_all(1,2,3,4,5,6) # "Too many arguments!"


# a function that makes sure arguments are only integers
# '''
# @only_ints
# def add(x, y):
#     return x + y
# add(1, 2) # 3
# add("1", "2") # "Please only invoke with integers."
# '''


#make a decorator function that assures only integers as inputs.
# from math import fsum as fsum
# from functools import wraps
# def only_ints(fn):
#     @wraps(fn)
#     def wrapper(*args, **kwargs):
#         if all(isinstance(arg, int) for arg in args):
#             return fn(*args)
#         return "Please only invoke with integers."
#     return wrapper
# @only_ints
# def add(*args):
#     return(fsum(list(args)))


# '''
# a function that checks kwargs to see if somebody is an admin
# and if not shows unauthorized.
# show_secrets(role="admin") # "Shh! Don't tell anybody!"
# show_secrets(role="nobody") # "Unauthorized"
# show_secrets(a="b") # "Unauthorized"
# '''
# from functools import wraps
# def ensure_authorized(fn):
#     @wraps(fn)
#     def wrapper(*args, **kwargs):
#         if kwargs.get("role") == "admin":
#             return fn(*args, **kwargs)
#         return "Unauthorized"
#     return wrapper


# a function that waits before running, using the sleep function.
# from functools import wraps
# from time import sleep
# def delay(timer):
#     def inner(fn):
#         @wraps(fn)
#         def wrapper(*args, **kwargs):
#             print("Waiting {}s before running {}".format(timer, fn.__name__))
#             sleep(timer)
#             return fn(*args, **kwargs)
#         return wrapper
#     return inner

#Assertions
#remember python3 -O means all assertions are ignored.
# def say_hi(name):
#   assert name == "Colt", "I only say hi to Colt!"
#   return "Hi" + name
#  print(say_hi("colt"))

#doctest
# def add(a, b):
#     """Add together x and y
#     >>> add(2,3)
#     5
#     """
#     return a + b

# def double(values):
# 	""" double the values in a list
# 	>>> double([1,2,3,4])
# 	[2, 4, 6, 8]
# 	>>> double([])
# 	[]
# 	>>> double(['a', 'b', 'c'])
# 	['aa', 'bb', 'cc']
# 	>>> double([True, None])
# 	Traceback (most recent call last):
# 		...
# 	TypeError: unsupported operand type(s) for *: 'int' and 'NoneType'
# 	"""
# 	return [2 * element for element in values]

# def true_that():
# 	"""
# 	>>> true_that()
# 	True
# 	"""
# 	return True

# copies a file from one to another, but reverses it.
# uses string slices
# def copy_and_referse(file_name, new_file_name):
#     with open(file_name) as file:
#         text = file.read()[::-1]
#
#     with open(new_file_name, "w") as new_file:
#         new_file.write(text)


#get stats on any given file
# '''
# statistics('story.txt')
# # {'lines': 172, 'words': 2145, 'characters': 1111227}
# '''
# def statistics(file_name):
#     with open(file_name) as file:
#         lines = file.readlines()
#
#     return { "lines": len(lines),
#              "words": sum(len(line.split(" ")) for line in lines),
#              "characters": sum(len(line) for line in lines) }

#find_and_replace('story.txt', 'Alice', 'Colt')

def find_and_replace(f, needle, haystack):
    with open (f, 'w') as file:
        newdata = str(open(f).read())\
            .replace(needle,haystack)\

        file.write(newdata)
find_and_replace('fraser.csv', 'tori', 'gf')
