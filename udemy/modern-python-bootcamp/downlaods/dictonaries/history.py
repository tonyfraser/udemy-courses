using range
In [320]: list1 = ["CA", "NJ", "RI"]
     ...: list2 = ["California", "New Jersey", "Rhode Island"]
In [324]: answer = {list1[i] : list2[i] for i in range(0,3)}
In [325]: answer
Out[325]: {'CA': 'California', 'NJ': 'New Jersey', 'RI': 'Rhode Island'}

using zip
In [327]: list3 = dict(zip(list1, list2))
In [328]: list3
Out[328]: {'CA': 'California', 'NJ': 'New Jersey', 'RI': 'Rhode Island'}


-- creating dictonaries
list with two items to dict
In [329]:person = [["name", "Jared"], ["job", "Musician"], ["city", "Bern"]]
In [330]: type(person)
In [331]: d = dict(person)
In [332]: d
Out[332]: {'name': 'Jared', 'job': 'Musician', 'city': 'Bern'}

-- or using kv pairs
In [333]: {k:v for k,v in person}
Out[334]: {'name': 'Jared', 'job': 'Musician', 'city': 'Bern'}

-- dictionary from keys examples
In [336]: dict.fromkeys('aeiou', 0)
Out[336]: {'a': 0, 'e': 0, 'i': 0, 'o': 0, 'u': 0}

In [337]: {char:0 for char in 'aeiou'}
Out[337]: {'a': 0, 'e': 0, 'i': 0, 'o': 0, 'u': 0}
(same thing)
In [338]: {char:0 for char in ['a','e','i','o','u']}
Out[338]: {'a': 0, 'e': 0, 'i': 0, 'o': 0, 'u': 0}


--
Character codes for all capital letters char(55)
answer =  {i:chr(i) for i in range(65,91)}
